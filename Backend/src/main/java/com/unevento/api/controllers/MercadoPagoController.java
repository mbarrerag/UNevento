package com.unevento.api.controllers;


import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.MercadoPagoConfig;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


import com.unevento.api.domain.records.PreferenceData;

@CrossOrigin
@RestController
@RequestMapping("/create-preference")
public class MercadoPagoController {

    @PostMapping
    public Preference getMercadoPago(@RequestBody PreferenceData preferenceData) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken("APP_USR-5210675112328853-050421-46fbefcb9d6220c39df5051c721f31e1-1337650373");
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
                .success(("https://localhost:4200"))
                .pending("https://www.seu-site/pending")
                .failure("https://localhost:4200")
                .build();


        PreferenceRequest request = PreferenceRequest.builder()
                .backUrls(backUrls)
                .build();



        List<PreferencePaymentMethodRequest> excludedPaymentMethods = new ArrayList<>();

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();


        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(null)
                .backUrls(backUrls)
                .autoReturn("approved")
                .paymentMethods(null)
                .notificationUrl(null)
                .statementDescriptor("UNevento")
                .externalReference(null)
                .expires(true)
                .expirationDateFrom(null)
                .expirationDateTo(null)
                .build();





        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);


        return preference;
    }

}
