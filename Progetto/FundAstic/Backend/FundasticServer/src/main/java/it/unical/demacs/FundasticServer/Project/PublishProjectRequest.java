package it.unical.demacs.FundasticServer.Project;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class ProjectRequest {
    private String title;
    private String description;
    private String category;
    private String image;
    private String video;
    private String members;
    private Double amount;
    private LocalDate startDate;
    private LocalDate endDate;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

    public String getMembers() {
        return members;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}
