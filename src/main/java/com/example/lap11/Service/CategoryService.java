package com.example.lap11.Service;

import com.example.lap11.Api.ApiException;
import com.example.lap11.Model.Category;
import com.example.lap11.Model.User;
import com.example.lap11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Integer categoryId  ,Category category) {
        Category c = categoryRepository.findCategoriesByCategoryId(categoryId);
        if(c==null){
            throw new ApiException("Category not found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }

    public void deleteCategory(Integer categoryId ) {
        Category c = categoryRepository.findCategoriesByCategoryId(categoryId);
        if(c==null){
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(c);
    }

    public Category getCategoryByName(String name){ // 1-extra endpoint
        Category c =categoryRepository.pleaseSearchByName(name);
        if(c==null){
            throw new ApiException("Category by name not found");
        }
        return c;
    }
}
