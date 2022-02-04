package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum MessageVariable {

  CUSTOMER_NAME("#{고객이름}", ""),
  CUSTOMER_ID("#{고객 ID}", ""),
  INIT_PASSWORD("#{초기화비밀번호}", ""),
  ADV_NAME("#{광고 이름}", ""),
  ADV_START_DATE("#{광고시작일}", ""),
  ADV_END_DATE("#{광고종료일}", ""),
  PENDING_TYPE("#{보류 타입}", ""),
  REQ_DATE("#{신청일시}", ""),
  PASSWORD_CHANGE_URL("#{비밀번호 재설정 url}", ""),
  REMOVE_TARGET_DATE("#{계정 삭제 예정일}", ""),
  SLEEP_TARGET_DATE("#{휴면 예정일}", ""),
  ;

  private String variable;

  private String value;

  MessageVariable(String variable, String value) {
    this.variable = variable;
    this.value = value;
  }
}
