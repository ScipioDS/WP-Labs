package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.inmemory.InMemoryEventBookingRepository;
import mk.finki.ukim.mk.lab.repository.inmemory.InMemoryEventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final InMemoryEventRepository eventRepository;
    private final InMemoryEventBookingRepository eventBookingRepository;
    public EventBookingServiceImpl(InMemoryEventRepository eventRepository, InMemoryEventBookingRepository eventBookingRepository){
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
