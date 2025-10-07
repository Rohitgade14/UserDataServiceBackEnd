package com.spcodage.config.start;


import com.spcodage.entities.Designation;
import com.spcodage.entities.Permission;
import com.spcodage.entities.Role;
import com.spcodage.entities.User;
import com.spcodage.repository.DesignationRepository;
import com.spcodage.repository.PermissionRepository;
import com.spcodage.repository.RoleRepository;
import com.spcodage.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final DesignationRepository designationRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Permission read = permissionRepository.findFirstByPermissionName("READ")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "READ", null, null, null)));
        Permission create = permissionRepository.findFirstByPermissionName("CREATE")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "CREATE", null, null, null)));
        Permission update = permissionRepository.findFirstByPermissionName("UPDATE")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "UPDATE", null, null, null)));
        Permission delete = permissionRepository.findFirstByPermissionName("DELETE")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "DELETE", null, null, null)));

        log.info("permission ensured successfully");

        Role admin = roleRepository.findFirstByRoleName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN", Set.of(), Set.of(), Set.of(read, create, update, delete))));

         log.info("Role  Added Successfully");

        Designation hr = designationRepository.findFirstBydesignationName("HR")
                .orElseGet(() -> {
                    Designation designation = new Designation();
                    designation.setDesignationName("HR");
                    designation.getDesiperms().addAll(Set.of(create,read));
                    return designationRepository.save(designation);
                });

        Designation tl = designationRepository.findFirstBydesignationName("TEAM LEAD")
                .orElseGet(() -> {
                    Designation designation = new Designation();
                    designation.setDesignationName("TEAM LEAD");
                    designation.getDesiperms().addAll(Set.of(update,read));
                    return designationRepository.save(designation);
                });

        Designation pm = designationRepository.findFirstBydesignationName("PROJECT MANAGER")
                .orElseGet(() -> {
                    Designation designation = new Designation();
                    designation.setDesignationName("PROJECT MANAGER");
                    designation.getDesiperms().addAll(Set.of(read,delete));
                    return designationRepository.save(designation);
                });

        log.info("Designation Added Successfully");

        if(!userRepository.existsByEmail("rohit14gade@gmail.com")){
            User userAdmin  = new User();
             userAdmin.setName("Rohit Gade");
             userAdmin.setEmail("rohit14gade@gmail.com");
             userAdmin.setAbout("Java Full Stack Dev");
             userAdmin.setPassword(passwordEncoder.encode("Rohit@123"));
             userAdmin.setRoles(Set.of(admin));
             userAdmin.setPermissions(Set.of(create,read,update,delete));
             userAdmin.setDesignations(Set.of(hr,tl,pm));
            User savedAdmin = userRepository.save(userAdmin);
            log.info("User ADMIN ADDED SUCCESSFULLY: {} {}",savedAdmin,savedAdmin.getRoles());

            log.info("Default data loaded successfully!");

        }
    }
}

//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        // ---------------- Permissions ----------------
//        Permission read = permRepo.findFirstByPermissionName("READ").orElseGet(() -> permRepo.save(new Permission(null, "READ", null, null, null)));
//        Permission create = permRepo.findFirstByPermissionName("CREATE").orElseGet(() -> permRepo.save(new Permission(null, "CREATE", null, null, null)));
//        Permission update = permRepo.findFirstByPermissionName("UPDATE").orElseGet(() -> permRepo.save(new Permission(null, "UPDATE", null, null, null)));
//        Permission delete = permRepo.findFirstByPermissionName("DELETE").orElseGet(() -> permRepo.save(new Permission(null, "DELETE", null, null, null)));
//
//        log.info("Permissions ensured successfully!");
//
//        // ---------------- Roles ----------------
//        Role adminRole = roleRepo.findFirstByRoleName("ADMIN")
//                .orElseGet(() -> roleRepo.save(new Role(null, "ADMIN", null, Set.of(read, create, update, delete))));
////        Role userRole = roleRepo.findFirstByRoleName("USER")
////                .orElseGet(() -> roleRepo.save(new Role(null, "USER", null, Set.of(read))));
//
//        log.info("Roles ensured successfully!");
//
//        // ---------------- Designations ----------------
//        Designation hr = desRepo.findFirstBydesignationName("HR").orElseGet(() -> desRepo.save(new Designation(null, "HR", null, Set.of(read, create))));
//        Designation pm = desRepo.findFirstBydesignationName("Project Manager").orElseGet(() -> desRepo.save(new Designation(null, "Project Manager", null, Set.of(read, update))));
//        Designation tl = desRepo.findFirstBydesignationName("Team Lead").orElseGet(() -> desRepo.save(new Designation(null, "Team Lead", null, Set.of(read, update, delete))));
//
//        log.info("Designations ensured successfully!");
//
//        // ---------------- Admin User ----------------
//        if (!userRepo.existsByEmail("rohitgade@gmail.com")) {
//            User admin1 = new User();
//            admin1.setName("Rohit Gade");
//            admin1.setEmail("rohitgade@gmail.com");
//            admin1.setAbout("Full Stack Dev");
//            admin1.setPassword(passwordEncoder.encode("Rohit@123"));
//            admin1.setRoles(Set.of(adminRole));
//            admin1.setDesignations(Set.of(hr, pm, tl));
//            admin1.setPermissions(Set.of(read, create, update, delete));
//            admin1.setIsDeleted(false);
//            userRepo.save(admin1);
//            log.info("Admin user created successfully!");
//        }
//
//        // ---------------- Normal User ----------------
////        if (!userRepo.existsByEmail("mukeshpatil@gmail.com")) {
////            User user1 = new User();
////            user1.setName("Mukesh Patil");
////            user1.setEmail("mukeshpatil@gmail.com");
////            user1.setAbout("Java Developer");
////            user1.setPassword(passwordEncoder.encode("Mukesh@123"));
////            user1.setRoles(Set.of(userRole));
////            user1.setDesignations(Set.of(hr));
////            user1.setPermissions(Set.of(read));
////            user1.setIsDeleted(false);
////            userRepo.save(user1);
////            log.info("Normal user created successfully!");
////        }

//    }



