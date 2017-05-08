package com.iteso.appsmovil.proyectofinal.pojos;

import com.owlike.genson.annotation.JsonProperty;

/**
 * Created by dideleo on 08/05/2015.
 */

public final class LoginBean {

    private final String username;
    private final String password;
    private final Boolean authorized;

    public LoginBean(@JsonProperty("username") String username, @JsonProperty("password") String password,@JsonProperty("authorized") Boolean authorized) {
        this.username = username;
        this.password = password;
        this.authorized = authorized;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public Boolean isAuthorized() {
        return authorized;
    }


}
