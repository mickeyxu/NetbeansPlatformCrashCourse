/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.analyzer;

import de.hhb.domain.AccountBook;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "AccountBook",
        id = "de.hhb.analyzer.AnalyzerAccountBookAction")
@ActionRegistration(
        iconBase = "de/hhb/analyzer/icon-01.png",
        displayName = "#CTL_AnalyzerAccountBookAction")
@ActionReferences({
    @ActionReference(path = "Menu/Edit", position = 2450, separatorBefore = 2425, separatorAfter = 2475),
    @ActionReference(path = "Toolbars/File", position = 500)
})
@Messages("CTL_AnalyzerAccountBookAction=AccountBook Analyzer")
public final class AnalyzerAccountBookAction implements ActionListener {

    private final AccountBook context;

    public AnalyzerAccountBookAction(AccountBook context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {

        JOptionPane.showMessageDialog(null, "Let's Analyze!");        
    }
}
