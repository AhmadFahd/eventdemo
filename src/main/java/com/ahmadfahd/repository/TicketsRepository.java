package com.ahmadfahd.repository;

import com.ahmadfahd.entity.EventsEntity;
import com.ahmadfahd.entity.TicketsEntity;
import com.ahmadfahd.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketsRepository extends JpaRepository<TicketsEntity,Long> {


    Long countByEventidAndCanceledFalse(EventsEntity byId);
    Long countByUseridAndEventid(UsersEntity byUId,EventsEntity byEId);
    Long countByUseridAndDate(UsersEntity byId, LocalDate date);
    List<TicketsEntity> findByUserid(UsersEntity byId);
    List<TicketsEntity> findByEventid(EventsEntity byId);
    TicketsEntity findByUseridAndEventid(UsersEntity byUId,EventsEntity byEId);

}
