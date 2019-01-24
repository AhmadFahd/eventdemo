package com.ahmadfahd.repository;


import com.ahmadfahd.entity.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<EventsEntity,Long> {
//    EventsEntity findByEventname (String eventname);

    List<EventsEntity> findAllByCity(String eventcity);
    List<EventsEntity> findByDate(LocalDate eventdate);
    Optional<EventsEntity> findByIdAndDeletedFalseAndApprovedTrue(Long eventid);
    List<EventsEntity> findByDeletedFalseAndApprovedTrueAndDateAfter(LocalDate date);
    Optional<EventsEntity> findByIdAndDeletedFalseAndApprovedTrueAndDateAfter(Long eventid,LocalDate date);
    Long countByApprovedTrue();
    long countByOrganizerId(Long id);
    //    List<EventsEntity> findByOrganizerIdAndDeletedFalseAndApprovedTrue(Long oid);
    List<EventsEntity> findByOrganizerIdAndDeletedFalseAndApprovedTrueAndDateAfter(Long oid,LocalDate date);
    List<EventsEntity> findByOrganizerIdAndDeletedFalseAndApprovedFalse(Long oid);
    List<EventsEntity> findByDeletedFalseAndApprovedFalse();



     }
