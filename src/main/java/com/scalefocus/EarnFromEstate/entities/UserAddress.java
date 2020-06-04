package com.scalefocus.EarnFromEstate.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "USER_ADDRESS")
@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserAddress {

    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private int postCode;

    private String city;

    private String street;

    private String streetNumber;
}
