package it.unical.demacs.FundasticServer;

import it.unical.demacs.FundasticServer.Users.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class FundasticServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundasticServerApplication.class, args);
	}

}
