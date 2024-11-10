package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository){
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> saveEvent(String name, String description, Double score, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        return eventRepository.saveEvent(name, description, score, location);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void editById(Long eventId, String name, String score, String description, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        eventRepository.editById(eventId, name, score, description, location);
    }

    @Override
    public void save(String name, String score, String description, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        eventRepository.saveEvent(name, description, Double.parseDouble(score), location);
    }
}
