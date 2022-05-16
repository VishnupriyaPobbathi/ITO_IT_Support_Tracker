package com.project.tracker.Controller;

import com.project.tracker.Exception.ResourceNotFoundException;
import com.project.tracker.Repository.CommentRepository;
import com.project.tracker.Repository.SubCategoryRepository;
import com.project.tracker.Repository.TicketRepository;
//import com.project.tracker.Repository.UserRepository;
import com.project.tracker.Exception.UserNotFoundException;
import com.project.tracker.Repository.UserRepository;
import com.project.tracker.Response;
import com.project.tracker.Service.UserService;
import com.project.tracker.Utility.TicketDisplay;
import com.project.tracker.Utility.TicketShow;
import com.project.tracker.entity.Comment;
import com.project.tracker.entity.SubCategory;
import com.project.tracker.entity.Ticket;
import com.project.tracker.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @RestController is used to specify the creation of RESTful services.
 * This is a TicketController class which contains all the methods implemented by the User.
 */
@RestController
public class TicketController {
    /**
     * @Autowired annotation is used in setter methods to inject the value of the class properties.
     */

    @Autowired TicketRepository ticketRepository;

    @Autowired CommentRepository commentRepository;

    @Autowired UserRepository userRepository;

    @Autowired SubCategoryRepository subCategoryRepository;

    @Autowired
    private UserService userService;



    /**
     * @PostMapping annotated methods handle the HTTP POST requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * @RequestBody is used to read the data provided by a user and bind it to the request body.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to create a ticket.
     * @return
     */


        @PostMapping("/createticket")
        public ResponseEntity<?> createTicket(@RequestBody Ticket ticket,@RequestParam("id") int id, HttpServletResponse response)
        {
            Ticket tickets = userService.createTicket(ticket, id);

            if(tickets!=null) {
                return new ResponseEntity<>(new Response("Ticket Created Successfully with ticket Id : " +ticket.getTicketId()+
                        " with ticket URL : " +response.encodeURL("http://localhost:8081/View/Tickets/byId?ticketId=" +ticket.getTicketId())), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Response("Ticket was not created successfully"),HttpStatus.OK);

        }

    /**
     * @GetMapping annotated methods handle the HTTP GET requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to view the tickets by requesting parameter user ID .
     * @return
     */

    @GetMapping("/fetch/tickets")
    public ResponseEntity<?> viewTicketsByUserId(@RequestParam("id") int id)
    {
        List<TicketShow> tickets = userService.ViewTickets(id);
        if(tickets==null)
            return new ResponseEntity<>(
                    new Response("Tickets not availaible for the given userId :" +id),HttpStatus.NOT_FOUND
            );

        return new ResponseEntity<>(tickets,HttpStatus.OK);
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
    @PostMapping("/save/Comment")
    public ResponseEntity<?> saveComment(@RequestParam("ticketId") int ticketId,@RequestParam("userId") int userId,@RequestBody
            Comment comment) {
        Comment comments = userService.sendComment(ticketId, userId, comment);
        Optional<Users> user = userRepository.findById(userId);
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (user.isPresent() && ticket.isPresent()) {
            return new ResponseEntity<>(new Response
                    ("Successfully added comment on ticket" + ticketId), HttpStatus.OK);
            //return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new Response
                    ("Invalid id"),HttpStatus.NOT_FOUND);
        }

    }

    /**
     * @GetMapping annotated methods handle the HTTP GET requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to values which is in the User table.
     * @return
     */

      @GetMapping("/Fetch")
      public ResponseEntity<?> findUsersById(@RequestParam("Uid") Integer id)
      {
          Optional<Users> user = userRepository.findById(id);
         if(user.isPresent()) {
              Users users = user.get();
              return new ResponseEntity<>(users,HttpStatus.OK);
          }else
          {
              throw new ResourceNotFoundException("User with the given ID : " +id + " Not found. ");
          }


      }

    /**
     * @GetMapping annotated methods handle the HTTP GET requests matched with the URI given.
     * The below method is used to fetch the categories and sub categories.
     * @return
     */
      //fetching the categories and sub_categories
      @GetMapping("/fetch/SubCategory")
      public List<SubCategory> fetchList()
      {
            return subCategoryRepository.findAll();
      }

    /**
     * @GetMapping annotated methods handle the HTTP GET requests matched with the URI given.
     * @RequestParam is used to read the data provided by a user and bind it to the request parameter.
     * ResponseEntity represents an HTTP response, including body , status codes, etc.
     * ResponseEntity is used when you need to change HTTP status code based on the request or some logic.
     * The below method is used to fetch the tickets by its ticket ID.
     * @return
     */

      @GetMapping("/fetch/ticket")
      public ResponseEntity<?> viewTicketByID(@RequestParam("id") int id)
      {
          TicketDisplay ticket = userService.viewById(id);
          return new ResponseEntity<>(ticket,HttpStatus.OK);
      }

}
