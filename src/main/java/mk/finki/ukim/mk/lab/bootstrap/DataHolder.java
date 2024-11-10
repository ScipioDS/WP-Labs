package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static EventBooking booking = null;
    public static List<Location> locations = null;
    @PostConstruct
    public void init(){
        locations = new ArrayList<>();
        locations.add(new Location("Tehnichki kampus", "Ruger Boshkovikj 16", "10000", "Uni Campus"));
        locations.add(new Location("Arena Boris Trajkovski", "BT 1", "100000", "Sports Arena"));
        locations.add(new Location("MKC", "Kaj MRT", "7000", "Venue"));
        locations.add(new Location("T-Mobile Centre", "TMC 1", "200000", "Sports Arena"));
        locations.add(new Location("Ploshtad", "Bez adresa 15", "1000000", "Square"));

        events = new ArrayList<>();
        events.add(new Event("Brucoshka@FEIT", "Student Party", 10.0, locations.get(0)));
        events.add(new Event("Denovi na knigata", "Book Fair", 9.0, locations.get(1)));
        events.add(new Event("Panair", "City Fair", 8.0, locations.get(4)));
        events.add(new Event("Sean Paul Live", "Concert", 7.0, locations.get(1)));
        events.add(new Event("The Big Steppers", "Tour", 6.0, locations.get(3)));
        events.add(new Event("The Popout", "Concert", 5.0, locations.get(3)));
        events.add(new Event("Denovi na medot", "Honey Fair", 4.0, locations.get(4)));
        events.add(new Event("Pivolend", "Fair", 3.0, locations.get(4)));
        events.add(new Event("Sureshots 20", "Party", 2.0, locations.get(2)));
        events.add(new Event("Brucoshka@FINKI", "Student Party", 1.0, locations.get(2)));

        booking = new EventBooking("Yes Event", "Yes", "Yes Street", (long) 10);
    }
}
