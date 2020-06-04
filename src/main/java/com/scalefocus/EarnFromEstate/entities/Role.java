package com.scalefocus.EarnFromEstate.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "ROLES")
@Entity
//should i remove?
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<User> users;

}
