package com.gl.eventmanagementsystem.service.impl;


import com.gl.eventmanagementsystem.dto.EventDto;
import com.gl.eventmanagementsystem.entity.Event;
import com.gl.eventmanagementsystem.exception.ResourceNotFoundException;
import com.gl.eventmanagementsystem.repository.EventRepository;
import com.gl.eventmanagementsystem.service.EventService;
import com.gl.eventmanagementsystem.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = eventRepository.save(Mapper.mapToEvent(eventDto));
        return Mapper.mapToEventDto(event);
    }

    @Override
    public EventDto getEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                ()-> new ResourceNotFoundException("Event","id",eventId)
        );
        return Mapper.mapToEventDto(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> eventList= eventRepository.findAll();
        List<EventDto> eventDtos = eventList.stream().map(e->Mapper.mapToEventDto(e)).toList();
        return eventDtos;
    }

    @Override
    public void cancelEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                ()-> new ResourceNotFoundException("Event","id",eventId)
        );
        eventRepository.delete(event);
    }
}
