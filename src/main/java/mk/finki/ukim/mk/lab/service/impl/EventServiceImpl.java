package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.inmemory.InMemoryEventRepository;
import mk.finki.ukim.mk.lab.repository.inmemory.InMemoryLocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

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
        return eventRepository.findAllByNameLike(text);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> saveEvent(String name, String description, Double score, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));

        Event e = new Event(name, description, score, location);
        return Optional.of(eventRepository.save(e));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void editById(Long eventId, String name, String score, String description, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));

        List<Event> eventList = eventRepository.findAllById(eventId);
        Event tmp=eventList.get(0);
        tmp.setName(name);
        tmp.setPopularityScore(Double.parseDouble(score));
        tmp.setDescription(description);
        tmp.setLocation(location);
    }

    @Override
    public void save(String name, String score, String description, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        Event e = new Event(name, description, Double.parseDouble(score), location);
        this.eventRepository.save(e);
    }
}
