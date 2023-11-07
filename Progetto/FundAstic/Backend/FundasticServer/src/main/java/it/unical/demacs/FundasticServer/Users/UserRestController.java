package it.unical.demacs.FundasticServer.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/users") // http://localhost:8080/api/v1/users

public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // il GetMapping serve per ottenere la lista degli utenti
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @PostMapping // il PostMapping serve per registrare un nuovo utente, attraverso il body della richiesta
    public void registerNewUser(@RequestBody Users user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}") // il DeleteMapping serve per eliminare un utente, attraverso il body della richiesta
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}") // il PutMapping serve per modificare un utente, attraverso il body della richiesta
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam (required = false) String name,
            @RequestParam (required = false) String surname,
            @RequestParam (required = false) String username,
            @RequestParam (required = false) String password,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) LocalDate birthday){

        userService.updateUser(userId, name, surname, username, password, email, birthday);
    }

}
