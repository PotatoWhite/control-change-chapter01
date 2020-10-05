package me.potato.demo.controlchange.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;

    private String postCode;
    private String city;
}
