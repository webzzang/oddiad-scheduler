package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum AlrimTalkTemplateCodes {



  WITHDRAWAL_ACCOUNT("oddi_account_del", "계정 삭제 안내"),
  SLEEP_ACCOUNT("oddi_accout_sleep", "계정 휴면 알림"),
  ACCEPTED_ADV("oddi_ad_appr", "광고 승인 안내"),
  DENY_ADV("oddi_ad_deny", "광고 보류 안내"),
  EXPIRED_ADV("oddi_ad_exp", "광고 만료 안내"),
  AD_REFUND("oddi_ad_refund", "광고 취소 및 환불1"),
  VOC_COMMENT("oddi_cs_ans", "문의 답변 알림"),
  PASSWORD_FIND("oddi_pw_find", "비밀번호 찾기"),
  PASSWORD_INIT("oddi_pw_reset", "관리자 비밀번호 초기화"),
  SYS_ALERT("oddi_sys_alert", "시스템 얼럿"),
  ;


  private String templateId;

  private String templateName;

  AlrimTalkTemplateCodes(String id, String name) {
    this.templateId = id;
    this.templateName = name;
  }

  public static AlrimTalkTemplateCodes findByCode(String templateId) {
    for (AlrimTalkTemplateCodes code : AlrimTalkTemplateCodes.values()) {

      if (templateId.equalsIgnoreCase(code.getTemplateId())) {
        return code;
      }
    }
    return null;
  }
}
