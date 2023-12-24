package it.unical.demacs.FundasticServer.ProjectControllers;

import it.unical.demacs.FundasticServer.Project.FinanceProjectRequest;
import it.unical.demacs.FundasticServer.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/project/finance")
public class FinanceProjectController {
    private final ProjectService projectService;

    @Autowired
    public FinanceProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<?>> financeProject(@RequestBody FinanceProjectRequest request){
        return projectService.financeProject(request);
    }
}
