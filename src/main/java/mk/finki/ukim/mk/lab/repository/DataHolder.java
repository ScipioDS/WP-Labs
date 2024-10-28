package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static EventBooking booking = null;
    @PostConstruct
    public void init(){
        events = new ArrayList<>();
        events.add(new Event("Brucoshka@FEIT", "Student Party", 10.0));
        events.add(new Event("Denovi na knigata", "Book Fair", 9.0));
        events.add(new Event("Panair", "City Fair", 8.0));
        events.add(new Event("Sean Paul Live", "Concert", 7.0));
        events.add(new Event("The Big Steppers", "Tour", 6.0));
        events.add(new Event("The Popout", "Concert", 5.0));
        events.add(new Event("Denovi na medot", "Honey Fair", 4.0));
        events.add(new Event("Pivolend", "Fair", 3.0));
        events.add(new Event("Sureshots 20", "Party", 2.0));
        events.add(new Event("Brucoshka@FINKI", "Student Party", 1.0));

        booking = new EventBooking("Yes Event", "Yes", "Yes Street", (long) 10);
    }
}
