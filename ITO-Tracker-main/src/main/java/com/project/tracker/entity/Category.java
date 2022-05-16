package com.project.tracker.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
/**
 * This is the POJO class "Category" defined for the database Category.
 * @Entity annotation specifies that the class is an entity and is mapped to a database table.
 * @Data annotation generates getters and setters for all the fields, toString(), constructor, etc.
 * @Getter is used to generate getters for all the private fields.
 * @Setter is used to generate setters for all the private fields.
 */
public class Category {
    /**
     * @Id specifies the primary key of the entity.
     * @GeneratedValue provides specification of generation strategies for the values of primary keys.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryDesc;
}
