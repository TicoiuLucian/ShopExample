package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

  Optional<MyUser> findByEmail(String email);

}
