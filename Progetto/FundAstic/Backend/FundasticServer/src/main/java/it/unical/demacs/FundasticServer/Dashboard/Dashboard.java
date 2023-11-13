package it.unical.demacs.FundasticServer.Dashboard;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
public class Dashboard {

    @Id
    private Long id;
    private Long userActiveNumber; // Utenti attivi
    private Long donationNumber; // Investimenti
    private Long eurAmount; // Euro raccolti
    private Long projectActiveAmount; // Progetti attivi

    public Dashboard(){}
}
