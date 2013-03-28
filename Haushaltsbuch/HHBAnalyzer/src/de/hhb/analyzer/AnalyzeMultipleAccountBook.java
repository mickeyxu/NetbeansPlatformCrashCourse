/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hhb.analyzer;

import de.hhb.domain.AccountBook;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "AccountBook",
        id = "de.hhb.analyzer.AnalyzeMultipleAccountBook")
@ActionRegistration(
        iconBase = "de/hhb/analyzer/icon-02.png",
        displayName = "#CTL_AnalyzeMultipleAccountBook")
@ActionReferences({
    @ActionReference(path = "Menu/Edit", position = 2462),
    @ActionReference(path = "Toolbars/File", position = 600)
})
@Messages("CTL_AnalyzeMultipleAccountBook=Multiple AccountBook Analyzer")
public final class AnalyzeMultipleAccountBook implements ActionListener {

    private final List<AccountBook> context;

    public AnalyzeMultipleAccountBook(List<AccountBook> context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        for (AccountBook accountBook : context) {
//            accountBook.getPayment().size();
        }
    }
}
