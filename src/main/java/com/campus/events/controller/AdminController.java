package com.campus.events.controller;

import com.campus.events.model.Event;
import com.campus.events.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            @RequestParam(required = false) String department,
                            @RequestParam(required = false) String type,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        
        if (department != null || type != null || start != null || end != null) {
            model.addAttribute("events", eventService.getFilteredEvents(department, type, start, end));
        } else {
            model.addAttribute("events", eventService.getAllEvents());
        }
        return "admin/dashboard";
    }

    @GetMapping("/events/new")
    public String newEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "admin/event-form";
    }

    @GetMapping("/events/edit/{id}")
    public String editEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        model.addAttribute("event", event);
        return "admin/event-form";
    }

    @PostMapping("/events/save")
    public String saveEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/event-form";
        }
        eventService.saveEvent(event);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/events/stats")
    public String statistics(Model model) {
        model.addAttribute("stats", eventService.getRegistrationStats());
        model.addAttribute("events", eventService.getAllEvents());
        return "admin/stats";
    }
}
