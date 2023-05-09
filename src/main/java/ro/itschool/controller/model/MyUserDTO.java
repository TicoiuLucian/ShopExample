package ro.itschool.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyUserDTO {
    private String email;

    private String password;

    private String fullName;

    public MyUserDTO(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }
}
