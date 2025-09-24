//package com.spcodage.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "password_history")
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class UpdatePassword {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer passwordId;
//    @Column(nullable = false)
//    private String oldPassword;
//    @Column(nullable = false)
//    private LocalDateTime changedAt = LocalDateTime.now();
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId" , nullable = false)
//    private User user;
//
//
//
//}
