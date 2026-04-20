package com.campus.events.controller;

import com.campus.events.model.Event;
import com.campus.events.model.Registration;
import com.campus.events.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class PublicEventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public String index(@RequestParam(required = false) String dept,
                        @RequestParam(required = false) String type,
                        Model model) {
        if ((dept != null && !dept.isEmpty()) || (type != null && !type.isEmpty())) {
            model.addAttribute("events", eventService.getFilteredEvents(dept, type, LocalDateTime.now(), null));
        } else {
            model.addAttribute("events", eventService.getUpcomingEvents());
        }
        return "index";
    }

    @GetMapping("/events/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        model.addAttribute("event", event);
        model.addAttribute("registration", new Registration());
        return "event-details";
    }

    @PostMapping("/register")
    public String registerForEvent(@Valid @ModelAttribute("registration") Registration registration,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            Event event = eventService.getEventById(registration.getEvent().getId()).get();
            model.addAttribute("event", event);
            return "event-details";
        }
        
        try {
            eventService.registerStudent(registration);
            return "redirect:/registration-success";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            Event event = eventService.getEventById(registration.getEvent().getId()).get();
            model.addAttribute("event", event);
            return "event-details";
        }
    }

    @GetMapping("/registration-success")
    public String registrationSuccess() {
        return "registration-success";
    }

    @GetMapping("/my-registrations")
    public String myRegistrations(@RequestParam(required = false) String email, Model model) {
        if (email != null && !email.isEmpty()) {
            model.addAttribute("registrations", eventService.getStudentRegistrations(email));
        }
        return "my-registrations";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@RequestParam Long registrationId,
                                 @RequestParam Integer rating,
                                 @RequestParam String feedback,
                                 @RequestParam String email) {
        eventService.submitFeedback(registrationId, rating, feedback);
        return "redirect:/my-registrations?email=" + email;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
