package me.potato.demo.controlchange.stores;

import me.potato.demo.controlchange.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
