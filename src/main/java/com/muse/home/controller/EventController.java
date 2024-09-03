package com.muse.home.controller;

import com.muse.home.domain.Event;
import com.muse.home.repository.EventRepository;
import com.muse.home.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {
    private final CalendarService calendarService;
    @Autowired
    public EventController(CalendarService calendarService){
        this.calendarService = calendarService;
    }

    @GetMapping("/events")
    public List<Event> getEvents(){
        return calendarService.getAllEventList();
    }
}
