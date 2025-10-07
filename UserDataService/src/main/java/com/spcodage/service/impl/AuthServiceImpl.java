package com.spcodage.service.impl;

import com.spcodage.config.CustomUserDetails;
import com.spcodage.config.JwtService;
import com.spcodage.dtos.*;
import com.spcodage.entities.Designation;
import com.spcodage.entities.Permission;
import com.spcodage.entities.Role;
import com.spcodage.entities.User;
import com.spcodage.exceptions.ResourceAlreadyExistsException;
import com.spcodage.mappers.DesignationMapper;
import com.spcodage.mappers.PermissionMapper;
import com.spcodage.mappers.RoleMapper;
import com.spcodage.mappers.UserMapper;
import com.spcodage.repository.DesignationRepository;
import com.spcodage.repository.PermissionRepository;
import com.spcodage.repository.RoleRepository;
import com.spcodage.repository.UserRepository;
import com.spcodage.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private  final RoleMapper roleMapper;
    private  final DesignationMapper  designationMapper;
    private  final PermissionMapper permissionMapper;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private  final DesignationRepository designationRepository;
    private  final PermissionRepository permissionRepository;

    @Override

    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ResourceAlreadyExistsException("User Already Present in DB: " + userDto.getEmail());
        }
        String encodePassword = passwordEncoder.encode(userDto.getPassword());

        Role userRole = roleRepository.findFirstByRoleName("USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "USER", new HashSet<>(),new HashSet<>(),new HashSet<>())));

        Designation userDesignation = designationRepository.findFirstBydesignationName("Developer").
                orElseGet(() -> designationRepository.save((new Designation(null, "Developer", new HashSet<>(), new HashSet<>(),new HashSet<>()))));

        Permission userPermission = permissionRepository.findFirstByPermissionName("Read")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "Read", new HashSet<>(), new HashSet<>(), new HashSet<>())));


              userRole.getDesignations().add(userDesignation); //  Role-> Designation
              userDesignation.getRoles().add(userRole);// Designation-> Role  (bidirectional)

//               userRole.getPermissions().add(userPermission);
//               userPermission.getPermission



              //userRole.getPermissions().add(permissionUser);




             // 3. Add designation with permission
            //  designationUser.getDesignationPerm().add(permissionUser);

            //  designationRepository.save(designationUser);
            //  roleRepository.save(userRole);

        UserDto userdto = UserDto.builder()
                .name(userDto.getName())
                .password(encodePassword)
                .email(userDto.getEmail())
                .about(userDto.getAbout())
                // .roles(Set.of(userRoleDto))
               // .roles(Set.of(RoleDto.builder().roleName(userRole.getRoleName()).build()))
                .roles(Set.of(roleMapper.toRoleDto(userRole)))
                .designations(Set.of(designationMapper.toDesignationDto(userDesignation)))
                .permissions(Set.of(permissionMapper.toPermissionDto(userPermission)))
               // .permissions(Set.of(PermissionDto.builder().permissionName(permissionUser.getPermissionName()).build()))
                .build();

        User user = userMapper.toUserEntity(userdto);
        user.setIsDeleted(false);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }


      @Transactional(readOnly = true)
    public AuthRes loginReq(AuthReq authReq) {
        try {
            log.info("Login Req By Email: {}", authReq.getEmail());
            log.info("Login password: {}",authReq.getPassword());
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authReq.getEmail(),
                            authReq.getPassword()
                    )
            );
            log.info("Authentication is Successful email: {}", authReq.getEmail());
            User user = userRepository.findByEmail(authReq.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "User not found after authentication: " + authReq.getEmail()));
            log.info("Fetched user for JWT generation: {}", user.getEmail());

            CustomUserDetails customUser = new CustomUserDetails(user);
            String accessToken = jwtService.generateToken(customUser, true);
            String refreshToken = jwtService.generateToken(customUser, false);

            log.info("Generated access and refresh tokens for: {}", user.getEmail());
            UserDto userDto = userMapper.toUserDto(user);

            return AuthRes.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .user(userDto)
                    .build();
        } catch (BadCredentialsException ex) {
            log.error("Invalid credentials for email: {}", authReq.getEmail(), ex);
            throw new RuntimeException("Invalid email or password");
        } catch (UsernameNotFoundException ex) {
            log.error("User not found: {}", authReq.getEmail(), ex);
            throw new RuntimeException("User not found: " + authReq.getEmail());
        } catch (Exception ex) {
            log.error("Login failed for email: {}", authReq.getEmail(), ex);
            throw new RuntimeException("Login failed: " + ex.getMessage());
        }

    }


}


