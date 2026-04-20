package com.campus.events.repository;

import com.campus.events.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStudentEmail(String email);
    List<Registration> findByEventId(Long eventId);
    long countByEventId(Long eventId);
    
    @Query("SELECT r.event.title, COUNT(r) FROM Registration r GROUP BY r.event.title")
    List<Object[]> countRegistrationsByEventTitle();
}
