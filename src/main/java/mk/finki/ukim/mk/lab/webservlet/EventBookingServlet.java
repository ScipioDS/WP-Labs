package mk.finki.ukim.mk.lab.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "Confirm Booking", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingService eventBookingService;
    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine){
        this.springTemplateEngine = springTemplateEngine;
        this.eventBookingService = eventBookingService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("booking", eventBookingService.getBooking());

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }
}
