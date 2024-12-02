package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final EventBookingService eventBookingService;
    private final LocationService locationService;
    public EventController(EventService eventService, EventBookingService eventBookingService, LocationService locationService){
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
        this.locationService = locationService;
    }
    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("events", eventService.listAll());
        return "listEvents";
    }
    @PostMapping("/book")
    public String bookEvent(@RequestParam(required = false) String error,
            @RequestParam("group") String selectedEvent, // Extract selected radio button
            @RequestParam("numTickets") int numTickets, // Extract number of tickets
            @RequestParam("bookbool") String bookbool, // Confirm booking action,
             Model model
            ){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        eventBookingService.placeBooking(selectedEvent, "David", "K1", numTickets);
        model.addAttribute("booking", eventBookingService.getBooking());
        return "bookingConfirmation";
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }
    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locations = this.locationService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String score,
                              @RequestParam String description,
                              @RequestParam Long location){
        this.eventService.save(name, score, description, location);
//        if (id != null) {
//            this.eventService.editById(id,name,description,score,location);
//        } else {
//            this.eventService.save(name, score, description, location);
//        }

        return "redirect:/events";
    }


}
