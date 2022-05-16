package com.project.tracker.Service;

import com.project.tracker.Repository.*;
import com.project.tracker.Utility.TeamTicketDisplay;
import com.project.tracker.Utility.TicketShow;
import com.project.tracker.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * This is a class which is implemented by the Admin service
 */
@Service
public class AdminServiceImpl implements AdminService{
    /**
     *@Autowired annotation is used in setter methods to inject the value of the class properties.
     */
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PriorityRepository priorityRepository;


    @Override
    public List<TicketShow> viewAllTicketsByPriority() {
        return null;
    }



    /**
     *This is a method which is implemented by the interface called Admin Service
     * This method is to show all the tickets
     * @return
     */
    @Override public List<TicketShow> viewAllTickets() {

        List<Ticket> Tickets = ticketRepository.findAll();
        List<TicketShow> ticketShow = new ArrayList<>();

        for(Ticket i:Tickets)
        {
            int categoryId = i.getCategoryId();
            int subCategoryId = i.getSubCategoryId();
            int priorityId = i.getPriorityId();
            int statusId = i.getStatusId();
            int assigneeId = i.getAssigneeId();

            Optional<Category> c = categoryRepository.findById(categoryId);
            Optional<SubCategory> s = subCategoryRepository.findById(subCategoryId);
            Optional<SubCategory> p = subCategoryRepository.findById(priorityId);
            Optional<SubCategory> st = subCategoryRepository.findById(statusId);
            Optional<AdminTeam> Admin = adminRepository.findById(assigneeId);

            Category category = c.get();
            SubCategory subCategory = s.get();
            SubCategory priority = p.get();
            SubCategory status = st.get();

            if(Admin.isEmpty())
            {
                ticketShow.add(new TicketShow(i.getTicketId(),category.getCategoryDesc(),subCategory.getSubCategoryDesc(),i.getSubject(),priority.getSubCategoryDesc(),status.getSubCategoryDesc(),null,"http://localhost:8081/fetch/ticket?id="+i.getTicketId()));
            }else
            {
                AdminTeam adminTeam = Admin.get();
                ticketShow.add(new TicketShow(i.getTicketId(),category.getCategoryDesc(),subCategory.getSubCategoryDesc(),i.getSubject(),priority.getSubCategoryDesc(),status.getSubCategoryDesc(),adminTeam.getName(),"http://localhost:8081/fetch/ticket?id="+i.getTicketId()));
            }
        }
        return ticketShow;
    }


    /**
     * This is a method which is implemented by the interface called Admin service
     * In this method, Sending TicketId in request param to fetch the ticket on particular ticketId
     * @param ticketId
     * @return
     */
    @Override public TeamTicketDisplay viewTicket(Integer ticketId) {

        Ticket t1 = ticketRepository.findById(ticketId).get();
        int adminId = t1.getAssigneeId();

        int userId = t1.getReportedId();

        List<String> mess = new ArrayList<>();
        List<Comment> comment = commentRepository.findByTicketId(ticketId);

        AdminTeam ad;
        if(adminId == 0) {
            ad = null;
        }else
        {
            ad = adminRepository.findById(adminId).get();
        }

        List<String> messages = new ArrayList<>();
        Map<String,List<String>> comments = new HashMap<>();

        List<Comment> comm = commentRepository.findByAdminId(adminId);
        for(Comment c:comm)
        {
            if(c.getTicketId() == ticketId) {
                mess.add(c.getMessage());
            }
        }

        List<Comment> userComments = commentRepository.findByAdminId(userId);
        for(Comment i:userComments)
        {
            if(i.getTicketId() == ticketId) {
                messages.add(i.getMessage());
            }
            Users user = userRepository.findById(userId).get();

            comments.put(user.getName(),new ArrayList<>(messages));
            if(ad == null)
            {

            }else {
                comments.put(ad.getName(), mess);
            }
        }
        System.out.println("Size of Messages is >>>>>>>>>>>>>>>>>>>>>>" +messages.size());
        Ticket ticket = ticketRepository.findById(ticketId).get();

        int categoryId = ticket.getCategoryId();
        int subCategoryId = ticket.getSubCategoryId();
        int priorityId = ticket.getPriorityId();
        int statusId = ticket.getStatusId();
        int assigneeId = ticket.getAssigneeId();
        int reportedId = ticket.getReportedId();

        Optional<Category> c = categoryRepository.findById(categoryId);
        Optional<SubCategory> s = subCategoryRepository.findById(subCategoryId);
        Optional<SubCategory> p = subCategoryRepository.findById(priorityId);
        Optional<SubCategory> st = subCategoryRepository.findById(statusId);
        Optional<AdminTeam> Admin = adminRepository.findById(assigneeId);
        Optional<Users> user = userRepository.findById(reportedId);

        Category category = c.get();
        SubCategory subCategory = s.get();
        SubCategory priority = p.get();
        SubCategory status = st.get();
        Users users = user.get();
        TeamTicketDisplay t = null;
        if(Admin.isEmpty()) {
            t = new TeamTicketDisplay(ticket.getTicketId(), category.getCategoryDesc(),
                    subCategory.getSubCategoryDesc(), null, users.getName(), ticket.getSubject(), ticket.getDescription(),
                    status.getSubCategoryDesc(), priority.getSubCategoryDesc(), ticket.getCreateDateTime(),
                    ticket.getLastModifiedDateTime(),comments);
        }else
        {
            AdminTeam admin = Admin.get();
            t = new TeamTicketDisplay(ticket.getTicketId(), category.getCategoryDesc(),
                    subCategory.getSubCategoryDesc(), admin.getName(), users.getName(), ticket.getSubject(), ticket.getDescription(),
                    status.getSubCategoryDesc(), priority.getSubCategoryDesc(), ticket.getCreateDateTime(),
                    ticket.getLastModifiedDateTime(),comments);
        }

        return t;
    }

    /**
     * This is a method which is implemented by the interface called Admin service
     * In this method, Sending Ticket Id and user Id in request param and Admin in request body to set the Assignee
     * @param ticketId
     * @param id
     * @param adminTeam
     * @return
     */
    @Override
    public Ticket setAssignee(Integer ticketId, int id, AdminTeam adminTeam) {
        Ticket ticket = ticketRepository.findById(ticketId).get();

        AdminTeam admin = adminRepository.findByEmail(adminTeam.getEmailId());
        if(ticket.getAssigneeId() != 0) {
            System.out.println("Already assigned");
        }
        else{
            ticket.setAssigneeId(admin.getAdminId());
            ticket.setLastModifiedDateTime(LocalDateTime.now());
            ticketRepository.save(ticket);
        }
        return ticket;
    }

    /**
     * This is a method which is implemented by the interface called Admin service
     * In this method, Sending Ticket Id , user Id  and statusId in request param to change the status
     * @param ticketId
     * @param statusId
     * @param userId
     * @return
     */
    @Override public Ticket changeStatus(Integer ticketId, Integer statusId, Integer userId) {
        Ticket ticket = ticketRepository.findTicketByticketAndUserId(ticketId, userId);
        ticket.setStatusId(statusId);
        ticketRepository.save(ticket);
        return ticket;
    }

    /**
     * This is a method which is implemented by the interface called Admin service
     * In this method, Sending Ticket Id and user Id in request param and message in request body which is in comment table to comment on Ticket in the Comment table
     * @param ticketId
     * @param userId
     * @param comment
     * @return
     */
    @Override public Comment sendComment(Integer ticketId, Integer userId, Comment comment) {
        comment.setUserId(userId);
        comment.setTicketId(ticketId);
        commentRepository.save(comment);
        return comment;
    }
}
