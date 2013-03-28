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
public class AccountBook {
    
    String description;
    int type;
    List<Payment> payment;

    public AccountBook(String description, int type, List<Payment> payment) {
        this.description = description;
        this.type = type;
        this.payment = payment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    
}
