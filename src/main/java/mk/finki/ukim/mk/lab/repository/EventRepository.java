package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {
    public List<Event> findAll(){
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text){
        return DataHolder.events.stream()
                .filter(c -> c.getName().contains(text) || c.getDescription().contains(text))
                .toList();
    }

    public EventBooking placeBooking(EventBooking eventBooking){
        DataHolder.booking = eventBooking;
        return eventBooking;
    }
    public EventBooking getBooking(){
        return DataHolder.booking;
    }
}
