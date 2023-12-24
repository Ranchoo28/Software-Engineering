package it.unical.demacs.FundasticServer.Project;

import it.unical.demacs.FundasticServer.Dashboard.Dashboard;
import it.unical.demacs.FundasticServer.Service.EmailService;
import it.unical.demacs.FundasticServer.Users.Users;
import it.unical.demacs.FundasticServer.Users.UsersRepository;
import jakarta.transaction.Transactional;
import org.hibernate.sql.exec.spi.JdbcOperationQueryMutationNative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UsersRepository usersRepository;
    private final EmailService emailService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UsersRepository usersRepository ,EmailService emailService){
        this.projectRepository = projectRepository;
        this.usersRepository = usersRepository;
        this.emailService = emailService;
    }

    public ResponseEntity<?> publishProject(PublishProjectRequest request) {
        Optional<Project> projectOptional = projectRepository.findProjectByTitle(request.getTitle());
        if(projectOptional.isPresent())
            return ResponseEntity.status(409).body("Project with this name already exists");
        else {
            /*
            System.out.println(
                    "PROJECT" + '\n' +
                    request.getTitle() + '\n' +
                    request.getDescription() + '\n' +
                    request.getCategory() + '\n' +
                    "Image : " + Arrays.toString(request.getImage()) + '\n' +
                    request.getVideo() + '\n' +
                            "Membri :" + request.getMembers() + '\n' +
                    request.getAmount() + '\n' +
                    "Payment: " + request.getPayments_method() + '\n' +
                    request.getDoc_ricon() + '\n' +
                    request.getStartDate() + '\n' +
                    request.getEndDate());
             */
            projectRepository.save(new Project(
                    request.getTitle(),
                    request.getDescription(),
                    request.getCategory(),
                    request.getImage(),
                    request.getVideo(),
                    request.getMembers(),
                    request.getAmount(),
                    0d,
                    request.getPayments_method(),
                    new String[]{""},
                    request.getDoc_ricon(),
                    request.getStartDate(),
                    request.getEndDate()
            ));

            return ResponseEntity.ok().body("Project published successfully!");
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> financeProject(FinanceProjectRequest request) {

        Project projectToUpdate = projectRepository.findProjectByTitle(request.title)
                .orElseThrow(() -> new IllegalStateException("Project with name  " + request.title + " does not exist"));
        Users userToUpdate = usersRepository.findUsersByUsername(request.username)
                .orElseThrow(() -> new IllegalStateException("User with name  " + request.username + " does not exist"));

        if(projectToUpdate != null && userToUpdate != null){
            System.out.println(request);
            projectToUpdate.setAmountReached(projectToUpdate.getAmountReached() + request.donation_amount);
            String[] oldDonators = projectToUpdate.getDonators_username();
            String[] newDonators = Arrays.copyOf(oldDonators, oldDonators.length + 1);
            newDonators[newDonators.length - 1] = request.getUsername();
            projectToUpdate.setDonators_username(newDonators);
            projectRepository.save(projectToUpdate);

            String[] oldProjects = userToUpdate.getDonated_projects();
            String[] newProjects = Arrays.copyOf(oldProjects, oldProjects.length + 1);
            newProjects[newProjects.length - 1] = request.getTitle();
            userToUpdate.setDonated_projects(newProjects);
            usersRepository.save(userToUpdate);
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok().body("Project financed successfully!"));
    }

    public List<Project> findProjects() {
        return projectRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> removeProject(String title) {
        try {
            projectRepository.deleteByTitle(title);
            return ResponseEntity.ok("Project removed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during project removal: " + e.getMessage());
        }
    }
}
