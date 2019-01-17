package com.ahmadfahd.repository;

import com.ahmadfahd.entity.RoleRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRequestRepository extends JpaRepository<RoleRequestEntity,Long> {

    List<RoleRequestEntity> findAllByAcceptedFalseAndRole(String role);
    List<RoleRequestEntity> findAllByAcceptedFalse();
}
