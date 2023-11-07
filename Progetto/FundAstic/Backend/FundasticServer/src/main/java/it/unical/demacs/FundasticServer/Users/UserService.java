package it.unical.demacs.FundasticServer.Users;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    public List<User> getStudent(){
        return List.of(new User(1L,
                "Mario",
                "Rossi",
                "savcreaa.kr99@gmail.com",
                LocalDate.of(1999, 5, 21)));
    }
}
