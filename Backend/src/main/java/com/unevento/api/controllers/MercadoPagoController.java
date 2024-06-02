package com.unevento.api.controllers;


import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.MercadoPagoConfig;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.unevento.api.domain.records.PreferenceData;

@CrossOrigin
@RestController
@RequestMapping("/create-preference")
public class MercadoPagoController {

    @PostMapping
    public Preference getMercadoPago(@RequestBody PreferenceData preferenceData) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken("APP_USR-3054549772251715-052810-4d0e190c75da4281a1d68ca17cd8649c-1461153682");
        int id = preferenceData.id();
        String title = preferenceData.title();
        int price = preferenceData.unit_price();
        int quantity = preferenceData.quantity();



        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id(String.valueOf(id))
                .title(title)
                .description(null)
                .pictureUrl(null)
                .categoryId(null)
                .quantity(quantity)
                .currencyId("COP")
                .unitPrice(new BigDecimal(String.valueOf(price)))
                .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success(("https://unevento.vercel.app/home"))
                .pending("https://unevento.vercel.app/home")
                .failure("https://unevento.vercel.app/home")
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(null)
                .backUrls(backUrls)
                .autoReturn("approved")
                .paymentMethods(null)
                .notificationUrl("https://e274-2800-484-db6d-a600-ad3a-928c-21ff-a667.ngrok-free.app/notification")
                .statementDescriptor(null)
                .externalReference(null)
                .expires(true)
                .expirationDateFrom(null)
                .expirationDateTo(null)
                .build();


        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);


        return preference;
    }


    @CrossOrigin
    @GetMapping("/payment-status")
    public String getPaymentStatus(@RequestParam("payment_id") Long paymentId) {
        try {
            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(paymentId);

            if (payment != null) {
                String status = payment.getStatus();
                System.out.println("Payment status: " + status);

                if ("approved".equals(status)) {
                    return "The transaction was successful!";
                } else {
                    return "The transaction was not successful. Status: " + status;
                }
            } else {
                return "Payment not found.";
            }
        } catch (MPException | MPApiException e) {
            e.printStackTrace();
            return "An error occurred while retrieving the payment status.";
        }
    }

    @CrossOrigin
    @PostMapping("/payment-notification")
    public ResponseEntity<String> handleNotification() {






        return ResponseEntity.ok("LLego la notificacion de pago!");
    }
}


