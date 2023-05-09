package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.itschool.controller.model.AuthenticationRequest;
import ro.itschool.controller.model.AuthenticationResponse;
import ro.itschool.controller.model.MyUserDTO;
import ro.itschool.service.AuthenticationService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return service.authenticate(authenticationRequest);
    }

    @PostMapping("/register")
    public void register(@RequestBody MyUserDTO registerRequest) {
        service.register(registerRequest);
    }
}
