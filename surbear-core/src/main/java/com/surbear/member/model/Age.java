package com.surbear.member.model;

public enum Age {
    UNDER_TWENTY("1~19"),
    TWENTIES("20~29"),
    THIRTIES("30~39"),
    FOURTIES("40~49"),
    FIFTIES("50~59"),
    OVER_SIXTIES("60~69"),
    ;

    private String ageRange;

    Age(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getAgeRange() {
        return ageRange;
    }
}
