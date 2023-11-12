package it.unical.demacs.FundasticServer.Users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table

public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String birthday;
    @Transient
    private Integer age; // Viene automaticamente calcolata in base alla data di nascita

    public Users() {}
    public Users(String name, String surname,String username, String password, String email, String birthday) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    public Integer getAge() {
        return Period.between(LocalDate.parse(this.birthday), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
