package com.gl.eventmanagementsystem.controller;

import com.gl.eventmanagementsystem.dto.RegistrationDto;
import com.gl.eventmanagementsystem.service.impl.RegistrationServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
    Add the required annotations to make this class a REST Controller
    for handling the registration related requests.
 */
@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;

    // Add a registration
    @PostMapping("/register")
    public ResponseEntity<RegistrationDto> addRegistration(@Valid @RequestBody RegistrationDto registrationDto){
        return new ResponseEntity<>(registrationService.register(registrationDto), HttpStatus.CREATED);
    }

    // Get the registration status
    @GetMapping("/status/{registrationId}/{eventId}")
    public RegistrationDto getRegistrationStatus(@PathVariable(value = "eventId") Long  eventId,
                                 @PathVariable(value = "registrationId") Long registrationId){
        return registrationService.getRegistrationStatus(eventId,registrationId);
    }

    // Delete a registration
    @DeleteMapping("/cancel/{registrationId}/{eventId}")
    public String cancelRegistration(@PathVariable(value = "eventId") Long eventId ,
                                     @PathVariable(value = "registrationId") Long registrationId){
        registrationService.deleteRegistration(eventId,registrationId);
        return "Registration canceled successfully";
    }
}
