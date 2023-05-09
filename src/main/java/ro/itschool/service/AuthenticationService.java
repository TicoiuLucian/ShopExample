package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.itschool.controller.model.AuthenticationRequest;
import ro.itschool.controller.model.AuthenticationResponse;
import ro.itschool.controller.model.MyUserDTO;
import ro.itschool.entity.MyRole;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.RoleName;
import ro.itschool.entity.ShoppingCart;
import ro.itschool.repository.RoleRepository;
import ro.itschool.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public void register(MyUserDTO request) {

        MyRole userRole = roleRepository.findByName(RoleName.ROLE_USER);
        var user = MyUser.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(userRole))
                .cart(new ShoppingCart())
                .build();
        repository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
