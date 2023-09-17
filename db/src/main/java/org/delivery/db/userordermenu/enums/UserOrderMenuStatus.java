package org.delivery.db.userordermenu.enums;

public enum UserOrderMenuStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ORDER("주문"),
    ACCEPT("확인"),
    COOKING("요리중"),
    DELIVERY("배달중"),
    RECEIVE("완료"),
    ;

    UserOrderMenuStatus(String description){
        this.description = description;
    }
    private String description;
}
