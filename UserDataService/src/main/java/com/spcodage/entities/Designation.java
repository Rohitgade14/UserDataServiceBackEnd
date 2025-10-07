package com.spcodage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "designations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Designation {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer designationId;
    private String designationName; //HR,TL,PM

    @ManyToMany(mappedBy = "designations",fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "designations",fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "designations_permissions",
            joinColumns = @JoinColumn(name = "designation_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Permission> desiperms = new HashSet<>();




}
