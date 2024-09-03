package com.muse.home.service;

import com.muse.home.controller.EventForm;
import com.muse.home.domain.Event;
import com.muse.home.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CalendarServiceImpl implements CalendarService{
    private final EventRepository eventRepository;
    @Autowired
    public CalendarServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Long addEvent(EventForm eventForm) {
        Event event = new Event();
        String [] dateString = eventForm.getDateString().split("-");
        event.setDateString(dateString[0]+dateString[1]+dateString[2]);
        event.setTitle(eventForm.getTitle());
        event.setTeamName(eventForm.getTeamName());
        event.setTeamMembers(eventForm.getTeamMembers());
        if(eventForm.getStartTime()!=null){
            String [] startTime = eventForm.getStartTime().toString().split(":");
            event.setStartTime(startTime[0]+":"+startTime[1]);
        } else{
            event.setStartTime("");
        }
        if(eventForm.getEndTime()!=null){
            String [] endTime = eventForm.getEndTime().toString().split(":");
            event.setEndTime(endTime[0]+":"+endTime[1]);
        } else {
            event.setEndTime("");
        }
        eventRepository.save(event);
        return event.getId();
    }

    @Override
    public Long editEvent(EventForm eventForm) {
        Event event = new Event();
        event.setId(eventForm.getId());
        String [] dateString = eventForm.getDateString().split("-");
        event.setDateString(dateString[0]+dateString[1]+dateString[2]);
        event.setTitle(eventForm.getTitle());
        event.setTeamName(eventForm.getTeamName());
        event.setTeamMembers(eventForm.getTeamMembers());
        if(eventForm.getStartTime()!=null){
            String [] startTime = eventForm.getStartTime().toString().split(":");
            if(startTime.length>=2){
                event.setStartTime(startTime[0]+":"+startTime[1]);
            } else{
                event.setStartTime("00:00");
            }
        }
        if(eventForm.getEndTime()!=null){
            String [] endTime = eventForm.getEndTime().toString().split(":");
            event.setEndTime(endTime[0]+":"+endTime[1]);
        } else {
            event.setEndTime("00:00");
        }
        eventRepository.update(event);
        return event.getId();
    }

    @Override
    public void deleteEvent(EventForm eventForm) {
        eventRepository.delete(eventForm.getId());
    }

    @Override
    public List<Event> getAllEventList() {
        getAllEventString();
        return eventRepository.getAllEvent();
    }

    @Override
    public String getAllEventString() {
        List<Event> eventList = eventRepository.getAllEvent();
        Map<Long, Event> eventMap = new TreeMap<>();
        eventList.forEach(event -> {
            //Long dateTime = Long.parseLong(event.getDateString() + event.getStartTime().split(":")[0] + event.getStartTime().split(":")[1]);
            System.out.println(event.getStartTime().split(":").length);
            //eventMap.put(dateTime,event);
        });
        System.out.println(eventMap);
        return "good";
    }
}
