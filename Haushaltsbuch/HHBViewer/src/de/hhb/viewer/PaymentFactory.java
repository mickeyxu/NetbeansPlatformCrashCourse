/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.viewer;

import de.hhb.domain.AccountBook;
import de.hhb.domain.Payment;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author s201126
 */
public class PaymentFactory extends ChildFactory<Payment>{

    private AccountBook ab;

    public PaymentFactory(AccountBook ab) {
        this.ab = ab;
    }
    
    
    @Override
    protected boolean createKeys(List<Payment> list) {
        list.addAll(ab.getPayment());
        
        return true;
    }

    @Override
    protected Node createNodeForKey(Payment key) {
        PaymentNode node = null;
        try {
            node = new PaymentNode(key, Children.create(new ItemFactory(key), true));
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        return node;
    }
    
    
    
    
    
}
