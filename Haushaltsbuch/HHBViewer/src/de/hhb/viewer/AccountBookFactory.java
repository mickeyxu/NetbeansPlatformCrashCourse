/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.viewer;

import de.hhb.domain.AccountBook;
import de.hhb.domain.Item;
import de.hhb.domain.Payment;
import java.beans.IntrospectionException;
import java.util.Arrays;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author s201126
 */
public class AccountBookFactory extends ChildFactory<AccountBook>{

    @Override
    protected boolean createKeys(List<AccountBook> list) {
//        list.add(new AccountBook("Mike Accounts", 10, 
//                Arrays.asList(new Bank("Deutsche Bank"), new Bank("Post Bank"))));
//        list.add(new AccountBook("Cherry Accounts", 1, 
//                Arrays.asList(new Bank("Deutsche Bank"), new Bank("Post Bank"))));
//        list.add(new AccountBook("Jen Accounts", 0,
//                Arrays.asList(new Bank("Hypovereinsbank"), new Bank("Post Bank"))));
//        list.add(new AccountBook("Michael Accounts", 1, 
//                Arrays.asList(new Bank("Deutsche Bank"), new Bank("Hypovereinsbank"))));

        list.add(new AccountBook("Mike Accounts", 10, 
                Arrays.asList(new Payment("Deutsche Bank", 
                    Arrays.asList(new Item("Auto", 2000), new Item("Essen", 200))))));
                    

        list.add(new AccountBook("Cherry Accounts", 1,
                 Arrays.asList(new Payment("Post Bank", 
                    Arrays.asList(new Item("Flug", 1000), new Item("Essen", 100))))));

        list.add(new AccountBook("Jen Accounts", 0,
                 Arrays.asList(new Payment("Hypovereinsbank", 
                    Arrays.asList(new Item("Auto", 4000), new Item("Essen", 500))))));

        list.add(new AccountBook("Michael Accounts", 1,
                 Arrays.asList(new Payment("Deutsche Bank", 
                    Arrays.asList(new Item("Schiff", 8000), new Item("Essen", 50))))));

        return true;
    }

    @Override
    protected Node createNodeForKey(AccountBook key) {

        AccountBookNode node = null;
        
        try {
            node = new AccountBookNode(key,Children.create(new PaymentFactory(key), true), Lookups.singleton(key));
//            node = new AccountBookNode(key,Children.create(new BankFactory(key), true));
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        return node;
    }
    
    
}
