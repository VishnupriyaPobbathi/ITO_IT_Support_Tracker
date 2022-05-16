package com.project.tracker.Repository;

import com.project.tracker.entity.Status;
import com.project.tracker.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {
    @Query("select s from Status s where s.statusName=?1")
    Status findByStatusDescription(String statusName);

    @Query("select s from Status s where s.statusId=?1")
    Status findByPriorityId(Integer id);
}
