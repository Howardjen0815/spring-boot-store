package com.howard.store.payments;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CheckOutResponse {
    private Long orderId;
    private String checkoutUrl;
    public CheckOutResponse(Long orderId, String  checkoutUrl) {
        this.orderId = orderId;
        this.checkoutUrl = checkoutUrl;
    }
}
