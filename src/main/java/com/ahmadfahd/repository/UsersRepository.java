package com.ahmadfahd.repository;

import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    List<UsersEntity> findByDeletedIsFalse();
    UsersEntity findByUsername(String uname);



}