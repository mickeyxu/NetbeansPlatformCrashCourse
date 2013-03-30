/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.editor;

import de.hhb.domain.AccountBook;
import de.hhb.domain.Payment;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.api.visual.action.AcceptProvider;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.action.ConnectorState;
import org.netbeans.api.visual.action.MoveProvider;
import org.netbeans.api.visual.action.PopupMenuProvider;
import org.netbeans.api.visual.action.SelectProvider;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.vmd.VMDPinWidget;
import org.netbeans.api.visual.widget.EventProcessingType;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//de.hhb.editor//Editor//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "EditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "de.hhb.editor.EditorTopComponent")
@ActionReference(path = "Menu/Window/Views" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_EditorAction",
        preferredID = "EditorTopComponent")
@Messages({
    "CTL_EditorAction=Editor",
    "CTL_EditorTopComponent=Editor Window",
    "HINT_EditorTopComponent=This is a Editor window"
})
public final class EditorTopComponent extends TopComponent {

    Scene abScene = new Scene();
    
    public EditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_EditorTopComponent());
        setToolTipText(Bundle.HINT_EditorTopComponent());

        setLayout(new BorderLayout());

        JScrollPane pane = new JScrollPane();
        
        final LayerWidget invisiblePane = new LayerWidget(abScene);


        abScene.getActions().addAction(ActionFactory.createZoomAction());
        abScene.getActions().addAction(ActionFactory.createAcceptAction(new AcceptProvider() {
            @Override
            public ConnectorState isAcceptable(Widget widget, Point point, Transferable t) {
                Node[] nodes = NodeTransfer.nodes(t, NodeTransfer.DND_COPY_OR_MOVE);
                for (Node node : nodes) {
                    boolean selected =  NbPreferences.forModule(AccountBook.class).getBoolean("dragEnabler", true);
                    
//                    StatusDisplayer.getDefault().setStatusText("selected? " + Boolean.toString(selected));
                    if (!selected){
                        JOptionPane.showMessageDialog(null, "Drag not Enabled!");
                        return ConnectorState.REJECT;
                    }
                    
                    if (node.getLookup().lookup(AccountBook.class) == null) {
                        return ConnectorState.REJECT;
                    }
                }
                return ConnectorState.ACCEPT;
            }

            @Override
            public void accept(Widget widget, Point point, Transferable t) {
                Node[] nodes = NodeTransfer.nodes(t, NodeTransfer.DND_COPY_OR_MOVE);
                for (Node node : nodes) {


                    final AccountBook ab = node.getLookup().lookup(AccountBook.class);
                    final VMDNodeWidget simpleVMD = new VMDNodeWidget(abScene);

                    List<Payment> payments = ab.getPayment();
                    for (Payment payment : payments) {
                        VMDPinWidget vmdpw = new VMDPinWidget(abScene);
                        vmdpw.setPinName(payment.getBankName());
                        simpleVMD.addChild(vmdpw);
                    }

                    // popup menu action on the dropped node;
                    simpleVMD.getActions().addAction(ActionFactory.createPopupMenuAction(new PopupMenuProvider() {
                        @Override
                        public JPopupMenu getPopupMenu(Widget widget, Point point) {
                            JPopupMenu jpm = new JPopupMenu();
                            jpm.add(new AbstractAction("Delete Widget") {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    invisiblePane.removeChild(simpleVMD);
                                    StatusDisplayer.getDefault().setStatusText(ab.getDescription() + " Deleted!");
                                }
                            });

                            return jpm;
                        }
                    }));

//                    simpleVMD.getActions().addAction(ActionFactory.createForwardKeyEventsAction(simpleVMD, "Key Action"));
//                    InputMap inputMap = new InputMap();
//                    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false), "Delete Action");
//
//                    ActionMap actionMap = new ActionMap();
//                    actionMap.put("delete action", new AbstractAction() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            System.out.println("action performed");
//                            invisiblePane.removeChild(simpleVMD);
//                        }
//                    });
//                    simpleVMD.getActions().addAction(ActionFactory.createActionMapAction(inputMap, actionMap));

                    simpleVMD.setNodeName(ab.getDescription());
                    simpleVMD.setCheckClipping(true);

                    simpleVMD.setPreferredLocation(point);
                    simpleVMD.getActions().addAction(ActionFactory.createMoveAction());
                    
//                    invisiblePane.addChild(simpleVMD);
                    invisiblePane.addChild(new AccountBookWidget(abScene, ab, point));
                }
            }
        }));
        
        abScene.setKeyEventProcessingType(EventProcessingType.FOCUSED_WIDGET_AND_ITS_CHILDREN);

        abScene.addChild(invisiblePane);
        pane.setViewportView(abScene.createView());
        add(pane, BorderLayout.CENTER);
    }

    private static class MyAction extends AbstractAction {

        public MyAction() {
            super("My Action");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "My Action has been invoked");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }
    
    @Override
    public void componentActivated() {
        abScene.getView().requestFocusInWindow();
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
