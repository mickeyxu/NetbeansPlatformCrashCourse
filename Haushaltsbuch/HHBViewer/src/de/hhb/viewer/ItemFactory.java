/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.viewer;

import de.hhb.domain.Item;
import de.hhb.domain.Payment;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author s201126
 */
public class ItemFactory extends ChildFactory<Item>{

    private final Payment payment;

    public ItemFactory(Payment payment) {
        this.payment = payment;
    }
    
    
    
    @Override
    protected boolean createKeys(List<Item> list) {
        list.addAll(payment.getItems());
        
        return true;
    }

    @Override
    protected Node createNodeForKey(Item key) {
        ItemNode node = null;
        try {
            node = new ItemNode(key);
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        return node;
        
    }
    
    
    
    
}
