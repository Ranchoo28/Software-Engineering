package it.unical.demacs.FundasticServer.Users.Registration;

import it.unical.demacs.FundasticServer.Users.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class RegistrationRequest {
    private long idUser;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String birthday;
    private Role role;

    public RegistrationRequest(){}
}
