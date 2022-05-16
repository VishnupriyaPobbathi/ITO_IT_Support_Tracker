package com.project.tracker.Utility;

import java.time.LocalDateTime;

/**
 * This is TicketDisplay class which displays the required ticket details by generating all the
 * required getters and setters , constructors, etc.
 */
public class TicketDisplay {
 /*   "ticketId": 5,
        "categoryId": 1,
        "subCategoryId": 1,
        "assigneeId": 0,
        "reportedId": 1,
        "subject": "Hardware",
        "description": "allocate Hardware",
        "statusId": 13,
        "priorityId": 1,
        "createDateTime": "2022-04-30T17:27:07",
        "lastModifiedDateTime": null*/
    private int ticketId;
    private String category;
    private String subCategory;
    private String assignee;
    private String reported;
    private String subject;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedTime;

    public TicketDisplay(int ticketId, String category, String subCategory, String assignee, String reported,
                         String subject, String description, String status, String priority, LocalDateTime createDateTime,
                         LocalDateTime lastModifiedTime) {
        this.ticketId = ticketId;
        this.category = category;
        this.subCategory = subCategory;
        this.assignee = assignee;
        this.reported = reported;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createDateTime = createDateTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReported() {
        return reported;
    }

    public void setReported(String reported) {
        this.reported = reported;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
