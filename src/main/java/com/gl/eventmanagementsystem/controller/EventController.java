package com.gl.eventmanagementsystem.controller;

import com.gl.eventmanagementsystem.dto.EventDto;
import com.gl.eventmanagementsystem.service.impl.EventServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    Add the required annotations to make this class a REST Controller
    for handling the event related requests.
 */
@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
public class EventController {

    private  final EventServiceImpl eventService;
    // Add an event
    @PostMapping
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto){
        System.out.println("hello World!");
        return new ResponseEntity<>(eventService.createEvent(eventDto), HttpStatus.CREATED);
    }

    // Get all events
    @GetMapping
    public List<EventDto> getAll(){
        return eventService.getAllEvents();
    }

    // Get an event by ID
    @GetMapping("/{eventId}")
    public EventDto getById(@PathVariable Long eventId){
        return eventService.getEvent(eventId);
    }

    // Cancel an event
    @DeleteMapping("/{eventId}")
    public String cancelEvent(@PathVariable Long eventId){
        eventService.cancelEvent(eventId);
        return "Event canceled successfullty";
    }

}
