package it.unical.demacs.FundasticServer.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository <Users, Long>{

    @Query("SELECT user FROM Users user WHERE user.email = ?1")
    Optional<Users> findUsersByEmail(String email);

    @Query("SELECT user FROM Users user WHERE user.username = ?1")
    Optional<Users> findUsersByUsername(String username);

}
