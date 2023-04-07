package com.smithexperience.springbootrestfulwebservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lombok's library of annotations help Spring automatically create boilerplate code like
 * getters, setters, no-argument constructor, constructor with all arguments, and even toString method
 *
 * JPA (Java Persistence API): used to persistently store vast amounts of data into a database
 * @Entity annotation is used to make a class a JPA Entity
 * @Column
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
}
