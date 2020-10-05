package me.potato.demo.controlchange.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String account;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Address> addresses = new HashSet<>();

    public void addAddress(Address newAddress) {
        addresses.add(newAddress);
    }

    public void addContact(Contact newContact) {
        contacts.add(newContact);
    }
}
