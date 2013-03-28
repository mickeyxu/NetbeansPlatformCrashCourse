/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.viewer;

import de.hhb.domain.AccountBook;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author s201126
 */
public class AccountBookNode extends BeanNode<AccountBook>{

    public AccountBookNode(AccountBook bean) throws IntrospectionException {
        super(bean);        
        setDisplayName(bean.getDescription());
    }

    public AccountBookNode(AccountBook bean, Children children) throws IntrospectionException {
        super(bean, children);
        setDisplayName(bean.getDescription());
    }

    public AccountBookNode(AccountBook bean, Children children, Lookup lkp) throws IntrospectionException {
        super(bean, children, lkp);
        setDisplayName(bean.getDescription());
    }
    
    
        
}
