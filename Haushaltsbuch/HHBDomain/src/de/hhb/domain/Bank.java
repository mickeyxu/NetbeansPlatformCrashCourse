/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.domain;

/**
 *
 * @author s201126
 */
public class Bank {
    
    String BankName;

    public Bank(String BankName) {
        this.BankName = BankName;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }
    
    
    
}
