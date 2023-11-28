package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService {

    List<Category> categories = new ArrayList<>();

    public List<Category> getCategories(){
        return categories;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public boolean updateCategory(String id, Category category){
        for(int i = 0; i<categories.size();i++){
            if(categories.get(i).getId().equals(id)){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for(int i = 0 ; i<categories.size();i++){
            if(categories.get(i).getId().equals(id)){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public Category search(String categoryname){
        for(Category c : categories){
            if(c.getName().equals(categoryname)) return c;
        }
        return null;
    }
}
