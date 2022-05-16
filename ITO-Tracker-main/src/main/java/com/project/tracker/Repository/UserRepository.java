package com.project.tracker.Repository;

import com.project.tracker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Service annotation is used with the classes that provide some business functionalities.
 * Here, UserRepository extends with its parent class JpaRepository which contains all the methods to manipulate the data.
 */
@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

}
