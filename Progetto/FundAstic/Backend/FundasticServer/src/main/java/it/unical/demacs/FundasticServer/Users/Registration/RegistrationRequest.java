package it.unical.demacs.FundasticServer.Users.Registration;

import it.unical.demacs.FundasticServer.Users.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class RegistrationRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
    private Integer number;
    private String  role;

    public RegistrationRequest(){}
}
