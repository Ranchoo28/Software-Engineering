package it.unical.demacs.FundasticServer.Project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class PublishProjectRequest {
    private String title;
    private String description;
    private String category;
    private String email;
    @JsonFormat(shape= JsonFormat.Shape.ARRAY) private byte[] image;
    @JsonFormat(shape= JsonFormat.Shape.ARRAY) private byte[] video;
    private String[] members;
    private Double amount;
    private String payments_method;
    @JsonFormat(shape = JsonFormat.Shape.ARRAY) private byte[] doc_ricon;
    private LocalDate startDate;
    private LocalDate endDate;
}
