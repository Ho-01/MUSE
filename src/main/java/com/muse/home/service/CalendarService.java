package com.muse.home.service;

import com.muse.home.controller.EventForm;
import com.muse.home.domain.Event;

import java.util.List;

public interface CalendarService {
    public Long addEvent(EventForm eventForm);
    public Long editEvent(EventForm eventForm);
    public void deleteEvent(EventForm eventForm);
    public List<Event> getAllEventList();
    public String getAllEventString();
}
