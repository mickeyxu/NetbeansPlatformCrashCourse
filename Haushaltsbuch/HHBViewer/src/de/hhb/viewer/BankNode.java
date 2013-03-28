/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.viewer;

import de.hhb.domain.Bank;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;


/**
 *
 * @author s201126
 */
public class BankNode extends BeanNode<Bank>{

    public BankNode(Bank bean) throws IntrospectionException {
        super(bean);
        setDisplayName(bean.getBankName());
    }

    public BankNode(Bank bean, Children children) throws IntrospectionException {
        super(bean, children);
        setDisplayName(bean.getBankName());
    }
    
    

}
