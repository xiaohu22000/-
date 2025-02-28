package com.example.common.enums;

public enum MemberEnum {
    Yes("会员"),
    No("非会员"),
    ;
    MemberEnum(String info) {
        this.info = info;
    }

    public String info;

}
