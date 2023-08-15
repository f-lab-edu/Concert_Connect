package io.concertconnect.concerteventmanagement.controller;

import io.concertconnect.concerteventmanagement.model.Event;
import io.concertconnect.concerteventmanagement.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> retrieveAllEvents() {
        return eventService.retrieveAllEvents();
    }

    @GetMapping("/{id}")
    public Event retrieveEvent(@PathVariable int id) {
        return eventService.retrieveEvent(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEvent.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Event> partialUpdateEvent(@PathVariable int id, @RequestBody Event updatedEvent) {
        Event savedEvent = eventService.partialUpdateEvent(id, updatedEvent);
        return ResponseEntity.ok(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @Valid @RequestBody Event updatedEvent) {
        Event savedEvent = eventService.updateEvent(id, updatedEvent);
        return ResponseEntity.ok(savedEvent);
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserveEvent(@PathVariable int id) {
        EventService.UpdateResult result = eventService.reserveEvent(id);

        if (result == EventService.UpdateResult.SUCCESS) {
            return ResponseEntity.ok(result.getMessage());
        } else {
            return ResponseEntity.badRequest().body(result.getMessage());
        }
    }
}
