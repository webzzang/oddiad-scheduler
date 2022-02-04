package com.exflyer.oddiad.scheduler.task.service.device;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.task.codes.AlrimTalkTemplateCodes;
import com.exflyer.oddiad.scheduler.task.codes.DeviceCode;
import com.exflyer.oddiad.scheduler.task.codes.MemberStateCode;
import com.exflyer.oddiad.scheduler.task.dto.DeviceStat;
import com.exflyer.oddiad.scheduler.task.dto.ManagerGroup;
import com.exflyer.oddiad.scheduler.task.model.KakaoTemplate;
import com.exflyer.oddiad.scheduler.task.model.Notification;
import com.exflyer.oddiad.scheduler.task.model.NotificationGroup;
import com.exflyer.oddiad.scheduler.task.repository.DeviceStatMapper;
import com.exflyer.oddiad.scheduler.task.repository.KakaoTemplateRepository;
import com.exflyer.oddiad.scheduler.task.repository.ManagerNotificationRepository;
import com.exflyer.oddiad.scheduler.task.repository.NotificationGroupRepository;
import com.exflyer.oddiad.scheduler.task.repository.NotificationRepository;
import com.exflyer.oddiad.scheduler.task.service.message.AlrimtalkVariableService;
import com.exflyer.oddiad.scheduler.task.service.notification.NotificationService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class DeviceStateService {

    @Autowired
    private DeviceStatMapper deviceStatMapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private KakaoTemplateRepository kakaoTemplateRepository;

    @Autowired
    private ManagerNotificationRepository managerNotificationRepository;

    @Autowired
    private NotificationGroupRepository notificationGroupRepository;

    public void deviceState() {

        LocalDateTime nowDate = LocalDateUtils.korNowDateTime();
        List<DeviceStat> deviceStatList = deviceStatMapper.findDeviceState(nowDate);

        if (CollectionUtils.isEmpty(deviceStatList)) {
            return;
        }

        Optional<KakaoTemplate> templateOptional = kakaoTemplateRepository.findById(AlrimTalkTemplateCodes.SYS_ALERT.getTemplateId());
        if (!templateOptional.isPresent()) {
            log.info("!!! deviceStat alrimtalk template null");
            return;
        }

        //알림톡 템플릿이 있으면
        KakaoTemplate kakaoTemplate = templateOptional.get();

        List<Notification> notificationList = new ArrayList<>();

        //장비 상태 심각일 경우 알림수신 회원 조회
        deviceStatList.forEach(datas -> {

            String msg = datas.getMallName() + datas.getDeviceName();
            String deviceState = datas.getDeviceState();

            //광고 재생 실패 일 경우
            if("run".equals(deviceState) && (datas.getModDateAdd() > 5)) {
                notificationList(DeviceCode.DDT001.getCode(), kakaoTemplate, msg +" " + DeviceCode.DDT001_MSG.getName(),nowDate);
            }
            //앱 백그라운드 (5분)
            if("pause".equals(deviceState) && (datas.getModDateAdd() > 5)) {
                notificationList(DeviceCode.DDT002.getCode(),kakaoTemplate,  msg +" " + DeviceCode.DDT002_MSG.getName(),nowDate);
            }
            //앱 중지 (5분)
            if("stop".equals(deviceState) && (datas.getModDateAdd() > 5)) {
                notificationList(DeviceCode.DDT003.getCode(),kakaoTemplate,  msg +" " + DeviceCode.DDT003_MSG.getName(),nowDate);
            }
            //앱 구동 실패
            if("start".equals(deviceState) && (datas.getModDateAdd() > 5)) {
                notificationList(DeviceCode.DDT004.getCode(),kakaoTemplate,  msg +" " + DeviceCode.DDT004_MSG.getName(),nowDate);
            }
            //앱 반복 종료
            if(("destroy_exception".equals(deviceState) || "exception".equals(deviceState)) && (datas.getModDateAdd() < 11)) {
                notificationList(DeviceCode.DDT005.getCode(),kakaoTemplate,  msg +" " + DeviceCode.DDT005_MSG.getName(),nowDate);
            }
            //앱 종료 유지
            if(("destroy_user".equals(deviceState) || "destroy_exception".equals(deviceState) || "exception".equals(deviceState)) && (datas.getModDateAdd() > 5)) {
                notificationList(DeviceCode.DDT006.getCode(),kakaoTemplate,  msg +" " + DeviceCode.DDT006_MSG.getName(),nowDate);
            }
        });

        if (CollectionUtils.isEmpty(notificationList)) {
            log.info("!!! deviceStat notificationList null");
            return;
        }
        notificationRepository.saveAll(notificationList);
    }

    public List<Notification> notificationList(String deviceStateCode,KakaoTemplate kakaoTemplate, String msg,LocalDateTime nowDate){

        List<Notification> notificationList = new ArrayList<>();

        List<ManagerGroup> managerGroupsList = deviceStatMapper.findManagerGroup(MemberStateCode.ADMIN_USING.getCode(), deviceStateCode,nowDate);//알림톡 받을 관리자

        if (CollectionUtils.isEmpty(managerGroupsList)) {
            log.info("!!! notificationList managerGroupsList null");
            return notificationList;
        }

        //처음 notificationGroup 갯수는 0
        NotificationGroup notificationGroup = notificationService.createGroup(kakaoTemplate, managerGroupsList.size());
        int iGroupCnt = 0;
        for(ManagerGroup list: managerGroupsList) {

            boolean updateYn = false;

            //reg_date_add > 0 이면 알림톡 보내기
            if(list.getManagerPush() == true && list.getRegDateAdd() > 0) {
                Notification notificationKakao = notificationService.createDevice(list, kakaoTemplate, msg, notificationGroup);
                notificationList.add(notificationKakao);
                iGroupCnt++;
                updateYn = true;
            }
            //알림톡 처음 보내는경우
            if(!list.getManagerPush()) {
                Notification notificationKakao = notificationService.createDevice(list, kakaoTemplate, msg, notificationGroup);
                notificationList.add(notificationKakao);
                iGroupCnt++;
                updateYn = true;
            }

            if(updateYn) {
                managerNotificationRepository.updateManagerPush(list.getRoleSeq(), list.getDeviceStateCode(), LocalDateUtils.korNowDateTime());
            }
        }

        notificationGroupRepository.updateNotificationGroup(notificationGroup.getSeq(), iGroupCnt);
        return notificationList;
    }
}
