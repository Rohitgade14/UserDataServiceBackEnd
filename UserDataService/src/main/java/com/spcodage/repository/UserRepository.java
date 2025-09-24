package com.spcodage.repository;

import com.spcodage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //deleted all users not shown
    List<User> findByIsDeletedFalse();

    // for single User is Delted not shown
    Optional<User> findByUserIdAndIsDeletedFalse(Integer userId);
    // for login created custom method findByEmail
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

}
