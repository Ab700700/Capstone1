package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Category;
import com.example.amazonclone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    List<Product> products = new ArrayList<>();
    final private CategoryService categoryService;
    public List<Product> getProducts(){
        return products;
    }
    public Product getProduct(String id){
        for(Product p : products){
            if(p.getId().equals(id)) return p;
        }
        return null;
    }
    public void addProduct(Product product){
        products.add(product);
    }

    public boolean updateProduct(String id, Product product){
        for(int i = 0 ; i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id){
        for(int i = 0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product search(String id ){
        for(Product p :products){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public List<Product> searchbyCategory(String category){
        List<Product> newList = new ArrayList<>();
        Category ca =  categoryService.search(category);
        if(ca == null){
            return new ArrayList<>();
        }
        for(Product p : products){
            if(p.getCategoryID().equals(ca.getId())) newList.add(p);
        }
        return newList;
    }
}
