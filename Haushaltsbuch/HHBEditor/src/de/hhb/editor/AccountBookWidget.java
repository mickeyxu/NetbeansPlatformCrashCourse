/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.editor;

import de.hhb.domain.AccountBook;
import java.awt.Point;
import java.awt.event.KeyEvent;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.awt.StatusDisplayer;

/**
 *
 * @author Administrator
 */

 public class AccountBookWidget extends VMDNodeWidget {

     AccountBook ab;
     
    public AccountBookWidget(final Scene scene, AccountBook ab, Point point) {
        super(scene);
        this.ab = ab;
        setNodeName(this.ab.getDescription());
        setPreferredLocation(point);
        getActions().addAction(ActionFactory.createMoveAction()); 
        getActions().addAction(new AccountBookWidget.KeyboardMoveAction());
        getActions().addAction(ActionFactory.createSelectAction(new SelectProvider() {
            @Override
            public boolean isAimingAllowed(Widget widget, Point localLocation, boolean invertSelection) {
                return true;
            }
            @Override
            public boolean isSelectionAllowed(Widget widget, Point localLocation, boolean invertSelection) {
                return true;
            }
            @Override
            public void select(Widget widget, Point localLocation, boolean invertSelection) {
                scene.setFocusedWidget(widget);
            }
        }));
    }

    private final class KeyboardMoveAction extends WidgetAction.Adapter {
        private MoveProvider provider;
        private KeyboardMoveAction() {
            this.provider = ActionFactory.createDefaultMoveProvider();
        }
        @Override
        public WidgetAction.State keyPressed(Widget widget, WidgetAction.WidgetKeyEvent event) {
            Point originalSceneLocation = provider.getOriginalLocation(widget);
            int newY = originalSceneLocation.y;
            int newX = originalSceneLocation.x;
            if (event.getKeyCode() == KeyEvent.VK_UP) {
                newY = newY - 20;
            } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                newY = newY + 20;
            } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                newX = newX + 20;
            } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                newX = newX - 20;
            } else if (event.getKeyCode() == KeyEvent.VK_DELETE){
                widget.removeFromParent();
                StatusDisplayer.getDefault().setStatusText(widget.toString() + " Deleted!!! ");
            }
            provider.movementStarted(widget);
            provider.setNewLocation(widget, new Point(newX, newY));
            return WidgetAction.State.CONSUMED;
        }
        @Override
        public WidgetAction.State keyReleased(Widget widget, WidgetAction.WidgetKeyEvent event) {
            provider.movementFinished(widget);
            return WidgetAction.State.REJECTED;
        }
    }

    @Override
    public String toString() {
        return ab.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }

    
}

