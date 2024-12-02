package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.CategoryRepository;
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
    private final CategoryRepository categoryRepository;
    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, CategoryRepository categoryRepository){
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
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
    public Optional<Event> saveEvent(String name, String description, Double score, Long locationId, Long categoryId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new LocationNotFoundException(categoryId));

        Event e = new Event(name, description, score, location, category);
        return Optional.of(eventRepository.save(e));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void editById(Long eventId, String name, Double score, String description, Long locationId, Long categoryId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new LocationNotFoundException(categoryId));

        List<Event> eventList = eventRepository.findAllById(eventId);
        Event tmp=eventList.get(0);
        tmp.setName(name);
        tmp.setPopularityScore(score);
        tmp.setDescription(description);
        tmp.setLocation(location);
        tmp.setCategory(category);
        eventRepository.save(tmp);
    }

    @Override
    public void save(String name, String score, String description, Long locationId, Long categoryId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new LocationNotFoundException(categoryId));

        Event e = new Event(name, description, Double.parseDouble(score), location, category);
        this.eventRepository.save(e);
    }
}
