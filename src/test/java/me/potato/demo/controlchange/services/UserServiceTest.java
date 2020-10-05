package me.potato.demo.controlchange.services;

import me.potato.demo.controlchange.domain.Address;
import me.potato.demo.controlchange.domain.Contact;
import me.potato.demo.controlchange.domain.User;
import me.potato.demo.controlchange.stores.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yml")
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(false)
    public void crud() throws Exception {
        var user = userService.createUser("testAccount", "testName");
        assertEquals("testAccount", user.getAccount());
        assertEquals("testName", user.getName());

        var contact1 = new Contact();
        contact1.setType("home");
        contact1.setDetail("myHome");

        var contact2 = new Contact();
        contact2.setType("company");
        contact2.setDetail("myCompany");

        Set<Contact> contacts = new HashSet<>();
        contacts.add(contact1);
        contacts.add(contact2);

        User updatedUser1 = userService.updateUser(user.getId(), contacts, Collections.emptySet());
        assertEquals(2, updatedUser1.getContacts().size());

        var address1 = new Address();
        address1.setCity("seoul");
        address1.setPostCode("1234");

        var address2 = new Address();
        address2.setCity("sungnam");
        address2.setPostCode("1234");

        Set<Address> addresses = new HashSet<>();
        addresses.add(address1);
        addresses.add(address2);

        userRepository.findAll();

        User updatedUser2 = userService.updateUser(user.getId(), contacts, addresses);
        assertEquals(2, updatedUser2.getContacts().size());
        assertEquals(2, updatedUser2.getAddresses().size());

        Set<Contact> company = userService.getContactByType(user.getId(), "company");
        assertEquals(1, company.size());

        Set<Contact> hotel = userService.getContactByType(user.getId(), "hotel");
        assertEquals(0, hotel.size());


        Set<Address> seoul = userService.getAddressByCity(user.getId(), "seoul");
        assertEquals(1, seoul.size());

        Set<Address> busan = userService.getAddressByCity(user.getId(), "busan");
        assertEquals(0, busan.size());
    }

}