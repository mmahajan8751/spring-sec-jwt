package com.devmnack.springsecjwt.model;

import java.io.Serializable;

/**
 * this class will return jwt token
 */
public class AuthenticationResponse implements Serializable {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
