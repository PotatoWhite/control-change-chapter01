package me.potato.demo.controlchange.services;

import me.potato.demo.controlchange.domain.Address;
import me.potato.demo.controlchange.domain.Contact;
import me.potato.demo.controlchange.domain.User;
import me.potato.demo.controlchange.stores.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String account, String name) {
        var newUser = new User();
        newUser.setAccount(account);
        newUser.setName(name);
        return userRepository.save(newUser);
    }

    private User findById(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("User does not exist"));
    }

    public User updateUser(Long userId, Set<Contact> contacts, Set<Address> addresses) throws Exception {
        User aUser = findById(userId);

        aUser.setContacts(contacts);
        aUser.setAddresses(addresses);

        return userRepository.save(aUser);
    }

    public Set<Contact> getContactByType(Long userId, String type) throws Exception {
        Set<Contact> contacts = findById(userId).getContacts();
        return contacts.stream()
                .filter(contact -> contact.getType().equals(type))
                .collect(Collectors.toSet());
    }

    public Set<Address> getAddressByCity(Long userId, String city) throws Exception {
        Set<Address> addresses = findById(userId).getAddresses();
        return addresses.stream()
                .filter(address -> address.getCity().equals(city))
                .collect(Collectors.toSet());
    }

}
