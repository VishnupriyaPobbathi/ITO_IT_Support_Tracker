package com.project.tracker.Service;

import com.project.tracker.Utility.TeamTicketDisplay;
import com.project.tracker.Utility.TicketShow;
import com.project.tracker.entity.AdminTeam;
import com.project.tracker.entity.Comment;
import com.project.tracker.entity.Ticket;

import java.util.List;

/**
 * This interface is used to specify methods that classes must implement
 */
public interface AdminService {

    List<TicketShow> viewAllTicketsByPriority();

    List<TicketShow> viewAllTickets();

    TeamTicketDisplay viewTicket(Integer ticketId);

    Ticket setAssignee(Integer ticketId, int id, AdminTeam adminTeam);

    Ticket changeStatus(Integer ticketId,Integer statusId,Integer userId);

    Comment sendComment(Integer ticketId, Integer userId, Comment comment);
}
