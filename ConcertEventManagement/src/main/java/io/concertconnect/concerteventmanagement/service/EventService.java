
package io.concertconnect.concerteventmanagement.service;

import io.concertconnect.concerteventmanagement.exception.EventNotFoundException;
import io.concertconnect.concerteventmanagement.model.Event;
import io.concertconnect.concerteventmanagement.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
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
    @Getter
    public enum UpdateResult { //1번밖에 안써서 분리하기 애매함
        SUCCESS("예약이 성공적으로 완료되었습니다!"),
        FULLY_BOOKED("이벤트가 모두 예약되었습니다!");

        private final String message;

        UpdateResult(String message) {
            this.message = message;
        }
    }
    @Transactional
    public UpdateResult reserveEvent(int id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException("ID가 {" + id + "}인 이벤트를 찾을 수 없습니다.");
        }
        Event event = optionalEvent.get();

        if (event.getCapacity() > 0) {
            event.setCapacity(event.getCapacity() - 1);
            eventRepository.save(event);
            return UpdateResult.SUCCESS;
        } else {
            return UpdateResult.FULLY_BOOKED;
        }
    }
}