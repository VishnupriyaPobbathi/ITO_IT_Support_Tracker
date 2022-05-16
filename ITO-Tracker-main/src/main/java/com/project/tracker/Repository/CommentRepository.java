package com.project.tracker.Repository;

import com.project.tracker.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository annotation indicates that a class is a repository.
 * In order to define SQL to execute for a spring data repository method, we can annotate the method with @Query Annotation.
 * The @Query annotation can only be used to annotate repository interface methods.
 * Here, CommentRepository extends with its parent class JpaRepository which contains all the methods to manipulate the data.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query("select c from Comment c where c.ticketId=?1")
    public List<Comment> findByTicketId(int ticketId);

    @Query("select c from Comment c where c.userId=?1")
    public List<Comment> findByAdminId(int id);
}
