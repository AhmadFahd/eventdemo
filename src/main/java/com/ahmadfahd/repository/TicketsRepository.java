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


    Long countByEventIdAndCanceledFalse(Long id);
    boolean existsByUserIdAndEventDate(Long Id, LocalDate date);
    Long countByUserIdAndEventDate(Long Id, LocalDate date);
    List<TicketsEntity> findByUserIdAndChickedFalseAndCanceledFalse(Long Id);
    List<TicketsEntity> findByUserIdAndCanceledFalse(Long Id);
    List<TicketsEntity> findByEventId(Long byId);
    List<TicketsEntity> findByEventIdAndCanceledFalse(Long byId);
    TicketsEntity findByUserIdAndEventId(Long UId,Long EId);
    List<TicketsEntity> findByUserIdAndChickedTrue(Long uid);
    long countByUserIdAndEventIdAndChickedTrue(Long uId,Long eId);
    boolean existsByUserIdAndEventIdAndChickedTrue(Long uId,Long eId);
    List<TicketsEntity> findByUserIdIn(List<Long> ids);

}
