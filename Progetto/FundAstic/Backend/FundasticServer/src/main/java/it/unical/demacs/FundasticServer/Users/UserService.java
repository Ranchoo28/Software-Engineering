package it.unical.demacs.FundasticServer.Users;

import it.unical.demacs.FundasticServer.Service.EmailService;
import it.unical.demacs.FundasticServer.Users.Jwt.JwtAuthResponse;
import it.unical.demacs.FundasticServer.Users.Jwt.JwtService;
import it.unical.demacs.FundasticServer.Users.Login.LoginRequest;
import it.unical.demacs.FundasticServer.Users.Registration.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    //private final SessionRepository sessionRepository;

    @Autowired
    public UserService(UsersRepository usersRepository, JwtService jwtService, EmailService emailService) {
        this.usersRepository = usersRepository;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public List<Users> getUsers(){ return usersRepository.findAll(); }

    public ResponseEntity<?> loginUser(LoginRequest request){
        Optional<Users> userOptional = usersRepository.findUsersByUsername(request.getUsername());
        if(userOptional.isEmpty()) return ResponseEntity.status(402).body("Username incorrect");
        else
            if(!userOptional.get().getPassword().equals(request.getPassword()))
                return  ResponseEntity.status(402).body("Password incorrect");
            else{
                return ResponseEntity.ok(new JwtAuthResponse(
                        jwtService.generateToken(
                        request.getUsername(),
                        userOptional.get().getRole().toString())));
            }
    }


    public ResponseEntity<?> addNewUser(RegistrationRequest request) {
        Optional<Users> userOptional = usersRepository.findUsersByEmail(request.getEmail());
        if(userOptional.isPresent()) return ResponseEntity.status(402).body("Account with this email already exist.");
        userOptional = usersRepository.findUsersByUsername(request.getUsername());
        if(userOptional.isPresent()) return ResponseEntity.status(402).body("Account with this username already exist.");

        if(Period.between(request.getBirthday(), LocalDate.now()).getYears() < 18)
            return ResponseEntity.status(402).body("You must be of legal age.");

        Role role = switch (request.getRole()) {
            case "Finanziatore" -> Role.Finanziatore;
            case "Publisher" -> Role.Publisher;
            default -> Role.Utente;
        };

        usersRepository.save(new Users(
                request.getName(),
                request.getSurname(),
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getBirthday(),
                request.getNumber(),
                role,
                new String[]{""}
        ));
        System.out.println(request.getBirthday());
        /*
        emailService.sendEmail(
                request.getEmail(),
                "Benvenuto in FundAstic!" ,
                request.getName() + " grazie per esserti registrato!"
                );

         */
        return ResponseEntity.status(200).body("Account registered!");
    }

    public void deleteUser(Long id) {
       if(usersRepository.existsById(id)) usersRepository.deleteById(id);
         else throw new IllegalStateException("User with id " + id + " does not exist");
    }

    /*
    @Transactional
    public void updateUser(
            Long userId,
            String name,
            String surname,
            String username,
            String password,
            String email,
            String birthday,
            Role role) {

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

        if(role != null && !role.equals(userToUpdate.getRole())){
            userToUpdate.setRole(role);
        }
    }

    public boolean isValidUser(String username, String password) {
        Users user = usersRepository.findUsersByUsername(username).orElse(null);

        if (user != null) return password.equals(user.getPassword());
        return false;
    }

     */
}
