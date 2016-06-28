package com.example;

import com.example.model.Agenda;
import com.example.model.Talk;
import com.example.repository.AgendaRepository;
import com.example.repository.TalkRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.model.Event;
import com.example.model.Organizer;
import com.example.repository.EventRepository;
import com.example.repository.OrganizerRepository;

import java.time.LocalDateTime;

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

		AgendaRepository agendaRepository = context.getBean(AgendaRepository.class);
		Agenda agenda = new Agenda();

		TalkRepository talkRepository = context.getBean(TalkRepository.class);
		Talk talk = new Talk();
		talk.setTitle("first talk");
		talk.setSpeaker("petar petrov");
		talk.setAgenda(agenda);

		talk.setStartTime(LocalDateTime.now());

		talkRepository.save(talk);


	}
	
}
