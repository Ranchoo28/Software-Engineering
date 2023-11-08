package it.unical.demacs.FundasticServer.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Questa interfaccia è un Repository, ovvero un'interfaccia che permette di interagire con il database
@Repository
public interface UsersRepository extends JpaRepository <Users, Long>{ // Specificare tabella e tipo di chiave primaria

    @Query("SELECT user FROM Users user WHERE user.email = ?1")
    Optional<Users> findUsersByEmail(String email);

    @Query("SELECT user FROM Users user WHERE user.username = ?1")
    Optional<Users> findUsersByUsername(String username);

    @Query("SELECT user FROM Users user WHERE user.username=?1 and user.password=?2")
    void loginUserByUsername(String username, String password);
}
