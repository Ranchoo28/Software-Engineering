package it.unical.demacs.FundasticServer.Project;

import it.unical.demacs.FundasticServer.Converter.ListByteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ListByteConverter listByteConverter;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ListByteConverter listByteConverter){
        this.projectRepository = projectRepository;
        this.listByteConverter = listByteConverter;
    }

    public ResponseEntity<?> publishProject(PublishProjectRequest request) {
        Optional<Project> projectOptional = projectRepository.findProjectByTitle(request.getTitle());
        if(projectOptional.isPresent()) {
            return ResponseEntity.status(409).body("Project with this name already exists");
        } else {

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

            System.out.println(Arrays.toString(request.getVideo()));


            projectRepository.save(new Project(
                    request.getTitle(),
                    request.getDescription(),
                    request.getCategory(),
                    request.getImage(),
                    request.getVideo(),
                    request.getMembers(),
                    request.getAmount(),
                    request.getPayments_method(),
                    request.getDoc_ricon(),
                    request.getStartDate(),
                    request.getEndDate()
            ));
            return ResponseEntity.ok().body("Project published successfully!");
        }
    }
}
