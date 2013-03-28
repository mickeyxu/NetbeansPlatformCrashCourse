/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.domain;

import java.util.List;

/**
 *
 * @author s201126
 */
public class Payment {
    
    String bankName;
    List<Item> items;

    public Payment(String bankName, List<Item> items) {       
        this.items = items;
        this.bankName = bankName;
    }

   
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    
    
}
