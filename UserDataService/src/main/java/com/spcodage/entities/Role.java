package com.spcodage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer roleId;
    private String roleName;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<User> users=new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "roles_designations",
            joinColumns =@JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name ="designation_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Designation> designations= new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns =@JoinColumn(name ="permission_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private  Set<Permission> permissions=new HashSet<>();






}
