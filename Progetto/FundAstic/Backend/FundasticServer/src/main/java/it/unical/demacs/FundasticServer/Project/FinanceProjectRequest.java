package it.unical.demacs.FundasticServer.Project;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class FinanceProjectRequest {
    String title;
    String username;
    Double donation_amount;
    String payment_method;
}
