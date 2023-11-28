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
public class MerchantStockService {

    List<MerchantStock> merchantStocks = new ArrayList<>();

    public List<MerchantStock> getMerchantStocks(){
        return merchantStocks;
    }


    public boolean addMerchantStocks(MerchantStock merchantStock){
        if(merchantStock.getStock()<10){
            return false;
        }
        merchantStocks.add(merchantStock);
        return true;
    }


    public boolean updateMerchantStock(String id, MerchantStock merchantStock){
        for(int i = 0 ;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }

        return  false;
    }


    public  boolean deleteMerchantStock(String id ,String merchantId){
        for(int i = 0; i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)&&merchantStocks.get(i).getMerchantid().equals(merchantId)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }


    public MerchantStock searchforMerchant(String id){
        for (MerchantStock merchantStock: merchantStocks){
            if(merchantStock.getMerchantid().equals(id))return merchantStock;
        }
        return  null;
    }

    public MerchantStock searchByids(String merchantid,String productid){
        for(MerchantStock merchantStock: merchantStocks){
            if(merchantStock.getMerchantid().equals(merchantid)&&merchantStock.getProductid().equals(productid)){
                return merchantStock;
            }
        }
        return null;
    }
}
