package com.muse.home.repository;

import com.muse.home.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryEventRepository implements EventRepository{
    private final Map<Long,Event> eventStorage = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Event save(Event event) {
        event.setId(sequence++);
        eventStorage.put(event.getId(), event);
        return event;
    }

    @Override
    public Event update(Event event) {
        eventStorage.put(event.getId(),event);
        return event;
    }

    @Override
    public void delete(Long id) {
        eventStorage.remove(id);
    }

    @Override
    public List<Event> getAllEvent() {
        List<Event> allEventList = new ArrayList<>();
        for(Map.Entry<Long, Event> entry: eventStorage.entrySet()){
            allEventList.add(entry.getValue());
        }
        return allEventList;
    }
}
