package it.unical.demacs.FundasticServer.ProjectControllers;

import it.unical.demacs.FundasticServer.PatternProxy.CheckPermissionRequest;
import it.unical.demacs.FundasticServer.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/project/delete")
public class DeleteProjectController {
    private final ProjectService projectService;

    @Autowired
    public DeleteProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<?> deleteProject(@RequestBody CheckPermissionRequest request){
       return projectService.removeProject(request.getTitle());
    }
}
