package com.gl.eventmanagementsystem.service.impl;

import com.gl.eventmanagementsystem.dto.RegistrationDto;
import com.gl.eventmanagementsystem.entity.Event;
import com.gl.eventmanagementsystem.entity.Registration;
import com.gl.eventmanagementsystem.entity.RegistrationStatus;
import com.gl.eventmanagementsystem.exception.EMSApiException;
import com.gl.eventmanagementsystem.exception.ResourceNotFoundException;
import com.gl.eventmanagementsystem.repository.EventRepository;
import com.gl.eventmanagementsystem.repository.RegistrationRepository;
import com.gl.eventmanagementsystem.service.RegistrationService;
import com.gl.eventmanagementsystem.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    private final RegistrationRepository registrationRepository;
    private  final EventRepository eventRepository;
    @Override
    public RegistrationDto register(RegistrationDto registrationDto) {
        registrationDto.setStatus(RegistrationStatus.CONFIRMED);
        Registration registration = registrationRepository.save(Mapper.mapToRegistration(registrationDto));
        return Mapper.mapToRegistrationDto(registration);
    }

    @Override
    public RegistrationDto getRegistrationStatus(Long eventId, Long regId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event","id",eventId)
        );
        Registration registration = registrationRepository.findById(regId).orElseThrow(
                () -> new ResourceNotFoundException("Registration","id",regId)
        );
        if(event.getId()!= registration.getEvent().getId())
            throw new EMSApiException(HttpStatus.NOT_FOUND,"Registration not found with ID ::"+regId+" for the event with ID ::"+eventId);
        return Mapper.mapToRegistrationDto(registration);
    }

    @Override
    public void deleteRegistration(Long eventId, Long regId) {
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotFoundException("Event","id",eventId)
        );
        Registration registration = registrationRepository.findById(regId).orElseThrow(
                () -> new ResourceNotFoundException("Registration","id",regId)
        );
        if(event.getId()!= registration.getEvent().getId())
            throw new EMSApiException(HttpStatus.NOT_FOUND,"Registration not found with ID ::"+regId+" for the event with ID ::"+eventId);
        registrationRepository.delete(registration);
    }
}
