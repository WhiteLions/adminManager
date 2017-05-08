package com.iteso.appsmovil.proyectofinal.pojos;

import com.owlike.genson.annotation.JsonProperty;

/**
 * Created by dideleo on 11/05/2015.
 */
public final class NewNotificationResponse {

    public static final String CREATED = "CREATED";
    public static final String ERROR = "ERROR";

    private final String status;
    private final String desc;

    public NewNotificationResponse(@JsonProperty("status") String status, @JsonProperty("desc") String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
