package it.unical.demacs.FundasticServer.Project;

import it.unical.demacs.FundasticServer.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT project FROM Project project WHERE project.title = ?1")
    Optional<Project> findProjectByTitle(String title);

}
