package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Product;
import ro.itschool.entity.ShoppingCart;
import ro.itschool.exceptions.CartNotFoundException;
import ro.itschool.exceptions.ProductNotFoundException;
import ro.itschool.repository.OrderRepository;
import ro.itschool.repository.ProductRepository;
import ro.itschool.service.ShoppingCartService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @PutMapping(value = "/add/{cartId}/{productId}")
    public ResponseEntity addProductToShoppingCart(@PathVariable Integer cartId, @PathVariable Integer productId) throws CartNotFoundException, ProductNotFoundException {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product  not found"));
        ShoppingCart cart = shoppingCartService.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart Not Found"));

        cart.addProductToShoppingCart(product);
        shoppingCartService.update(cart);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/remove/{cartId}")
    public ResponseEntity removeProductFromShoppingCart(@PathVariable Integer cartId, @RequestParam Integer productId) {

        Product product = productRepository.findById(productId).orElseThrow();
        ShoppingCart cart = shoppingCartService.findById(cartId).orElseThrow();

        cart.removeProductFromShoppingCart(product);
        shoppingCartService.update(cart);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void convertToOrder(@RequestBody ShoppingCart shoppingCart) {
        orderRepository.save(shoppingCartService.convertShoppingCartToOrder(shoppingCart));
    }

}
