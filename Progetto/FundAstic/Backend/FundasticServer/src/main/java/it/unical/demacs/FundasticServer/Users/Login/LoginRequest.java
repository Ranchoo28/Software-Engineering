package it.unical.demacs.FundasticServer.Users.Login;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {}
}

