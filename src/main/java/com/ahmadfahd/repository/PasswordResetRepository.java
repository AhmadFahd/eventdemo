package com.ahmadfahd.repository;

import com.ahmadfahd.entity.PasswordResetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PasswordResetRepository extends JpaRepository<PasswordResetEntity,String> {

    boolean existsByIdAndAndTimeAfterAndDoneFalse(String id,LocalDateTime time);
}
