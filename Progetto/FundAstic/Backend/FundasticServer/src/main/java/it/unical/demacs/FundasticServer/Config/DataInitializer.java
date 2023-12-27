package it.unical.demacs.FundasticServer.Config;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import java.time.LocalDate;

import it.unical.demacs.FundasticServer.Dashboard.Dashboard;
import it.unical.demacs.FundasticServer.Dashboard.DashboardRepository;
import it.unical.demacs.FundasticServer.Project.Project;
import it.unical.demacs.FundasticServer.Project.ProjectRepository;
import it.unical.demacs.FundasticServer.Users.Role;
import org.apache.commons.io.FileUtils;
import it.unical.demacs.FundasticServer.Users.Users;
import it.unical.demacs.FundasticServer.Users.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final ProjectRepository projectRepository;
    private final DashboardRepository dashboardRepository;

    @Autowired
    public DataInitializer(UsersRepository usersRepository, ProjectRepository projectRepository, DashboardRepository dashboardRepository) {
        this.usersRepository = usersRepository;
        this.projectRepository = projectRepository;
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public void run(String... args) {
        if(usersRepository.count() == 0) createUsers();
        if(projectRepository.count() == 0) createProjects();
        if(dashboardRepository.count() == 0) createDashboard();
    }

    private void createUsers() {
        usersRepository.save(new Users(
                "Manuel Alejandro",
                "Borroto Santana",
                "Manuel",
                "admin",
                "manuel@gmail.com",
                LocalDate.of(1998, 8, 8),
                254545435,
                Role.Moderatore,
                new String[]{}
        ));
        usersRepository.save(new Users(
                "Francesco",
                "Ricca",
                "Franc",
                "prova",
                "franc@gmail.com",
                LocalDate.of(1998, 8, 8),
                654645675,
                Role.Publisher,
                new String[]{}
        ));
        usersRepository.save(new Users(
                "Saverio",
                "Crea",
                "Sav",
                "prova",
                "sav@gmail.com",
                LocalDate.of(2003, 2, 28),
                567563,
                Role.Utente,
                new String[]{}
        ));
    }

    private void createProjects() {
        projectRepository.save(new Project(
                "FitWell Pro",
                "FitWell Pro è l'app di fitness che trasforma il tuo stile di vita. Con programmi personalizzati, monitoraggio delle attività e motivazione, è progettata per adattarsi alle tue esigenze.\n" +
                        "\n" +
                        "        Allenamenti Personalizzati:\n" +
                        "        Programmi adattabili a obiettivi come perdita di peso o guadagno muscolare, rendendo ogni allenamento efficace e su misura.\n" +
                        "\n" +
                        "        Monitoraggio Completo:\n" +
                        "        Traccia passi, distanza, calorie e tempi di allenamento. I grafici visualizzano il tuo progresso, fornendo una panoramica chiara.\n" +
                        "\n" +
                        "                Guida Audio e Video:\n" +
                        "        Istruzioni dettagliate tramite guide audio/video. Scegli tra vari istruttori virtuali per mantenere varietà e motivazione.\n" +
                        "\n" +
                        "                Tracciamento Nutrizionale:\n" +
                        "        Registra la tua dieta e ricevi suggerimenti personalizzati per mantenere l'equilibrio tra alimentazione ed esercizio.\n" +
                        "\n" +
                        "        Comunità e Condivisione:\n" +
                        "        Collegati con altri utenti, condividi progressi e partecipa a sfide. La condivisione su social media amplifica la motivazione.\n" +
                        "\n" +
                        "        Notifiche e Promemoria:\n" +
                        "        Ricevi notifiche per gli allenamenti e promemoria per il monitoraggio delle attività, mantenendo costante l'impegno.\n" +
                        "\n" +
                        "        Monitoraggio del Sonno:\n" +
                        "        Valuta la qualità del sonno per migliorare il recupero e l'energia quotidiana.\n" +
                        "\n" +
                        "        Obiettivi e Premi:\n" +
                        "        Imposta obiettivi personalizzati e guadagna premi virtuali per ogni traguardo raggiunto, mantenendo alta la motivazione.\n" +
                        "\n" +
                        "                FitWell Pro è il compagno di allenamento definitivo, unendo tecnologia avanzata a un'esperienza utente focalizzata sui risultati. Trasforma la tua vita con FitWell Pro, inizia oggi.",
                "Fitness",
                "fitwellpro@gmail.com",
                convertImageToByteArray("src/main/resources/fitwellpro.jpg"),
                new byte[]{},
                new String[]{"Saverio Crea, Manuel Alejandro Borroto Santana, Francesco Ricca"},
                15000d,
                0d,
                "PayPal: fitwellpro@gmail.com",
                new String[]{},
                new byte[]{},
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31)
        ));
    }

    private void createDashboard() {
        dashboardRepository.save(new Dashboard(
                1L,
                100L,
                50L,
                5000L,
                10L
        ));
    }

    private byte[] convertImageToByteArray(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            return FileUtils.readFileToByteArray(imageFile);
        } catch (Exception ignored) {}

        return new byte[]{};
    }
}
