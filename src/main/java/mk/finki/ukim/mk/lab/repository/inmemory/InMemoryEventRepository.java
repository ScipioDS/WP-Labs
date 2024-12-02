package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryEventRepository {
    public List<Event> findAll(){
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text){
        return DataHolder.events.stream()
                .filter(c -> c.getName().contains(text) || c.getDescription().contains(text))
                .toList();
    }
    public void deleteById(Long id){
        DataHolder.events.removeIf(e -> e.getId().equals(id));
    }
    public Optional<Event> saveEvent(String name, String description, Double score, Location location){
        if(location==null){
            throw new IllegalArgumentException();
        }
        DataHolder.events.removeIf(e -> e.getName().equals(name));
        Event event = new Event(name, description, score, location);
        DataHolder.events.add(event);
        return Optional.of(event);
    }
    public Optional<Event> findById(Long id){
        return DataHolder.events.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void editById(Long eventId, String name, String score, String description, Location location) {
        Event event = DataHolder.events.stream().filter(e -> e.getId().equals(eventId)).findFirst().get();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(Double.parseDouble(score));
        event.setLocation(location);
    }
}
