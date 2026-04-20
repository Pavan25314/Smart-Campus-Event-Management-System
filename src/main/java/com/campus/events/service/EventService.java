package com.campus.events.service;

import com.campus.events.model.Event;
import com.campus.events.model.Registration;
import com.campus.events.repository.EventRepository;
import com.campus.events.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findByEventDateAfterOrderByEventDateAsc(LocalDateTime.now());
    }

    public List<Event> getFilteredEvents(String department, String type, LocalDateTime start, LocalDateTime end) {
        return eventRepository.findEventsWithFilters(department, type, start, end);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Transactional
    public Registration registerStudent(Registration registration) {
        Event event = eventRepository.findById(registration.getEvent().getId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.isFull()) {
            throw new RuntimeException("Event is already at full capacity");
        }

        registration.setEvent(event);
        Registration saved = registrationRepository.save(registration);
        
        event.setRegistrationsCount(event.getRegistrationsCount() + 1);
        eventRepository.save(event);
        
        return saved;
    }

    public List<Registration> getStudentRegistrations(String email) {
        return registrationRepository.findByStudentEmail(email);
    }

    public List<Registration> getEventRegistrations(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    public List<Object[]> getRegistrationStats() {
        return registrationRepository.countRegistrationsByEventTitle();
    }

    @Transactional
    public void submitFeedback(Long registrationId, Integer rating, String feedback) {
        Registration reg = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        
        reg.setRating(rating);
        reg.setFeedback(feedback);
        registrationRepository.save(reg);

        // Recalculate average rating for the event
        Event event = reg.getEvent();
        List<Registration> allRegs = registrationRepository.findByEventId(event.getId());
        double avg = allRegs.stream()
                .filter(r -> r.getRating() != null)
                .mapToInt(Registration::getRating)
                .average()
                .orElse(0.0);
        
        event.setAverageRating(avg);
        eventRepository.save(event);
    }
}
