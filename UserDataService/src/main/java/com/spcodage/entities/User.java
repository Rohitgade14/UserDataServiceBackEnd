package com.spcodage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
//    @Column(unique = true)
    private String email;
    private String about;
    private String password;
    private Boolean isDeleted=false;

        @ManyToMany(fetch =FetchType.LAZY)
        @JoinTable(
                name="user_roles",
                joinColumns = @JoinColumn(name="user_id"),  //User Entity P.K
           inverseJoinColumns = @JoinColumn(name= "role_id")// Role Entity P.K
        )
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        @JsonIgnore
    private Set<Role> roles= new HashSet<>() ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_designations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "designation_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Designation> designations= new HashSet<>();



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private  Set<Permission> permissions= new HashSet<>();



}

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<UpdatePassword> passwords;
