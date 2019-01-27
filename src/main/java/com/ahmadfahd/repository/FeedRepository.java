package com.ahmadfahd.repository;

import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedRepository extends JpaRepository<FeedEntity,Long> {

    List<FeedEntity> findByUserId(Long id);
    List<FeedEntity> findByUserIdIn(List<Long> ids);
    boolean existsByTargetEventIdAndAction(long id, String action);
}
