package com.ahmadfahd.repository;

import com.ahmadfahd.entity.FollowEntity;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<FollowEntity,Long> {

    List<FollowEntity> findByUserIdAndStatusTrue(Long id);
    List<FollowEntity> findByFollowedIdAndStatusTrue(Long id);
    FollowEntity findByUserIdAndFollowedId(Long uid,Long fid);
    // TODO: 12/30/2018 add service and controller
    boolean existsByUserIdAndFollowedIdAndStatusTrue(Long uid, Long fid);
    @Query("select f.followed.id from FollowEntity f where f.user.id = ?1 and f.status = true ")
    List<Long> getAllIds(Long id);

}
