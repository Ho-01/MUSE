package com.muse.home.repository;

import com.muse.home.domain.Event;

import java.util.List;

public interface EventRepository {
    public Event save(Event event);
    public Event update(Event event);
    public void delete(Long id);
    public List<Event> getAllEvent();
}
