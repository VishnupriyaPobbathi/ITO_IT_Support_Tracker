package com.project.tracker.Repository;

import com.project.tracker.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Repository annotation indicates that a class is a repository.
 * In order to define SQL to execute for a spring data repository method, we can annotate the method with @Query Annotation.
 * The @Query annotation can only be used to annotate repository interface methods.
 * Here, SubCategoryRepository extends with its parent class JpaRepository which contains all the methods to manipulate the data.
 */
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {
    @Query("select s from SubCategory s where s.subCategoryDesc=?1")
    SubCategory findBySubCategoryDescription(String desc);

    @Query("select s from SubCategory s where s.subCategoryId=?1")
    SubCategory findByPriorityId(Integer id);
}
