package it.unical.demacs.FundasticServer.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Questa interfaccia è un Repository, ovvero un'interfaccia che permette di interagire con il database
@Repository
public interface UsersRepository extends JpaRepository <Users, Long>{ // Specificare tabella e tipo di chiave primaria


}
