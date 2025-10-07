package com.spcodage.repository;

import com.spcodage.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //deleted all users not shown
    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.roles " +
            "LEFT JOIN FETCH u.designations " +
            "LEFT JOIN FETCH u.permissions " +
            "WHERE u.isDeleted = false")
    List<User> findByIsDeletedFalse();

    // for single User is Deleted not shown
    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.roles " +
            "LEFT JOIN FETCH u.designations " +
            "LEFT JOIN FETCH u.permissions " +
            "WHERE u.userId = :userId AND u.isDeleted = false")
    Optional<User> findByUserIdAndIsDeletedFalse(Integer userId);

    // for login created custom method findByEmail
    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.roles " +
            "LEFT JOIN FETCH u.permissions " +
            "WHERE u.email = :email")
   Optional<User> findByEmail(@Param("email") String email);
    Boolean existsByEmail(String email);

}
