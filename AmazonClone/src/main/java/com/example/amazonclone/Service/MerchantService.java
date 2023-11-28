package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    List<Merchant> merchants = new ArrayList<>();
    final private MerchantStockService merchantStockService;
    final private ProductService productService;
    public List<Merchant> getMerchants(){
        return merchants;
    }

    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }

    public boolean updateMerchant(String id, Merchant merchant){
        for(int i = 0; i<merchants.size();i++){
            if(merchants.get(i).getId().equals(id)){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }


    public boolean deleteMerchant(String id){
        for(int i = 0 ; i<merchants.size();i++){
            if(merchants.get(i).getId().equals(id)){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    public Merchant search(String id){
        for(Merchant merchant: merchants){
            if(merchant.getId().equals(id)){
                return merchant;
            }
        }
        return null;
    }


    public int addStock(String productid,String merchantid, int amount){
        Product p = productService.getProduct(productid);
        MerchantStock ms = merchantStockService.searchforMerchant(merchantid);
        if(p == null|| ms == null) return 1;
        else {
            ms.setStock(ms.getStock()+amount);
            merchantStockService.updateMerchantStock(ms.getId(),ms);
            return 3;
        }
    }
}
