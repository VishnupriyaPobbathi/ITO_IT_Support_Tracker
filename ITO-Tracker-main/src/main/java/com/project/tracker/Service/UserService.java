package com.project.tracker.Service;

import com.project.tracker.Utility.TicketDisplay;
import com.project.tracker.Utility.TicketShow;
import com.project.tracker.entity.Comment;
import com.project.tracker.entity.Ticket;

import java.util.List;

/**
 * This interface is used to specify methods that classes must implement
 */
public interface UserService {

    public Ticket createTicket(Ticket ticket, int userId);

    public List<TicketShow> ViewTickets(Integer userId);

    public TicketDisplay viewById(Integer ticketId);

    public Comment sendComment(int ticketId,int userId,Comment comment);
}
