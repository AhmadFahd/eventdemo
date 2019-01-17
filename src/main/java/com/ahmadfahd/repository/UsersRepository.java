package com.ahmadfahd.repository;

import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    List<UsersEntity> findByEnabledIsTrue();
    // select * from users where enable = true

    UsersEntity findByUsername(String uname);
//    List<UsersEntity> findAllByRolesRoleNameEquals(String role);
    List<UsersEntity> findByRolesRoleNameNotContains(String role);
    List<UsersEntity> findByIdIn(List<Long> ids);
    boolean existsByUsername(String username);

}