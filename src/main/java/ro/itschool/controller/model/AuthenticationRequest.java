package ro.itschool.controller.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;
}
