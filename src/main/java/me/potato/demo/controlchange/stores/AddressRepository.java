package me.potato.demo.controlchange.stores;

import me.potato.demo.controlchange.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
