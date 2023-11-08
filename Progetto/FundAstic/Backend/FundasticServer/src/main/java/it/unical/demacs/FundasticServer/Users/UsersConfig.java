package it.unical.demacs.FundasticServer.Users;

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
                    "1998-01-01"
            );

            usersRepository.save(Mario);
        };

    }
}
