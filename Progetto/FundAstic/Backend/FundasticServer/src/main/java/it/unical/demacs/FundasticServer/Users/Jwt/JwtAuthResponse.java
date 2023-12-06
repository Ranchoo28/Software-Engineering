package it.unical.demacs.FundasticServer.Users.Jwt;

import lombok.Getter;


@Getter
public class JwtAuthResponse {
    private final String accessToken;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
