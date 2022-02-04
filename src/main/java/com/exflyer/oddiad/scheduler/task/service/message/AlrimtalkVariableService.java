package com.exflyer.oddiad.scheduler.task.service.message;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.share.OddiEncryptor;
import com.exflyer.oddiad.scheduler.task.config.OddiUrlConfig;
import com.exflyer.oddiad.scheduler.task.model.Adv;
import com.exflyer.oddiad.scheduler.task.model.Member;
import com.exflyer.oddiad.scheduler.task.model.PartnerConfig;
import com.exflyer.oddiad.scheduler.task.model.Voc;
import com.exflyer.oddiad.scheduler.task.repository.PartnerConfigRepository;
import com.exflyer.oddiad.scheduler.task.repository.PaymentRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlrimtalkVariableService {

  @Autowired
  private OddiUrlConfig oddiUrlConfig;

  @Autowired
  private PartnerConfigRepository partnerConfigRepository;

  @Autowired
  private PaymentRepository paymentRepository;


  /**
   * 오디AD 알림> #{고객명} 님의 계정이 휴면 상태로 전환될 예정입니다.
   *
   * 고객님이 1년 동안 서비스에 로그인하지 않아 #{휴면 예정일}부터 고객님의 계정 (#{고객 ID}) 이 휴면 계정으로 전환됩니다.
   *
   * 휴면 전환 후 1년 경과 시에는 자동으로 탈퇴 처리되고 기존의 광고 이용 내역은 삭제되어 재가입 시에도 복구할 수 없습니다.
   * 휴면을 원치 않으실 경우 전환 예정일 이전에 오디에 접속하시어 로그인하시면 정상 이용이 가능합니다.
   *
   * #{홈페이지url}
   */
  public String convertSleepMemberVariable(Member member, String templateContents){
    String replace = StringUtils.replace(templateContents, "#{고객명}", member.getName());
    replace = StringUtils.replace(replace, "#{휴면 예정일}", LocalDateUtils.korNowDateTime().plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    replace = StringUtils.replace(replace, "#{고객 ID}", OddiEncryptor.decrypt(member.getId()));
    replace = StringUtils.replace(replace, "#{홈페이지url}", oddiUrlConfig.getUserWebHost());
    return replace;
  }

  /**
   * 오디AD 알림> #{고객이름} 님의 계정이 탈퇴 처리될 예정입니다.
   *
   * 고객님의 계정 (#{고객 ID}) 이 휴면 전환 후 1년 동안 접속 이력이 없어 #{계정 삭제 예정일}일에 고객님의 계정이 탈퇴 처리될 예정입니다.
   *
   * 탈퇴 처리 후에는 기존의 광고 이용 내역은 삭제되어 재가입 시에도 복구할 수 없습니다.
   * 탈퇴를 원치 않으실 경우 탈퇴 예정일 이전에 오디에 접속하시어 로그인하시면 정상 이용이 가능합니다.
   *
   * #{홈페이지 url}
   */
  public String convertWithdrawalMemberVariable(Member member, String templateContents) {
    String replace = StringUtils.replace(templateContents, "#{고객이름}", member.getName());
    replace = StringUtils.replace(replace, "#{계정 삭제 예정일}", LocalDateUtils.korNowDateTime().plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    replace = StringUtils.replace(replace, "#{고객 ID}", OddiEncryptor.decrypt(member.getId()));
    replace = StringUtils.replace(replace, "#{홈페이지 url}", oddiUrlConfig.getUserWebHost());
    return replace;
  }

  /**
   * 오디AD 알림> #{고객이름} 님 문의에 답변 드립니다.
   *
   * 문의: #{1:1문의 제목}
   * 답변 확인: #{문의글 Url}
   *
   * #{홈페이지 url}
   */
  public String convertVocCommentVariable(String name, Voc voc, String templateContents) {
    String replace = StringUtils.replace(templateContents, "#{고객이름}", name);
    replace = StringUtils.replace(replace, "#{1:1문의 제목}", voc.getTitle());
    replace = StringUtils.replace(replace, "#{문의글 Url}", oddiUrlConfig.getUserWebHost() + oddiUrlConfig.getVocDetail() + "/" + voc.getUniqCode());
    replace = StringUtils.replace(replace, "#{홈페이지 url}", oddiUrlConfig.getUserWebHost());
    return replace;
  }


  /**
   * 오디AD 알림> #{고객이름} 님이 신청하신 광고가 승인되었습니다.
   *
   * 광고 이름: #{광고 이름}
   * 신청일: #{신청 일시}
   * 광고 기간: #{광고시작일} ~ #{광고종료일}
   *
   * 광고 취소 및 환불은 #{광고시작일-광고취소가능일}까지 가능합니다.
   *
   * #{홈페이지 url}
   */
  public String convertAcceptedAdv(String name, String templateContents, Adv adv) {
    PartnerConfig partnerConfig = partnerConfigRepository.findByType("PTT001");
    String replace = StringUtils.replace(templateContents, "#{고객이름}", name);
    replace = StringUtils.replace(replace, "#{광고 이름}", adv.getTitle());
    replace = StringUtils.replace(replace, "#{신청 일시}", adv.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    LocalDate advStartDateTime = LocalDate.parse(adv.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
    replace = StringUtils.replace(replace, "#{광고시작일}", advStartDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    LocalDate advEndDateTime = LocalDate.parse(adv.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
    replace = StringUtils.replace(replace, "#{광고종료일}", advEndDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));

    String cancelAbleDay = advStartDateTime.minusDays(partnerConfig.getOddiAdvCancelDate()).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    replace = StringUtils.replace(replace, "#{광고시작일-광고취소가능일}", cancelAbleDay);
    replace = StringUtils.replace(replace, "#{홈페이지 url}", oddiUrlConfig.getUserWebHost());
    return replace;
  }

  /**
   * 오디AD 알림> #{고객이름} 님이 신청하신 광고가 보류되었습니다.
   *
   * 광고 이름: #{광고 이름}
   * 신청일: #{신청 일시}
   * 광고 기간: #{광고시작일} ~ #{광고종료일}
   * 보류 사유: #{보류사유}
   *
   * 오디AD에 접속하여 상세 내용 확인 후 다시 등록해주시기 바랍니다.
   *
   * #{홈페이지 url}
   */
  public String convertDenyAdv(String name, String templateContents, Adv adv) {
    String replace = StringUtils.replace(templateContents, "#{고객이름}", name);
    replace = StringUtils.replace(replace, "#{광고 이름}", adv.getTitle());
    replace = StringUtils.replace(replace, "#{신청 일시}", adv.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    LocalDate advStartDateTime = LocalDate.parse(adv.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
    replace = StringUtils.replace(replace, "#{광고시작일}", advStartDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    LocalDate advEndDateTime = LocalDate.parse(adv.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
    replace = StringUtils.replace(replace, "#{광고종료일}", advEndDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    replace = StringUtils.replace(replace, "#{보류사유}", adv.getRejectionReason());
    replace = StringUtils.replace(replace, "#{홈페이지 url}", oddiUrlConfig.getUserWebHost());
    return replace;
  }

  /**
   * 오디AD 알림> #{고객이름} 님이 진행 중인 광고가 20일 후 종료됩니다.
   *
   * 광고 이름: #{광고 이름}
   * 광고 기간: #{광고시작일} ~ #{광고종료일}
   *
   * 광고를 계속 진행하시려면 오디AD에 접속하여 다시 신청해주시기 바랍니다.
   *
   * #{홈페이지 url}
   */
  public String convertWillDoneAdv(String name, String templateContents, Adv adv) {
    String replace = StringUtils.replace(templateContents, "#{고객이름}", name);
    replace = StringUtils.replace(replace, "#{광고 이름}", adv.getTitle());
    LocalDate advStartDateTime = LocalDate.parse(adv.getStartDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
    replace = StringUtils.replace(replace, "#{광고시작일}", advStartDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    LocalDate advEndDateTime = LocalDate.parse(adv.getEndDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
    replace = StringUtils.replace(replace, "#{광고종료일}", advEndDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    replace = StringUtils.replace(replace, "#{홈페이지 url}", oddiUrlConfig.getUserWebHost());
    return replace;
  }

  /**
   * 오디AD 시스템 알림> #{경고 메시지} 기기 > 기기관리 메뉴에서 상태 확인 후 조치하시기 바랍니다. #{어드민 url}
   *
   * 기기이름 : #{경고 메시지}
   * 어드민url: #{어드민 url}
   *
   */
  public String convertDeviceStat(String templateContents, String msg) {
    String replace = StringUtils.replace(templateContents, "#{경고 메시지}", msg);
    replace = StringUtils.replace(replace, "#{어드민 url}", oddiUrlConfig.getManagerWebHost());
    return replace;
  }
}
