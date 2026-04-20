package com.campus.events.config;

import com.campus.events.model.Event;
import com.campus.events.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(EventRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Event e1 = new Event();
                e1.setTitle("AI Workshop: Future of Coding");
                e1.setDescription("Learn about LLMs and agentic AI in this hands-on workshop.");
                e1.setEventDate(LocalDateTime.now().plusDays(5).withHour(10).withMinute(0));
                e1.setLocation("Main Auditorium");
                e1.setDepartment("Computer Science");
                e1.setType("Workshop");
                e1.setCapacity(50);
                repository.save(e1);

                Event e2 = new Event();
                e2.setTitle("Annual Tech Seminar 2026");
                e2.setDescription("Leading industry experts talk about the next generation of web technologies.");
                e2.setEventDate(LocalDateTime.now().plusDays(10).withHour(14).withMinute(30));
                e2.setLocation("Conference Hall A");
                e2.setDepartment("Engineering");
                e2.setType("Seminar");
                e2.setCapacity(100);
                repository.save(e2);

                Event e3 = new Event();
                e3.setTitle("Spring Music Festival");
                e3.setDescription("A day of musical performances and cultural events by students.");
                e3.setEventDate(LocalDateTime.now().plusDays(15).withHour(18).withMinute(0));
                e3.setLocation("Open Air Theater");
                e3.setDepartment("Cultural");
                e3.setType("Cultural");
                e3.setCapacity(500);
                repository.save(e3);

                Event e4 = new Event();
                e4.setTitle("Hackathon 2026: Code for Good");
                e4.setDescription("A 48-hour coding marathon to solve real-world problems. Great prizes for the top teams!");
                e4.setEventDate(LocalDateTime.now().plusDays(3).withHour(9).withMinute(0));
                e4.setLocation("Innovation Lab");
                e4.setDepartment("Computer Science");
                e4.setType("Workshop");
                e4.setCapacity(150);
                repository.save(e4);

                Event e5 = new Event();
                e5.setTitle("Introduction to Fine Arts");
                e5.setDescription("A seminar exploring modern expressionism and the evolution of art in the digital age.");
                e5.setEventDate(LocalDateTime.now().plusDays(8).withHour(11).withMinute(30));
                e5.setLocation("Arts Block Room 102");
                e5.setDepartment("Arts");
                e5.setType("Seminar");
                e5.setCapacity(60);
                repository.save(e5);

                Event e6 = new Event();
                e6.setTitle("Business Analytics & Networking");
                e6.setDescription("Learn the fundamentals of business analytics and build your professional network.");
                e6.setEventDate(LocalDateTime.now().plusDays(20).withHour(16).withMinute(0));
                e6.setLocation("Management Lounge");
                e6.setDepartment("Management");
                e6.setType("Seminar");
                e6.setCapacity(80);
                repository.save(e6);
            }
        };
    }
}
