package com.example.academicherald.services;

import com.example.academicherald.entity.Event;
import com.example.academicherald.entity.User;
import com.example.academicherald.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserService userService;
    private final EmailService emailService;

    public EventService(EventRepository eventRepository, UserService userService, EmailService emailService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    public Event create(Event event, Long userId) {
        event.setDateOfCreation(LocalDateTime.now());
        User author = userService.getById(userId);
        event.setAuthor(author);
        emailService.sendEventPublicationMessage(event);
        return eventRepository.save(event);
    }

    public Event getById(Long id) {
        return eventRepository.findByIdAndRdtIsNull(id);
    }

    public List<Event> getAll() {
        return eventRepository.getAllByRdtIsNull();
    }


    public Event update(Event newEvent) {
        Event oldEvent = getById(newEvent.getId());
        oldEvent.setTitle(newEvent.getTitle());
        oldEvent.setSubtitle(newEvent.getSubtitle());
        oldEvent.setText(newEvent.getText());
        oldEvent.setDateOfCreation(newEvent.getDateOfCreation());
        oldEvent.setAuthor(newEvent.getAuthor());
        return eventRepository.save(oldEvent);
    }

    public String delete(Long id) {
        Event event = getById(id);
        event.setRdt(LocalDateTime.now());
        eventRepository.save(event);
        return "Ивент " + event.getTitle() + " успешно удален";
    }
}
