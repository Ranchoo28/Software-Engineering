package it.unical.demacs.FundasticServer.Project;


import it.unical.demacs.FundasticServer.Converter.StringArrayConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Builder

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
    private byte[]  image;
    private byte[]  video;
    @Convert(converter = StringArrayConverter.class)
    private String[] members;
    private Double amount;
    private String paymentsMethod;
    private byte[] doc_ricon;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String title, String description, String category, byte[] image, byte[] video, String[] members, Double amount, String paymentsMethod, byte[] doc_ricon, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
        this.video = video;
        this.members = members;
        this.amount = amount;
        this.paymentsMethod = paymentsMethod;
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
                ", image='" + image + '\'' +
                ", video=" + video + '\'' +
                ", members=" + members + '\'' +
                ", amount=" + amount + '\'' +
                ", paymentMethod= " +  paymentsMethod + '\'' +
                ",start=" + startDate + '\'' +
                ",end=" + endDate + '\'' +
                '}';
    }
}
