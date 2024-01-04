package io.concertconnect.concerteventmanagement.service;

import io.concertconnect.concerteventmanagement.exception.EventNotFoundException;
import io.concertconnect.concerteventmanagement.model.Event;
import io.concertconnect.concerteventmanagement.repository.EventRepository;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    @PersistenceContext
    private EntityManager entityManager;

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

        //ofNullable로 감싸고 ifPresent 널체크, 추가

        Optional.ofNullable(updatedEvent.getCapacity()).ifPresent(existingEvent::setCapacity);
        Optional.ofNullable(updatedEvent.getName()).ifPresent(existingEvent::setName);
        Optional.ofNullable(updatedEvent.getVenue()).ifPresent(existingEvent::setVenue);
        Optional.ofNullable(updatedEvent.getStartTime()).ifPresent(existingEvent::setStartTime);
        Optional.ofNullable(updatedEvent.getEndTime()).ifPresent(existingEvent::setEndTime);

        return eventRepository.save(existingEvent);
    }

    @Transactional
    public Event updateEvent(int id, Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException("ID가 {" + id + "}인 이벤트를 찾을 수 없습니다.");
        }
        Event existingEvent = optionalEvent.get();

        BeanUtils.copyProperties(updatedEvent, existingEvent, "id");
        //업데이트되어 받는 객체의 값을 기존 이벤트 객체의 값으로 업데이트 해주는 기능(복사) id는 업데이트 안함

        return eventRepository.save(existingEvent);
    }

    @Transactional
    public synchronized UpdateResult reserveEvent(int id) {
        // 비관적 락
        Event event = entityManager.find(Event.class, id, LockModeType.PESSIMISTIC_WRITE);

        if (event == null) {
            throw new EventNotFoundException("ID가 {" + id + "}인 이벤트를 찾을 수 없습니다.");
        }

        if (event.getCapacity() > 0) {
            event.setCapacity(event.getCapacity() - 1);
            return UpdateResult.SUCCESS;
        } else {
            return UpdateResult.FULLY_BOOKED;
        }
    }

    @Getter
    public enum UpdateResult {
        SUCCESS("예약이 성공적으로 완료되었습니다!"),
        FULLY_BOOKED("이벤트가 모두 예약되었습니다!");

        private final String message;

        UpdateResult(String message) {
            this.message = message;
        }
    }
}