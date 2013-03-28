/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.viewer;

import de.hhb.domain.AccountBook;
import de.hhb.domain.Bank;
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
public class BankFactory extends ChildFactory<Bank>{

    private final AccountBook ab;

    public BankFactory(AccountBook ab) {
        this.ab = ab;
    }    
    
    
    @Override
    protected boolean createKeys(List<Bank> list) {
        list.add(new Bank("Deutsche Bank"));
        list.add(new Bank("Post Bank"));
        list.add(new Bank("Hypovereinsbank"));
//       
//        list.addAll(ab.getBanks());
        return true;
    }

    @Override
    protected Node createNodeForKey(Bank key) {
        BankNode bankNode = null; //To change body of generated methods, choose Tools | Templates.
        try {
            bankNode = new BankNode(key, Children.create(new PaymentFactory(ab), true));
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        return bankNode;
    }
    
    
            
    
}
