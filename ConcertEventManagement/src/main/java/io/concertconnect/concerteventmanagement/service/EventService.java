package io.concertconnect.concerteventmanagement.service;

import io.concertconnect.concerteventmanagement.exception.EventNotFoundException;
import io.concertconnect.concerteventmanagement.model.Event;
import io.concertconnect.concerteventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    public Event retrieveEvent(int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            throw new EventNotFoundException("ID{" + id + "} Not Found");
        }
        return event.get();
    }

    @Transactional
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    @Transactional
    public Event createEvent(Event event) {
        event.setId(eventRepository.getMaxId() + 1);
        return eventRepository.save(event);
    }
    public Event partialUpdateEvent(int id, Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException(String.format("ID가 {%d}인 이벤트를 찾을 수 없음", id));
        }

        Event existingEvent = optionalEvent.get();

        if (updatedEvent.getCapacity() != null) {
            existingEvent.setCapacity(updatedEvent.getCapacity());
        }
        if (updatedEvent.getName() != null) {
            existingEvent.setName(updatedEvent.getName());
        }
        if (updatedEvent.getVenue() != null) {
            existingEvent.setVenue(updatedEvent.getVenue());
        }
        if (updatedEvent.getStartTime() != null) {
            existingEvent.setStartTime(updatedEvent.getStartTime());
        }
        if (updatedEvent.getEndTime() != null) {
            existingEvent.setEndTime(updatedEvent.getEndTime());
        }

        return eventRepository.save(existingEvent);
    }

    @Transactional
    public Event updateEvent(int id, Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException("ID가 {" + id + "}인 이벤트를 찾을 수 없습니다.");
        }
        Event existingEvent = optionalEvent.get();

        existingEvent.setCapacity(updatedEvent.getCapacity());
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setVenue(updatedEvent.getVenue());
        existingEvent.setTicketstartTime(updatedEvent.getTicketstartTime());
        existingEvent.setTicketendTime(updatedEvent.getTicketendTime());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());

        return eventRepository.save(existingEvent);
    }

    @Transactional
    public String reserveEvent(int id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException("ID가 {" + id + "}인 이벤트를 찾을 수 없습니다.");
        }
        Event event = optionalEvent.get();

        if (event.getCapacity() > 0) {
            event.setCapacity(event.getCapacity() - 1);
            eventRepository.save(event);
            return "예약이 성공적으로 완료되었습니다!";
        } else {
            return "이벤트가 모두 예약되었습니다!";
        }
    }
}
