package com.example.amazonclone.Service;

import com.example.amazonclone.Model.Merchant;
import com.example.amazonclone.Model.MerchantStock;
import com.example.amazonclone.Model.Product;
import com.example.amazonclone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    int purchases = 5;
    List<User> users = new ArrayList<>();
    HashMap<String,Integer> giftcards = new HashMap<String, Integer>();

    final private ProductService productService;
    final private MerchantStockService merchantStockService;
    final private MerchantService merchantService;
    public void addUser(User user){
        users.add(user);
    }

    public List<User> getUsers(){
        return users;
    }

    public boolean updateUser(String id , User user){
        for(int i = 0; i <users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for(int i = 0 ; i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
    public User search(String id){
        for(User user: users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

        public int buy(String userid,String productid,String merchantid){
            User u = search(userid);
            Product p  = productService.search(productid);
            Merchant m = merchantService.search(merchantid);
            MerchantStock ms = merchantStockService.searchByids(merchantid,productid);
            if(u== null || p == null || m == null||ms==null){
                return 1;
            }else if(ms.getStock()==0){
                return 2;
            }else if(u.getBalance()<p.getPrice()){
                return 3;
            }else{

                ms.setStock(ms.getStock()-1);
                u.setBalance(u.getBalance()-p.getPrice());
                this.updateUser(userid,u);
                merchantStockService.updateMerchantStock(ms.getId(),ms);
                return 4;
            }
        }



    // generate a gift card
    public String generateCard(String id,int value){
        User u = this.search(id);
        if(u == null){
            return "notfound";
        }else if(u.getBalance()<value){
            return "notenough";
        }else if(value<=0){
            return"negative";
        }else{
            Random rand = new Random();
            int r1 = rand.nextInt(1,9);
            int r2 = rand.nextInt(1,9);
            int r3 = rand.nextInt(1,9);
            int r4 = rand.nextInt(1,9);
            String cardnum = ""+r1+r2+r3+r4;
            giftcards.put(cardnum,value);
            u.setBalance(u.getBalance()-value);
            this.updateUser(id,u);
            return"Card number "+cardnum;
        }
    }

    // redeem a gift card
    public int redeemCard(String id,String cardnumber){
        User u = this.search(id);
        if(u == null){
            return 1;
        }else if(!giftcards.containsKey(cardnumber)) {
            return 2;
        }else{
            u.setBalance(u.getBalance()+giftcards.get(cardnumber));
            giftcards.remove(cardnumber);
            this.updateUser(id,u);
            return 3;
        }

    }
}
