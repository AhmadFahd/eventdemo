package com.ahmadfahd.repository;

import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    List<RolesEntity> findByUserUsername(String username);
    RolesEntity findByUserAndRoleName(UsersEntity usersEntity,String role);
    List<RolesRepository> findByRoleName(String role);

}