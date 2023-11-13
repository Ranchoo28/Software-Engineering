package it.unical.demacs.FundasticServer.Dashboard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class DashboardRequest {
    private Long id;
    private Long userActiveNumber; // Utenti attivi
    private Long donationNumber; // Investimenti
    private Long eurAmount; // Euro raccolti
    private Long projectActiveAmount; // Progetti attivi

    public DashboardRequest() {}
}
