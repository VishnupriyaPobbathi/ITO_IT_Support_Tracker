package com.project.tracker.Repository;

import com.project.tracker.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository annotation indicates that a class is a repository.
 * In order to define SQL to execute for a spring data repository method, we can annotate the method with @Query Annotation.
 * The @Query annotation can only be used to annotate repository interface methods.
 * Here, TicketRepository extends with its parent class JpaRepository which contains all the methods to manipulate the data.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
   @Query("select s from Ticket s where s.reportedId=?1")
    List<Ticket> findByUserId(Integer userId);

    @Query("select s from Ticket s where s.ticketId = ?1 and s.reportedId=?2")
    Ticket findTicketByticketAndUserId(int ticketId,int userId);
}
