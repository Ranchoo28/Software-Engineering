package it.unical.demacs.FundasticServer.Users.Login;

import it.unical.demacs.FundasticServer.Users.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;


@RestController
@RequestMapping("api/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService ) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        return userService.loginUser(request);
    }
}
