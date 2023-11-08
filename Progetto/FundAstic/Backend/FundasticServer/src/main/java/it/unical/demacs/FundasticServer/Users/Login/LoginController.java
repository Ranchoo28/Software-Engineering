package it.unical.demacs.FundasticServer.Users.Login;

import it.unical.demacs.FundasticServer.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        userService.getLoggedUser();
    }

    @PostMapping
    public void loginUser(@RequestBody LoginRequest request){
        userService.loginUser(request);
    }


}
