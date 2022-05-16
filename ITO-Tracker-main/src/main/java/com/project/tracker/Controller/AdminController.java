package com.project.tracker.Controller;
import com.project.tracker.Response;
import com.project.tracker.Service.AdminService;
import com.project.tracker.Utility.TicketShow;
import com.project.tracker.entity.AdminTeam;
import com.project.tracker.entity.Comment;
import com.project.tracker.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @RestController is used to specify the creation of RESTful services.
 * This is a AdminController class which contains all the methods implemented by the admin.
 */
@RestController
public class AdminController {

    @GetMapping("/viewTickets/priority")
    public ResponseEntity<?> viewAllTicketsByPriority()
    {
        List<TicketShow> tickets = adminService.viewAllTicketsByPriority();
        if(tickets.isEmpty())
            return new ResponseEntity<>(
                    new Response("No Tickets Available"),HttpStatus.NOT_FOUND
            );

        else{
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }

    }


    /**
     * @Autowired annotation is used in setter methods to inject the value of the class properties.
     */
    @Autowired
    private AdminService adminService;


    /**
     * @GetMapping annotated methods handle the HTTP GET requests matched with the URI given.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to view all the tickets.
     * @return
     */
    @GetMapping("/viewTickets")
    public ResponseEntity<?> viewAllTickets()
    {
        List<TicketShow> tickets = adminService.viewAllTickets();
        if(tickets.isEmpty())
            return new ResponseEntity<>(
                    new Response("No Tickets Available"),HttpStatus.NOT_FOUND
            );

        else{
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }

    }

    /**
     * @GetMapping annotated methods handle the HTTP GET requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to view the ticket based on ticketID.
     * @return
     */
    @GetMapping("/View/Tickets/byId")
    public ResponseEntity<?> viewTicketById(@RequestParam("ticketId") int ticketId)
    {
        return new ResponseEntity<>(adminService.viewTicket(ticketId),HttpStatus.OK);
    }


    /**
     * @PutMapping annotated methods handle the HTTP PUT requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * @RequestBody is used to read the data provided by a user and bind it to the request body.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to set the assignee ID to the ticket.
     * @return
     */
    @PutMapping("/setAssignee")
    public ResponseEntity<?> setAssignee(@RequestParam("userId") int userId,@RequestParam("ticketId")int ticketId,@RequestBody AdminTeam adminTeam)
    {
        return new ResponseEntity<>(adminService.setAssignee(userId,ticketId,adminTeam),HttpStatus.OK);
    }


    /**
     * @PutMapping annotated methods handle the HTTP PUT requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to change the status of the ticket.
     * @return
     */
    @PutMapping("/changeStatus")
    public ResponseEntity<?> changestatus(@RequestParam("ticketId") int ticketId, @RequestParam("statusId") int statusId, @RequestParam("userId") int userId)
    {
        return new ResponseEntity<>(adminService.changeStatus(ticketId,statusId,userId),HttpStatus.OK);
    }

    /**
     * @PostMapping annotated methods handle the HTTP POST requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * @RequestBody is used to read the data provided by a user and bind it to the request body.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to add and save comments on the ticket.
     * @return
     */
    @PostMapping("/save/comment")
    public ResponseEntity<?> saveComment(@RequestParam("ticketId") int ticketId,@RequestParam("userId") int userId,@RequestBody
        Comment comment)
    {
        return new ResponseEntity<>(adminService.sendComment(ticketId,userId,comment),HttpStatus.OK);
    }


}
