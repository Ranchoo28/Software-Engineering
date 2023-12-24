package it.unical.demacs.FundasticServer.Project;
import it.unical.demacs.FundasticServer.Converter.StringArrayConverter;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder
@Setter
@Getter

public class Project {

    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )

    private Long id;
    @Column(unique = true) private String title;
    private String description;
    private String category;
    @Column(unique = true)  private String email;
    private byte[]  image;
    private byte[]  video;
    @Convert(converter = StringArrayConverter.class)
    private String[] members;
    private Double amountToReach;
    private Double amountReached;
    private String paymentsMethod;
    @Convert(converter = StringArrayConverter.class)
    private String[] donators_username;
    private byte[] doc_ricon;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String title, String description, String category,  String email, byte[] image, byte[] video, String[] members, Double amountToReach, Double amountReached, String paymentsMethod, String[] donators_username, byte[] doc_ricon, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.email = email;
        this.image = image;
        this.video = video;
        this.members = members;
        this.amountToReach = amountToReach;
        this.amountReached =  amountReached;
        this.paymentsMethod = paymentsMethod;
        this.donators_username = donators_username;
        this.doc_ricon = doc_ricon;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + Arrays.toString(image) + '\'' +
                ", video=" + Arrays.toString(video) + '\'' +
                ", members=" + Arrays.toString(members) + '\'' +
                ", amount=" + amountToReach + '\'' +
                ", amount=" + amountReached + '\'' +
                ", paymentMethod= " +  paymentsMethod + '\'' +
                ",start=" + startDate + '\'' +
                ",end=" + endDate + '\'' +
                '}';
    }
}
