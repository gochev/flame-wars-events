package com.example;

import com.example.model.Agenda;
import com.example.model.Event;
import com.example.model.Organizer;
import com.example.model.Submission;
import com.example.model.Talk;
import com.example.repository.AgendaRepository;
import com.example.repository.EventRepository;
import com.example.repository.OrganizerRepository;
import com.example.repository.SubmissionRepository;
import com.example.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Component
    public static class Initializer implements ApplicationRunner {

        private final PasswordEncoder passwordEncoder;

        private final OrganizerRepository organizerRepository;

        private final EventRepository eventRepository;

        private final AgendaRepository agendaRepository;

        private final TalkRepository talkRepository;
        
        private final SubmissionRepository submissionRepository;

        @Autowired
        public Initializer(PasswordEncoder passwordEncoder, OrganizerRepository organizerRepository, EventRepository eventRepository,
                           AgendaRepository agendaRepository, TalkRepository talkRepository, SubmissionRepository submissionRepository) {
            this.passwordEncoder = passwordEncoder;
            this.organizerRepository = organizerRepository;
            this.eventRepository = eventRepository;
            this.agendaRepository = agendaRepository;
            this.talkRepository = talkRepository;
            this.submissionRepository = submissionRepository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {

            Organizer organizer = new Organizer();
            organizer.setUsername("test");
            organizer.setPassword(passwordEncoder.encode("test"));
            organizerRepository.save(organizer);

            Event event = new Event();
            event.setTitle("Flame Wars");
            event.setLocation("SoftwareAG, Sofia");

            eventRepository.save(event);

            Submission submission = new Submission();
            submission.setTitle("first talk");
            submission.setEmail("petar petrov");
            submission.setHeadline("nou qk talk brat izberete me");
            submissionRepository.save(submission);
            
            Submission submission2 = new Submission();
            submission2.setTitle("awesome  talk");
            submission2.setEmail("4o4o ");
            submission2.setHeadline("ne e awesome");
            submissionRepository.save(submission2);
            
            Agenda agenda = new Agenda();
            agendaRepository.save(agenda);
            
            Talk talk = new Talk();
            talk.setAgenda(agenda);
            talk.setSubmission(submission);
            talk.setStartTime(new Date());

            talkRepository.save(talk);

        }
    }
}
