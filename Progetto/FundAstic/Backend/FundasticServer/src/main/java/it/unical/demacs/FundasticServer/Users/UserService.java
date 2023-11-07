package it.unical.demacs.FundasticServer.Users;

import it.unical.demacs.FundasticServer.Handler.RegexHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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

    public void addNewUser(Users user) {
        Optional<Users> userOptional = usersRepository.findUsersByEmail(user.getEmail());
        if(userOptional.isPresent()) throw new IllegalStateException("Email already taken");
        userOptional = usersRepository.findUsersByUsername(user.getUsername());
        if(userOptional.isPresent()) throw new IllegalStateException("Username already taken");

        usersRepository.save(user);
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
            LocalDate birthday) {

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
}
