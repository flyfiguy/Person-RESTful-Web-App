package com.histories.homeland.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) //Lombok no arg constructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC) //Lombok required args constructor (everything but id)
@AllArgsConstructor(access = AccessLevel.PUBLIC) //Lombok constructor to account for all args scenario
@Data //Lombok - Creates all getters and setters for class
@Entity //Specifies this class is an entity for persistence
@Table(name="ADDRESS") //Persistence - name of table in database
public class Address {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK Generated
    @Column(name = "ID", nullable = false) //Name in database table
    private Long id;

    @Column(name = "STREET", length = 85)
    @NonNull
    private String street;

    @Column(name = "CITY", length = 85)
    @NonNull
    private String city;

    @Column(name = "STATE", length = 60)
    @NonNull
    private String state;

    @Column(name = "ZIP_CODE", length = 10)
    @NonNull
    private String zipCode;

    @Column(name = "COUNTRY", length = 60)
    @NonNull
    private String country;

    public  Address(Address address) {
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
        this.zipCode = address.getZipCode();
        this.country = address.getCountry();
    }

}
