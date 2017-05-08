package com.iteso.appsmovil.proyectofinal.pojos;

import com.owlike.genson.annotation.JsonProperty;

public final class PaymentBean {

    private final String amount;

    public PaymentBean(@JsonProperty("amount") String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }



}