package com.project.tracker.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
/**
 * This is the POJO class "Ticket" defined for the database Ticket.
 * @Entity annotation specifies that the class is an entity and is mapped to a database table.
 * @Data annotation generates getters and setters for all the fields, toString(), constructor, etc.
 * @Getter is used to generate getters for all the private fields.
 * @Setter is used to generate setters for all the private fields.
 */
public class Ticket {
//    Ticket (ticket_id, category_id, sub-category_id, assignee_Id, reported_Id, subject, description, status_id, priority_id, create_datetime, last_modified_datetime)
    /**
     * @Id specifies the primary key of the entity.
     * @GeneratedValue provides specification of generation strategies for the values of primary keys.
     */
    @Id
    @GeneratedValue
    private int ticketId;
    private int categoryId;
    private int subCategoryId;
    private int assigneeId;
    private int reportedId;
    private String subject;
    private String description;
    private int statusId;
    private int priorityId;
    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedDateTime;
}
