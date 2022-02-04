package com.exflyer.oddiad.scheduler.task.model;

import com.exflyer.oddiad.scheduler.share.LocalDateUtils;
import com.exflyer.oddiad.scheduler.share.OddiEncryptor;
import com.exflyer.oddiad.scheduler.task.dto.ManagerGroup;
import com.exflyer.oddiad.scheduler.task.dto.NotificationCollectDto;
import com.exflyer.oddiad.scheduler.task.dto.NotificationTargetDto;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 알림
 */
@Data
@Entity
@Table(name = "notification")
@NoArgsConstructor
public class Notification implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 순번
   */
  @Id
  @Column(name = "seq", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  /**
   * 그룹 순번
   */
  @Column(name = "group_seq", nullable = false)
  private Long groupSeq;

  @Column(name = "receive_id")
  private String receiveId;

  /**
   * 수신자 이름
   */
  @Column(name = "receive_name", nullable = false)
  private String receiveName;

  /**
   * 수신자 전화 번호
   */
  @Column(name = "receive_phone_number", nullable = false)
  private String receivePhoneNumber;

  /**
   * 내용
   */
  @Column(name = "contents", nullable = false)
  private String contents;

  /**
   * 발송 시간(즉시 일경우 0)
   */
  @Column(name = "send_time")
  private String sendTime;

  /**
   * 발신자 id
   */
  @Column(name = "sender_id", nullable = false)
  private String senderId;

  /**
   * 발신자 이름
   */
  @Column(name = "sender_name")
  private String senderName;

  /**
   * 발신자 전화 번호
   */
  @Column(name = "sender_phone_number")
  private String senderPhoneNumber;

  @Column(name = "kakao_template_id")
  private String kakaoTemplateId;

  @Column(name = "alrim_talk")
  private boolean alrimTalk = false;

  @Column(name = "reg_date")
  private LocalDateTime regDate;


  public Notification(Member member, String content, NotificationGroup notificationGroup, String senderPhoneNumber) {
    this.groupSeq = notificationGroup.getSeq();
    this.receiveId = member.getId();
    this.receiveName = member.getName();
    this.receivePhoneNumber = OddiEncryptor.decrypt(member.getPhoneNumber());
    this.contents = content;
    if (StringUtils.isBlank(notificationGroup.getSendTime())) {
      this.sendTime = "0";
    } else if (StringUtils.length(notificationGroup.getSendTime()) > 12) {
      this.sendTime = notificationGroup.getSendTime().substring(0, 12);
    } else {
      this.sendTime = notificationGroup.getSendTime();
    }
    this.senderId = notificationGroup.getRegId();
    this.senderName = notificationGroup.getRegName();
    this.senderPhoneNumber = senderPhoneNumber;
    this.kakaoTemplateId = notificationGroup.getTemplateId();
    this.alrimTalk = notificationGroup.isAlrimTalk();
    this.regDate = LocalDateUtils.korNowDateTime();
  }

  public Notification(NotificationCollectDto notificationCollectDto, NotificationTargetDto notificationTargetDto,
      String senderPhoneNumber) {
    this.groupSeq = notificationCollectDto.getSeq();
    this.receiveId = notificationTargetDto.getId();
    this.receiveName = notificationTargetDto.getName();
    this.receivePhoneNumber = OddiEncryptor.decrypt(notificationTargetDto.getPhoneNumber());
    this.contents = notificationCollectDto.getContents();
    if (StringUtils.isBlank(notificationCollectDto.getSendTime())) {
      this.sendTime = "0";
    } else if (StringUtils.length(notificationCollectDto.getSendTime()) > 12) {
      this.sendTime = notificationCollectDto.getSendTime().substring(0, 12);
    } else {
      this.sendTime = notificationCollectDto.getSendTime();
    }
    this.senderId = "SCHEDULER";
    this.senderName = "SCHEDULER";
    this.senderPhoneNumber = senderPhoneNumber;
    this.alrimTalk = false;
    this.regDate = LocalDateUtils.korNowDateTime();
  }


  public Notification(ManagerGroup managerGroup, String content, NotificationGroup notificationGroup, String senderPhoneNumber) {
    this.groupSeq = notificationGroup.getSeq();
    this.receiveId = managerGroup.getManagerId();
    this.receiveName = managerGroup.getName();
    this.receivePhoneNumber = OddiEncryptor.decrypt(managerGroup.getPhoneNumber());
    this.contents = content;
    if (StringUtils.isBlank(notificationGroup.getSendTime())) {
      this.sendTime = "0";
    } else if (StringUtils.length(notificationGroup.getSendTime()) > 12) {
      this.sendTime = notificationGroup.getSendTime().substring(0, 12);
    } else {
      this.sendTime = notificationGroup.getSendTime();
    }
    this.senderId = notificationGroup.getRegId();
    this.senderName = notificationGroup.getRegName();
    this.senderPhoneNumber = senderPhoneNumber;
    this.kakaoTemplateId = notificationGroup.getTemplateId();
    this.alrimTalk = notificationGroup.isAlrimTalk();
    this.regDate = LocalDateUtils.korNowDateTime();
  }

}
