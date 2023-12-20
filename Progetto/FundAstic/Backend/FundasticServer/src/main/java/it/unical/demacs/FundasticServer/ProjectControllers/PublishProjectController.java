package it.unical.demacs.FundasticServer.ProjectControllers;

import it.unical.demacs.FundasticServer.Project.ProjectService;
import it.unical.demacs.FundasticServer.Project.PublishProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/project/publish")
public class PublishProjectController {
    private final ProjectService projectService;

    @Autowired
    public PublishProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<?> publishProject(@RequestBody PublishProjectRequest request){
        return projectService.publishProject(request);
    }
}
