package it.unical.demacs.FundasticServer.PatternProxy;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class CheckPermissionRequest {
    private String username;
    private String action;
    private String role;

  public CheckPermissionRequest() {}
}
