package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

@Repository
public class EventBookingRepository {
    public EventBooking placeBooking(EventBooking eventBooking){
        DataHolder.booking = eventBooking;
        return eventBooking;
    }
    public EventBooking getBooking(){
        return DataHolder.booking;
    }
}
