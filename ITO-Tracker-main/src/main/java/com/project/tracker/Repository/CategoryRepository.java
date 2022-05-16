package com.project.tracker.Repository;

import com.project.tracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository annotation indicates that a class is a repository.
 * Here, CategoryRepository extends with its parent class JpaRepository which contains all the methods to manipulate the data.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
