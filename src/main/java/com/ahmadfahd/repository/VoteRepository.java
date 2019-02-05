package com.ahmadfahd.repository;

import com.ahmadfahd.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<VoteEntity,Long> {

    boolean existsByUserIdAndEventId(Long uid,Long eid);
    long countByEventId(Long id);
}
