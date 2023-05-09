package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Order;
import ro.itschool.entity.ShoppingCart;
import ro.itschool.repository.ShoppingCartRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public Optional<ShoppingCart> findById(Integer id) {
        return shoppingCartRepository.findById(id);
    }


    public Order convertShoppingCartToOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setShoppingCart(shoppingCart);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public ShoppingCart update(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }
}
