package it.unical.demacs.FundasticServer.Users.Registration;

import it.unical.demacs.FundasticServer.Users.Role;
import it.unical.demacs.FundasticServer.Users.UserService;
import it.unical.demacs.FundasticServer.Users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Specifica l'origine del frontend
@RequestMapping("api/registration") // Specifica il percorso per accedere a questa classe ( http://localhost:8080/api/v1/users )
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody RegistrationRequest request ){
       return userService.addNewUser(request);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){ userService.deleteUser(userId); }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) String surname,
            @RequestParam (required = false) String username,
            @RequestParam (required = false) String password,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) String birthday,
            @RequestParam (required = false) Role role){

        userService.updateUser(userId, name, surname, username, password, email, birthday, role);
    }

}
