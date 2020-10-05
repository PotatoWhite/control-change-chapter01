package me.potato.demo.controlchange.stores;

import me.potato.demo.controlchange.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
