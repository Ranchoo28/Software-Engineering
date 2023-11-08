package it.unical.demacs.FundasticServer.Users;

import it.unical.demacs.FundasticServer.Handler.RegexHandler;
import it.unical.demacs.FundasticServer.Users.Login.LoginRequest;
import it.unical.demacs.FundasticServer.Users.Registration.RegistrationRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers(){ return usersRepository.findAll(); }

    public void loginUser(LoginRequest request){
        Optional<Users> userOptional = usersRepository.findUsersByUsername(request.getUsername());
        if(userOptional.isEmpty()) throw new IllegalStateException("Username or password are incorrect");
        else
            if(!userOptional.get().getPassword().equals(request.getPassword()))
                throw new IllegalStateException("Username or password are incorrect");
            else
                System.out.println("Login successful");
    }

    public void addNewUser(RegistrationRequest request) {
        Optional<Users> userOptional = usersRepository.findUsersByEmail(request.getEmail());
        if(userOptional.isPresent()) throw new IllegalStateException("Email already taken");
        userOptional = usersRepository.findUsersByUsername(request.getUsername());
        if(userOptional.isPresent()) throw new IllegalStateException("Username already taken");

        usersRepository.save(new Users(
                request.getName(),
                request.getSurname(),
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getBirthday()
        ));
    }

    public void deleteUser(Long id) {
       if(usersRepository.existsById(id)) usersRepository.deleteById(id);
         else throw new IllegalStateException("User with id " + id + " does not exist");
    }

    @Transactional
    public void updateUser(
            Long userId,
            String name,
            String surname,
            String username,
            String password,
            String email,
            String birthday) {

        Users userToUpdate = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));

        if(name != null && !name.isEmpty() && !name.equals(userToUpdate.getName())
                && name.matches(RegexHandler.getInstance().regexFirstLast)){
            userToUpdate.setName(name);
        }

        if(surname != null && !surname.isEmpty() && !surname.equals(userToUpdate.getSurname())
                && surname.matches(RegexHandler.getInstance().regexFirstLast)){
            userToUpdate.setSurname(surname);
        }

        if(username != null && !username.isEmpty() && !username.equals(userToUpdate.getUsername())){
            Optional<Users> userOptional = usersRepository.findUsersByEmail(username);
            if(userOptional.isPresent()) throw new IllegalStateException("Username already taken");
            userToUpdate.setUsername(username);
        }

        if(password != null && !password.isEmpty() && !password.equals(userToUpdate.getPassword())
                && password.matches(RegexHandler.getInstance().regexPassword)){
            userToUpdate.setPassword(password);
        }

        if(email != null && !email.isEmpty() && !email.equals(userToUpdate.getEmail())
                && email.matches(RegexHandler.getInstance().regexEmail)){
            Optional<Users> userOptional = usersRepository.findUsersByEmail(email);
            if(userOptional.isPresent()) throw new IllegalStateException("Email already taken");
            userToUpdate.setEmail(email);
        }

        if(birthday != null && !birthday.equals(userToUpdate.getBirthday()) && userToUpdate.getAge() > 18){
            userToUpdate.setBirthday(birthday);
        }
    }

    public boolean isValidUser(String username, String password) {
        Users user = usersRepository.findUsersByUsername(username).orElse(null);

        if (user != null) return password.equals(user.getPassword());
        return false;
    }

    public void getLoggedUser() {
        System.out.println("Logged user");
    }
}
