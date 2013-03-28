/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.domain;

/**
 *
 * @author s201126
 */
public class Item {
    
    String name;
    float amount;

    public Item(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
    
    
    
}
