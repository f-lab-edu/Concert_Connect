package io.concertconnect.concerteventmanagement.controller;

import io.concertconnect.concerteventmanagement.model.Event;
import io.concertconnect.concerteventmanagement.repository.EventRepository;
import io.concertconnect.concerteventmanagement.exception.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository; //생성자 주입방식을 선호하는 이유 https://madplay.github.io/post/why-constructor-injection-is-better-than-field-injection
    }

    //8080/jpa/events
    //이벤트 전체조회
    @GetMapping
    public List<Event> retrieveAllEvents() {

        return eventRepository.findAll();
    }
    //이벤트 단일조회
    @GetMapping("/{id}")
    public Event retrieveEvent(@PathVariable int id) {
        Optional<Event> event = eventRepository.findById(id);
        // Optional로 받는 이유 반환값으로 선언되어있어서
        // Optional을 쓰는 이유 데이터가 있을 수 도 있고 없을 수 도 있기 때문에 선택적 반환을 하기위해 존재
        // Optional<Event> event = eventRepository.findById(id);
        // Optional<Event> 타입의 변수 event에 eventRepository.findById(id);(이벤트 체크) Event객체를 반환하는데
        // null이면 비어있는 값을 반환하고(Optional) 아니면 값을 할당 해서 반환

        if (!event.isPresent()) { //.isPresent() 데이터가 존재하는지를 검사하는 메서드
            throw new EventNotFoundException(String.format("ID{%s} Not Found", id));
        }

        return event.get();//Optional<Event> 타입이 아니여도 get()이 맞게 반환해줌
    }
    //이벤트 삭제
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventRepository.deleteById(id);
    }
    //이벤트 생성
    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) { //클라이언트에게 데이터json으로받기
        event.setId(eventRepository.getMaxId() + 1); // Set ID as max ID + 1
        Event savedEvent = eventRepository.save(event);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedEvent.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    // 이벤트 수정
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @Valid @RequestBody Event updatedEvent) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException(String.format("ID가 {%d}인 이벤트를 찾을 수 없음", id));
        }

        Event existingEvent = optionalEvent.get();

        existingEvent.setName(updatedEvent.getName());
        existingEvent.setVenue(updatedEvent.getVenue());
        existingEvent.setTicketstartTime(updatedEvent.getTicketstartTime());
        existingEvent.setTicketendTime(updatedEvent.getTicketendTime());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());

        Event savedEvent = eventRepository.save(existingEvent);

        return ResponseEntity.ok(savedEvent);
    }
}

