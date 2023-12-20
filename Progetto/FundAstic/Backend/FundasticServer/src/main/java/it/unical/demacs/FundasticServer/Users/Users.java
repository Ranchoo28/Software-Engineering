package it.unical.demacs.FundasticServer.Users;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
@Builder

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
    @Column(unique = true) private String username;
    private String password;
    @Column(unique = true) private String email;
    private String birthday;
    @Transient private Integer age; // Viene automaticamente calcolata in base alla data di nascita
    private Integer number;
    @Enumerated(EnumType.STRING) private Role role;

    public Users(String name, String surname, String username, String password, String email, String birthday, Integer number, Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.number = number;
        this.role = role;
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
