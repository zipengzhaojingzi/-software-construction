package com.pet.demo.service.impl;

import com.pet.demo.dao.EventDao;
import com.pet.demo.entity.Event;
import com.pet.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public Event findById(String eventId) {
        return eventDao.findById(eventId);
    }

    @Override
    public void save(Event event) {
        if (event.getEventId() == null) {
            event.setEventId(UUID.randomUUID().toString());
        }
        eventDao.save(event);
    }

    @Override
    public void deleteById(String eventId) {
        eventDao.deleteById(eventId);
    }

    @Override
    public void update(Event event) {
        eventDao.update(event);
    }

    @Override
    public List<Event> findByEventName(String eventName) {
        return eventDao.findByEventName(eventName);
    }

    @Override
    public void insertUserEvent(String userId, String eventId) {
        eventDao.insertUserEvent(userId, eventId);
    }

    @Override
    public int findUserEvent(String userId, String eventId) {
        return eventDao.findUserEvent(userId, eventId);
    }
}
