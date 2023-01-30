package com.histories.homeland.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) //Lombok no arg constructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC) //Lombok required args constructor (everything but id)
@AllArgsConstructor(access = AccessLevel.PUBLIC) //Lombok constructor to account for all args scenario
@Data //Lombok - Creates all getters and setters for class
@Entity //Specifies this class is an entity for persistence
@Table(name="person") //Persistence - name of table in database
public class Person {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Primary key
    @Column(name="id") // Name in database table
    private Long id;

    //@Column(name="address_id")
    //@NonNull //for the RequiredArgsConstructor - indicates this field is required as a parameter
    //private Long addressId;

    @Column(name="first_name")
    @NonNull //for the RequiredArgsConstructor - indicates this field is required as a parameter
    private String firstName;

    @Column(name="last_name")
    @NonNull
    private String lastName;

    @OneToOne
    @JoinColumn(name = "id")
    private Address address;
}
