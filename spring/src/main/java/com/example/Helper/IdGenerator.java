package com.example.Helper;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IdGenerator {

    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    public String getTotalPrice(String price, int quantity){
        String p = price;
        int priceInt = Integer.valueOf(p.replace(",", ""));
        System.out.println(priceInt);
        String totalp = String.valueOf(priceInt*quantity);
        String req = "";
        int len = totalp.length();
        System.out.println(len);
        int left = len-1, i = 0;
        req += totalp.charAt(i);
        i++;
        while(left > 0){
            if(left % 2 == 1 && left >= 3){
                req += ",";
            }
            req += totalp.charAt(i);
            i++;
            left--;
        }
        System.out.println(req);
        return req;
    }
}
