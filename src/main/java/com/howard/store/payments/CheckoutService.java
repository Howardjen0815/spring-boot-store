package com.howard.store.payments;

import com.howard.store.entities.Order;
import com.howard.store.exceptions.CartEmptyException;
import com.howard.store.exceptions.CartNotFoundException;
import com.howard.store.repositories.CartRepository;
import com.howard.store.repositories.OrderRepository;
import com.howard.store.services.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final StripePaymentGateway stripePaymentGateway;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckOutResponse checkout(CheckoutRequest request) throws PaymentException {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        if(cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart,authService.getCurrentUser());
        orderRepository.save(order);
        //create a checkout session
        try{
            var session=stripePaymentGateway.createCheckoutSession(order);
            cartService.clearCart(cart.getId());
            return new CheckOutResponse(order.getId(),session.getCheckoutUrl());
        }catch (PaymentException ex){
            orderRepository.delete(order);
            throw ex;
        }
    }

    public void handleWebhookEvent(WebhookRequest request) {
        paymentGateway.parseWebhookRequest(request).ifPresent(paymentResult -> {
            var order =  orderRepository.findById(paymentResult.getOrderId().intValue()).orElseThrow();
            order.setStatus(paymentResult.getPaymentStatus());
            orderRepository.save(order);
        });

    }

}
