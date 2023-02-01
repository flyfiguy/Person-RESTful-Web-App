package com.histories.homeland.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) //Lombok no arg constructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC) //Lombok required args constructor (all fields with @NonNull)
@AllArgsConstructor(access = AccessLevel.PUBLIC) //Lombok constructor to account for all args scenario
@Data //Lombok - Creates all getters and setters for class
@Entity //Specifies this class is an entity for persistence
@Table(name="PERSON") //Persistence - name of table in database
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", length = 20)
    @NonNull //for the RequiredArgsConstructor - indicates this field is required as a parameter
    private String firstName;

    @Column(name = "LAST_NAME", length = 20)
    @NonNull
    private String lastName;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL /*, optional = false*/)
    @JoinColumn(name = "ADDRESS_ID" /*, nullable = false*/)
    private Address address;

    public Person(String fname, String lname, Address address) {
        this.firstName = fname;
        this.lastName = lname;
        this.address = address;
    }
}
