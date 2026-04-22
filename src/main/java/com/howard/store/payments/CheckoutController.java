package com.howard.store.payments;

import com.howard.store.dto.ErrorDto;
import com.howard.store.exceptions.CartEmptyException;
import com.howard.store.exceptions.CartNotFoundException;

import com.howard.store.repositories.OrderRepository;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;


    @PostMapping
    public CheckOutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);

    }

    @PostMapping("/webhook")
    public void handleWebHook(
            @RequestHeader Map<String, String> signature,
            @RequestBody String payload
    ){
        checkoutService.handleWebhookEvent(new WebhookRequest(signature, payload));
    }


    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Error creating a checkout session"));
    }
    @ExceptionHandler({CartEmptyException.class, CartNotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }
}
