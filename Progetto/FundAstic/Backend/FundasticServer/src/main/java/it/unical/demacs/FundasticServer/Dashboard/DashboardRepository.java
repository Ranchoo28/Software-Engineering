package it.unical.demacs.FundasticServer.Dashboard;

import it.unical.demacs.FundasticServer.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    @Query("SELECT data from Dashboard data WHERE data.id = ?1")
    List<Dashboard> findData(Long id);
}
