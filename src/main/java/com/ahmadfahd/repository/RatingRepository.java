package com.ahmadfahd.repository;


import com.ahmadfahd.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity,Long> {
    List<RatingEntity> findByEventOrganizerId(Long oId);
//    RatingEntity findByUserIdAndEventId(Long uid,Long eid);
//    long countByUserIdAndEventId(Long uid,Long eid);
    boolean existsByUserIdAndEventId(Long uid,Long eid);
//    @Query("SELECT AVG(r.rate) from RatingEntity r where r.event.organizer.id = ?1")
//    double getAverageRate(Long id);





}

