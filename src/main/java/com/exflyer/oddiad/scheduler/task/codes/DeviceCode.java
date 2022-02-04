package com.exflyer.oddiad.scheduler.task.codes;

import lombok.Getter;

@Getter
public enum DeviceCode {

    DDT001("DDT001", "광고 재생 실패"),
    DDT002("DDT002", "앱 백그라운드(5분)"),
    DDT003("DDT003", "앱 중지(5분)"),
    DDT004("DDT004", "앱 구동 실패"),
    DDT005("DDT005", "앱 반복 종료"),
    DDT006("DDT006", "앱 종료 유지"),

    DDT001_MSG("DDT001", "광고 재생이 중지된 것으로 감지되었습니다."),
    DDT002_MSG("DDT002", "오디앱이 백그라운드 상태로 감지되었습니다."),
    DDT003_MSG("DDT003", "오디앱이 5분간 중지된 것으로 감지되었습니다."),
    DDT004_MSG("DDT004", "오디앱 구동이 실패한 것으로 감지되었습니다."),
    DDT005_MSG("DDT005", "오디앱이 반복적으로 비정상 종료되는 것으로 감지되었습니다."),
    DDT006_MSG("DDT006", "오디앱이 정상 종료 후 5분 동안 다시 시작되지 않았습니다."),

    DDT_MSG("DDT_MSG","기기 > 기기관리 메뉴에서 상태 확인 후 조치하시기 바랍니다."),
    ;


    private String code;

    private String name;

    DeviceCode(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
