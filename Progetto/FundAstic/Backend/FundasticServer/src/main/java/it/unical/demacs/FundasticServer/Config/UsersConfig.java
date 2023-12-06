package it.unical.demacs.FundasticServer.Config;

import it.unical.demacs.FundasticServer.Users.Role;
import it.unical.demacs.FundasticServer.Users.Users;
import it.unical.demacs.FundasticServer.Users.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UsersConfig {
   // @Bean Serve solo per testare il database
    CommandLineRunner commandLineRunner(UsersRepository usersRepository){
        return args -> {
            Users Mario = new Users(
                    "Mario",
                    "Rossi",
                    "Ranchoo",
                    "sav@gmail.com",
                    "123456",
                    "1998-01-01",
                    Role.Publisher
            );

            usersRepository.save(Mario);
        };

    }
}
