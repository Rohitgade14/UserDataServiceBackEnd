package com.spcodage.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permission {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer permissionId;
    private  String permissionName;//READ,CREATE,UPDATE,DELETE

    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY) //user wise=READ
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<User> users= new HashSet<>();

    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY) //role-wise=ADMIN
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();


    @ManyToMany(mappedBy = "desiperms",fetch = FetchType.LAZY)  //designation-wise=HR=create,Read
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Designation> designations = new HashSet<>();






}
