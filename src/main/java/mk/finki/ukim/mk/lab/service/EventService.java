package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    void deleteById(Long id);
    Optional<Event> saveEvent(String name, String description, Double score, Long locationId);
    Optional<Event> findById(Long id);

    void editById(Long eventId, String name, Double score, String description, Long location);

    void save(String name, String score, String description, Long location);
}