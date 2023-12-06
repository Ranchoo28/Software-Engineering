package it.unical.demacs.FundasticServer.Users.Login;

import it.unical.demacs.FundasticServer.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService ) {
        this.userService = userService;
    }

    // fare get per printare chi è loggato
    @GetMapping
    public void getLoggedUser(){
        //userService.getLoggedUser();
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        return userService.loginUser(request);
    }

    /*
    @GetMapping("/logout")
    public void logoutUser(){
        userService.logoutUser();
    }

     */
}
