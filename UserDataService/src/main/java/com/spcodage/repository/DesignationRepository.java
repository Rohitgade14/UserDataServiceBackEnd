package com.spcodage.repository;

import com.spcodage.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Integer> {
    Optional<Designation> findFirstBydesignationName (String title);
}
