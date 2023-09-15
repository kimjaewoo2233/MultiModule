package org.delivery.db.store;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;

    private String description;

}
