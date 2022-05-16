package com.project.tracker.Utility;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
/**
 *@Data annotation generates getters and setters for all the fields, toString(), constructor, etc.
 *@Getter is used to generate getters for all the private fields.
 *This is class TicketShow which displays the required ticket details and is very similar to the TeamTicketDisplay class.
 *This class generates constructors for the fields.
 */
public class TicketShow {
  /*  •	Ticket_id
•	Category
•	Subcategory
•	Subject
•	Priority
•	Status
•	Assignee
•	Link for ticket*/
    private int ticketId;
    private String category;
    private String subCategory;
    private String subject;
    private String priority;
    private String status;
    private String Assignee;
    private String url;

    public TicketShow(int ticketId, String category, String subCategory, String subject, String priority, String status,
                      String assignee, String url) {
        this.ticketId = ticketId;
        this.category = category;
        this.subCategory = subCategory;
        this.subject = subject;
        this.priority = priority;
        this.status = status;
        this.Assignee = assignee;
        this.url = url;
    }
}
