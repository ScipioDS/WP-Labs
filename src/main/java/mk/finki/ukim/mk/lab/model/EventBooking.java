package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventBooking {
    String eventName;
    String attendeeName;
    String attendeeAddress;
    Long numberOfTickets;
    public EventBooking(String attendeeName, String attendeeAddress){
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
    }
}
