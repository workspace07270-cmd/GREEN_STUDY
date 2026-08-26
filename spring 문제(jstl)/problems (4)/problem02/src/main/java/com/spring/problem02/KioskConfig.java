package com.spring.problem02;

// TODO 1: @Component 어노테이션을 추가하라.

// TODO 2: @PropertySource("classpath:application.properties") 어노테이션을 추가하라.

public class KioskConfig {

    // TODO 3: @Value 어노테이션으로 아래 세 필드에 프로퍼티 값을 주입하라.
    //   kioskName  → kiosk.name
    //   taxRate    → kiosk.taxRate
    //   currency   → kiosk.currency
    private String kioskName;
    private int taxRate;
    private String currency;

    public void printInfo() {
        System.out.println("[KioskConfig] 가게: " + kioskName
                + " | 세율: " + taxRate + "%"
                + " | 통화: " + currency);
    }
}
