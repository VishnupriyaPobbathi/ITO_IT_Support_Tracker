package com.project.tracker.Repository;

import com.project.tracker.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority,Integer> {
    @Query("select p from Priority p where p.priorityName=?1")
    Priority findByPriorityName(String priorityName);

    @Query("select p from Status p where p.priorityId=?1")
    Priority findByPriorityId(Integer id);

    @Query("select p from Priority p order by p.priorityId=?1 desc")
    Priority findByPriority(String priorityName);
}
