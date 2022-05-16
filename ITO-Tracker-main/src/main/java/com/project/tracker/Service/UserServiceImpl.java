package com.project.tracker.Service;

import com.project.tracker.Exception.UserNotFoundException;
import com.project.tracker.Repository.*;
import com.project.tracker.Utility.TicketDisplay;
import com.project.tracker.Utility.TicketShow;
import com.project.tracker.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is a class which is implemented by the user service
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * @Autowired annotation is used in setter methods to inject the value of the class properties.
     */

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PriorityRepository priorityRepository;


    /**
     * This is a method which is implemented by the interface called user service
     * It is used to create the ticket
     * @param ticket
     * @param userID
     * @return
     */

        @Override
        public Ticket createTicket(Ticket ticket,int userID) {

            //SubCategory record = subCategoryRepository.findBySubCategoryDescription("Open");
            Status s = statusRepository.findByStatusDescription("open");
            Optional<Users> u = userRepository.findById(userID);
            if(u.isPresent())
            {
                ticket.setReportedId(userID);
                ticket.setStatusId(s.getStatusId());
                ticket.setCreateDateTime(LocalDateTime.now());
                ticketRepository.save(ticket);
            }else
            {
                throw new UserNotFoundException("501","User with the Given ID :" +userID + "Not present");
            }
            return ticket;
        }


    /**
     * This is a method which is implemented by the interface called user service
     * used to fetch the tickets by using userId
     * @param userId
     * @return
     */
    @Override
    public List<TicketShow> ViewTickets(Integer userId) {

        List<TicketShow> ticketShow = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        for(Ticket i:tickets)
        {
            int categoryId = i.getCategoryId();
            int subCategoryId = i.getSubCategoryId();
            int priorityId = i.getPriorityId();
            int statusId = i.getStatusId();
            int assigneeId = i.getAssigneeId();

            Optional<Category> c = categoryRepository.findById(categoryId);
            Optional<SubCategory> s = subCategoryRepository.findById(subCategoryId);
            Optional<Priority> p = priorityRepository.findById(priorityId);
            Optional<Status> st = statusRepository.findById(statusId);
            Optional<AdminTeam> Admin = adminRepository.findById(assigneeId);

            Category category = c.get();
            SubCategory subCategory = s.get();
            Priority priority=p.get();
            Status status = st.get();

            if(Admin.isEmpty())
            {
                ticketShow.add(new TicketShow(i.getTicketId(),category.getCategoryDesc(),subCategory.getSubCategoryDesc(),i.getSubject(),priority.getPriorityName(),status.getStatusName(),null,"http://localhost:8081/fetch/ticket?id="+i.getTicketId()));
            }else
            {
                AdminTeam adminTeam = Admin.get();
                ticketShow.add(new TicketShow(i.getTicketId(),category.getCategoryDesc(),subCategory.getSubCategoryDesc(),i.getSubject(),priority.getPriorityName(),status.getStatusName(),adminTeam.getName(),"http://localhost:8081/fetch/ticket?id="+i.getTicketId()));
            }
        }
        return ticketShow;
    }

    /**
     * This is a method which is implemented by the interface called user service
     * It is used to fetch the ticket by using ticket Id
     * @param ticketId
     * @return
     */
    @Override public TicketDisplay viewById(Integer ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        Ticket t = ticket.get();

        TicketDisplay td = null;

        int categoryId = t.getCategoryId();
        int subCategoryId = t.getSubCategoryId();
        int priorityId = t.getPriorityId();
        int statusId = t.getStatusId();
        int assigneeId = t.getAssigneeId();
        int reportedId = t.getReportedId();


        Optional<Category> c = categoryRepository.findById(categoryId);
        Optional<SubCategory> s = subCategoryRepository.findById(subCategoryId);
        Optional<Priority> p = priorityRepository.findById(priorityId);
        Optional<Status> st = statusRepository.findById(statusId);
        Optional<Users> user = userRepository.findById(reportedId);
        Optional<AdminTeam> admin = adminRepository.findById(assigneeId);

        Category category = c.get();
        SubCategory subCategory = s.get();
        Priority priority = p.get();
        Status status = st.get();
        Users users = user.get();


        if(admin.isEmpty()) {
            td =
                    new TicketDisplay(t.getTicketId(), category.getCategoryDesc(), subCategory.getSubCategoryDesc(),
                            null, users.getName(), t.getSubject(), t.getDescription(),
                            status.getStatusName(), priority.getPriorityName(), t.getCreateDateTime(),
                            t.getLastModifiedDateTime());
        }else
        {
            AdminTeam adminTeam = admin.get();
            td =
                    new TicketDisplay(t.getTicketId(), category.getCategoryDesc(), subCategory.getSubCategoryDesc(),
                            adminTeam.getName(), users.getName(), t.getSubject(), t.getDescription(),
                            status.getStatusName(), priority.getPriorityName(), t.getCreateDateTime(),
                            t.getLastModifiedDateTime());
        }

        return td;
    }

    /**
     * This is a method which is implemented by the interface called user service
     * In this method, Sending Ticket Id and user Id in request param and message in request body to comment on Ticket in the Comment table
     * @param ticketId
     * @param userId
     * @param comment
     * @return
     */
    @Override
    public Comment sendComment(int ticketId,int userId,Comment comment) {

        Ticket ticket = ticketRepository.findTicketByticketAndUserId(ticketId, userId);
        if(ticket!=null) {
            comment.setTicketId(ticket.getTicketId());
            comment.setUserId(userId);
            commentRepository.save(comment);
        }
        return comment;
    }
}
