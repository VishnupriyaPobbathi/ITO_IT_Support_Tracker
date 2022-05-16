package com.project.tracker.Repository;

import com.project.tracker.entity.AdminTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Repository annotation indicates that a class is a repository.
 * In order to define SQL to execute for a spring data repository method, we can annotate the method with @Query Annotation.
 * The @Query annotation can only be used to annotate repository interface methods.
 * Here, AdminRepository extends with its parent class JpaRepository which contains all the methods to manipulate the data.
 */
@Repository
public interface AdminRepository extends JpaRepository<AdminTeam,Integer> {

    @Query("select c from AdminTeam c where c.emailId =?1")
    AdminTeam findByEmail(String emailId);
}
