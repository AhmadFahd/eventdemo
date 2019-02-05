package com.ahmadfahd.repository;


import com.ahmadfahd.entity.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<EventsEntity,Long> {

    Optional<EventsEntity> findByIdAndDeletedFalse(Long eventid);
    List<EventsEntity> findByDeletedFalseAndApprovedTrueAndDateAfterAndSurveyFalse(LocalDate date);
    Optional<EventsEntity> findByIdAndDeletedFalseAndApprovedTrueAndDateAfterAndSurveyFalse(Long eventid, LocalDate date);
    List<EventsEntity> findByOrganizerIdAndDeletedFalseAndApprovedTrueAndDateAfterAndSurveyFalse(Long oid, LocalDate date);
    List<EventsEntity> findByOrganizerIdAndDeletedFalseAndApprovedFalseAndSurveyFalse(Long oid);
    List<EventsEntity> findByDeletedFalseAndApprovedFalseAndSurveyFalse();
    //same but survey
    Optional<EventsEntity> findByIdAndDeletedFalseAndSurveyTrue(Long eventid);
    List<EventsEntity> findByDeletedFalseAndSurveyTrueAndDateAfter(LocalDate date);
    Optional<EventsEntity> findByIdAndDeletedFalseAndSurveyTrueAndDateAfter(Long eventid, LocalDate date);
    List<EventsEntity> findByOrganizerIdAndDeletedFalseAndSurveyTrueAndDateAfter(Long oid, LocalDate date);
    List<EventsEntity> findByOrganizerIdAndDeletedFalseAndSurveyTrue(Long oid);
//    List<EventsEntity> findByDeletedFalseAndSurveyTrue();









}
