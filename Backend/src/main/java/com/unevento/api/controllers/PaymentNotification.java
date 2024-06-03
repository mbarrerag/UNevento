package com.unevento.api.controllers;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.merchantorder.MerchantOrderClient;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.merchantorder.MerchantOrder;
import com.mercadopago.resources.merchantorder.MerchantOrderPayment;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;

@Component
@RestController
@CrossOrigin
@RequestMapping("/payment-notification")
public class PaymentNotification {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(accessToken);
    }

    @GetMapping
    public ResponseEntity<String> handleNotification(@RequestParam Map<String, String> params) {
        String topic = params.get("topic");
        String id = params.get("id");

        MerchantOrder merchantOrder = null;
        try {
            if ("payment".equals(topic)) {
                PaymentClient paymentClient = new PaymentClient();
                Payment payment = paymentClient.get(Long.parseLong(id));
                merchantOrder = new MerchantOrderClient().get(payment.getOrder().getId());
            } else if ("merchant_order".equals(topic)) {
                merchantOrder = new MerchantOrderClient().get(Long.parseLong(id));
            }
        } catch (MPException | MPApiException e) {
            return ResponseEntity.status(500).body("Error processing notification: " + e.getMessage());
        }

        if (merchantOrder == null) {
            return ResponseEntity.badRequest().body("Invalid notification");
        }

        BigDecimal paidAmount = BigDecimal.ZERO;
        for (MerchantOrderPayment payment : merchantOrder.getPayments()) {
            if ("approved".equals(payment.getStatus())) {
                paidAmount = paidAmount.add(payment.getTransactionAmount());
            }
        }

        String responseMessage;
        if (paidAmount.compareTo(merchantOrder.getTotalAmount()) >= 0) {
            if (!merchantOrder.getShipments().isEmpty()) {
                if ("ready_to_ship".equals(merchantOrder.getShipments().get(0).getStatus())) {
                    responseMessage = "Totally paid. Print the label and release your item.";
                } else {
                    responseMessage = "Shipment not ready to ship.";
                }
            } else {
                responseMessage = "Totally paid. Release your item.";
            }
        } else {
            responseMessage = "Not paid yet. Do not release your item.";
        }

        return ResponseEntity.ok(responseMessage);
    }
}
