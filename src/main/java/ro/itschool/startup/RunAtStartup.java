package ro.itschool.startup;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.MyRole;
import ro.itschool.entity.Product;
import ro.itschool.entity.RoleName;
import ro.itschool.repository.ProductRepository;
import ro.itschool.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class RunAtStartup {

    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        roleRepository.save(new MyRole(RoleName.ROLE_USER));
        roleRepository.save(new MyRole(RoleName.ROLE_ADMIN));

        insert10ProductsIntoDB();
    }

    private void insert10ProductsIntoDB() {
        productRepository.save(new Product("product1", 15.99f));
        productRepository.save(new Product("product2", 16.99f));
        productRepository.save(new Product("product3", 25.99f));
        productRepository.save(new Product("product4", 26.99f));
        productRepository.save(new Product("product5", 27.99f));
        productRepository.save(new Product("product6", 76.99f));
        productRepository.save(new Product("product7", 88.99f));
        productRepository.save(new Product("product8", 89.99f));
        productRepository.save(new Product("product9", 90.99f));
        productRepository.save(new Product("product10", 101.99f));
    }
}
