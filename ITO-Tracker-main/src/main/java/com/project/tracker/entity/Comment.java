package com.project.tracker.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
/**
 * This is the POJO class "Comment" defined for the database Comment.
 * @Entity annotation specifies that the class is an entity and is mapped to a database table.
 * @Data annotation generates getters and setters for all the fields, toString(), constructor, etc.
 * @Getter is used to generate getters for all the private fields.
 * @Setter is used to generate setters for all the private fields.
 */
public class Comment {
//    Comment (comment_id, 	ticket_id, user_id, message
    /**
     * @Id specifies the primary key of the entity.
     * @GeneratedValue provides specification of generation strategies for the values of primary keys.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private int ticketId;
    private int userId;
    private String message;
}

