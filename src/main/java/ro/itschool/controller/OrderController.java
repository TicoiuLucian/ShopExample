package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.entity.MyUser;
import ro.itschool.entity.Order;
import ro.itschool.repository.OrderRepository;
import ro.itschool.repository.UserRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    @GetMapping(value = "/get-all/{userId}")
    public List<Order> getAllOrders(@PathVariable Integer userId) throws UserPrincipalNotFoundException {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            return optionalUser.get().getOrders();
        else
            //TODO Custom exception
            throw new UserPrincipalNotFoundException("User not found");
    }

}
