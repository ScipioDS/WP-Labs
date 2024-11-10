package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;
    private final EventBookingRepository eventBookingRepository;
    public EventBookingServiceImpl(EventRepository eventRepository, EventBookingRepository eventBookingRepository){
        this.eventRepository = eventRepository;
        this.eventBookingRepository = eventBookingRepository;
    }
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        eventBookingRepository.placeBooking(eventBooking);
        return eventBooking;
    }

    @Override
    public EventBooking getBooking() {
        return eventBookingRepository.getBooking();
    }
}
