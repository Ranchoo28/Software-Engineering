package it.unical.demacs.FundasticServer.ProjectControllers;

import it.unical.demacs.FundasticServer.Dashboard.Dashboard;
import it.unical.demacs.FundasticServer.Project.Project;
import it.unical.demacs.FundasticServer.Project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/showcase")
public class ShowcaseController {
    private final ProjectService projectService;

    public ShowcaseController(ProjectService projectService){
        this.projectService = projectService;
    }
    @GetMapping
    public List<Project> findProjects() {
        return projectService.findProjects();
    }
}
