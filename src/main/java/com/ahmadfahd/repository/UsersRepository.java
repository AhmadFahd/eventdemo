package com.ahmadfahd.repository;

import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    List<UsersEntity> findByEnabledIsTrue();
    List<UsersEntity> findByEnabledFalse();
    UsersEntity findByEmailAndFirstname(String email , String fn);

    // select * from users where enable = true
    UsersEntity findByEmail(String uname);
    UsersEntity findByUsername(String uname);
    UsersEntity findByUsernameAndEnabledTrue(String uname);

    Optional<UsersEntity> findByIdAndEnabledTrue(Long id);
//    List<UsersEntity> findAllByRolesRoleNameEquals(String role);
    List<UsersEntity> findByRolesRoleNameNotContains(String role);
    List<UsersEntity> findByIdIn(List<Long> ids);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndEnabledTrue(String username);
    boolean existsByEmailAndEnabledTrue(String email);


}