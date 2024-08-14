package com.example.lap11.Repository;

import com.example.lap11.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoriesByCategoryId(Integer categoryId);


    @Query("select c from Category c where c.name=?1") //1-extra endpoint
    Category pleaseSearchByName(String name);

}
