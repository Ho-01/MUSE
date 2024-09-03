package com.muse.home.controller;

import com.muse.home.domain.Event;
import com.muse.home.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalendarController {
    private final CalendarService calendarService;
    @Autowired
    public CalendarController(CalendarService calendarService){
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar";
    }

    @PostMapping("/calendar/new")
    public String newEvent(EventForm eventForm){
        calendarService.addEvent(eventForm);
        return "redirect:/calendar";
    }

    @PostMapping("/calendar/edit")
    public String editEvent(EventForm eventForm){
        calendarService.editEvent(eventForm);
        return "redirect:/calendar";
    }

    @PostMapping("/calendar/delete")
    public String deleteEvent(EventForm eventForm){
        calendarService.deleteEvent(eventForm);
        return "redirect:/calendar";
    }
}
