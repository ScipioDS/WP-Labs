package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookings")
public class EventBookingController {
    public final EventService eventService;
    private final EventBookingService eventBookingService;

    public EventBookingController(EventService eventService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
    }
    @PostMapping("/finalize")
    public String bookEvent(@RequestParam(required = false) String error,
                            @RequestParam("chosen_event") String selectedEvent, // Extract selected radio button
                            @RequestParam("numTickets") int numTickets, // Extract number of tickets
                            @RequestParam("bookbool") String bookbool, // Confirm booking action,
                            Model model,
                            @RequestHeader("User-Agent") String userAgent,
                            @RequestHeader("Origin") String origin
    ){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        // eventBookingService.placeBooking(selectedEvent, "David", "K1", numTickets);
        EventBooking eventBooking = new EventBooking(selectedEvent, userAgent, origin, (long) numTickets);
        model.addAttribute("booking", eventBooking);
        return "bookingConfirmation";
    }
}
