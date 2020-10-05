package me.potato.demo.controlchange.domain;

import me.potato.demo.controlchange.stores.AddressRepository;
import me.potato.demo.controlchange.stores.ContactRepository;
import me.potato.demo.controlchange.stores.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yml")
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ContactRepository contactRepository;


    @Test
    public void crud() {
        var aUser = new User();
        aUser.setAccount("test");
        aUser.setName("potato");

        var aAddress = new Address();
        aAddress.setPostCode("1234");
        aAddress.setCity("seoul");
//        aAddress.setUser(aUser);

        var aContact = new Contact();
        aContact.setType("home");
        aContact.setDetail("andromeda");
//        aContact.setUser(aUser);

        aUser.addAddress(aAddress);
        aUser.addContact(aContact);

        userRepository.save(aUser);

        List<User> allUsers = userRepository.findAll();

        allUsers.stream().forEach(System.out::println);
        allUsers.stream().forEach(item -> assertEquals(1, item.getAddresses().size()));
        allUsers.stream().forEach(item -> assertEquals(1, item.getContacts().size()));
    }
}