package com.scalefocus.EarnFromEstate.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "USERS")
@Entity
@Data
@Builder
@AllArgsConstructor(access= AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String matchingPassword;

    private String mobilePhone;

    //TODO: When the real dbs is plugged into the app, to find a way to persist boolean value.
    private String isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name="USER_ID", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="ROLE_ID", referencedColumnName="id"))
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "id")
    public UserAddress userAddress;

}
