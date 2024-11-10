package mk.finki.ukim.mk.lab.web.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.boot.web.servlet.server.Session;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "Event List", urlPatterns = "")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine springTemplateEngine;
    private String name;
    private String address;
    public EventListServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine){
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
        this.eventBookingService = eventBookingService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        address = req.getRemoteAddr();
        name = req.getHeader("user-agent");
        context.setVariable("events", eventService.listAll());
        springTemplateEngine.process("listEvents.html", context, resp.getWriter());

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String search = req.getParameter("search");
        String tmp = req.getParameter("bookbool");
        if(tmp!=null){
            String event = req.getParameter("group");
            int numTickets = Integer.parseInt(req.getParameter("numTickets"));

            eventBookingService.placeBooking(event, name, address, numTickets);

            resp.sendRedirect("/eventBooking");
        } else {
            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);

            WebContext context = new WebContext(webExchange);

            context.setVariable("events", eventService.searchEvents(search));
            springTemplateEngine.process("listEvents.html", context, resp.getWriter());
        }
    }
}
