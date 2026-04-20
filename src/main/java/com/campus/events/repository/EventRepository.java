package com.campus.events.repository;

import com.campus.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventDateAfterOrderByEventDateAsc(LocalDateTime date);

    @Query("SELECT e FROM Event e WHERE " +
           "(:department IS NULL OR e.department = :department) AND " +
           "(:type IS NULL OR e.type = :type) AND " +
           "(:startDate IS NULL OR e.eventDate >= :startDate) AND " +
           "(:endDate IS NULL OR e.eventDate <= :endDate)")
    List<Event> findEventsWithFilters(@Param("department") String department,
                                      @Param("type") String type,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);
}
