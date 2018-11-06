package com.ahmadfahd.repository;

import com.ahmadfahd.entity.CommentsEntity;

import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity,Long> {

    Long countByTheeventAndUseridAndCommenttimeAfter( EventsEntity EId , UsersEntity UId, LocalDateTime dateTime);
}
