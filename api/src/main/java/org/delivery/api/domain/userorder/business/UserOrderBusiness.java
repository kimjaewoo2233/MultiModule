package org.delivery.api.domain.userorder.business;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.business.model.UserOrderRequest;
import org.delivery.api.domain.userorder.service.UserOrderService;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;

    private final StoreMenuService storeMenuService;

    public void userOrder(User user, UserOrderRequest body) {
        var storeMenuEntity = storeMenuService.getStoreMenuWithThrow(body.getStoreMenuId());
    }
}
