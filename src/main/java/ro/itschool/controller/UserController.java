package ro.itschool.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.MyRole;
import ro.itschool.entity.MyUser;
import ro.itschool.mapper.MyUserMapper;
import ro.itschool.service.UserService;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {
        return new ResponseEntity<>(userService.findAll().stream().map(MyUserMapper::fromEntity).toList(), HttpStatus.OK);
    }

    //TODO Add user
    //TODO Delete user
    //TODO Update user
    //TODO getUsersByRole

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/update-role/{id}")
    public ResponseEntity updateRole(@PathVariable Integer id, @RequestBody Set<MyRole> roles) {
        Optional<MyUser> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            optionalUser.ifPresent(user -> {
                user.setRoles(roles);
                userService.saveUser(user);
            });
            return ResponseEntity.ok().build();
        } else
            return new ResponseEntity<>("User with id" + id + " was not found", HttpStatus.BAD_REQUEST);
    }
}
