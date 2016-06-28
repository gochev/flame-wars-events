package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.model.Event;
import com.example.model.Organizer;
import com.example.repository.EventRepository;
import com.example.repository.OrganizerRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
		OrganizerRepository organizerRepository = context.getBean(OrganizerRepository.class);
		Organizer organizer = new Organizer();
		organizer.setUsername("test");
		organizer.setPassword(encoder.encode("test"));
		organizerRepository.save(organizer);
		
		Event event = new Event();
		event.setTitle("Flame Wars");
		event.setLocation("SoftwareAG, Sofia");
		
		EventRepository eventRepository = context.getBean(EventRepository.class);
		eventRepository.save(event);
		
	}
	
}
