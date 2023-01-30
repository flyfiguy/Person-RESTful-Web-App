package com.histories.homeland.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) //Lombok no arg constructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC) //Lombok required args constructor (everything but id)
@AllArgsConstructor(access = AccessLevel.PUBLIC) //Lombok constructor to account for all args scenario
@Data //Lombok - Creates all getters and setters for class
@Entity //Specifies this class is an entity for persistence
@Table(name="address") //Persistence - name of table in database
public class Address {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generated
    @Column(name="id") //Name in database table
    private Long id;

    @Column(name="person_id")
    //@NonNull //for the RequiredArgsConstructor - indicates this field is required as a parameter
    private Long personId;

    @Column(name="street")
    @NonNull
    private String street;

    @Column(name="city")
    @NonNull
    private String city;

    @Column(name="state")
    @NonNull
    private String state;

    @Column(name="zip_code")
    @NonNull
    private String zipCode;

    @NonNull
    @Column(name="country")
    private String country;

    public Address(Long personId, String street, String city, String state, String zipCode, String country) {
        this.personId = personId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

}
