/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.viewer;

import de.hhb.domain.Item;
import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;

/**
 *
 * @author s201126
 */
public class ItemNode extends BeanNode<Item>{

    public ItemNode(Item bean) throws IntrospectionException {
        super(bean);
        setDisplayName(bean.getName());
    }
    
}
