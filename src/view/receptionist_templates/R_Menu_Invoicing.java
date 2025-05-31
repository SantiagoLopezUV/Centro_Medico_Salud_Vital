package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_Menu_Invoicing implements AccessPanel {
    private JPanel R_Menu_InvoicingPanel;
    private JLabel R_Menu_Invoicing_SubTitleRecep;
    private JLabel R_Menu_InvoicingRecepcionistTitle;
    private JButton R_Menu_Invoicing_ReturnBttn;
    private JButton R_Menu_Invoicing_PendingPaymentBttn;
    private JButton R_Menu_Invoicing_IssueInvoiceBttn;
    private JLabel R_Menu_Invoicing_GroupICON;

    public R_Menu_Invoicing() {

        this.R_Menu_Invoicing_IssueInvoiceBttn.addActionListener(e ->
                AccessPanel.changeContent("R_generateinvoice"));

        this.R_Menu_Invoicing_PendingPaymentBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Pending_Payment"));

        this.R_Menu_Invoicing_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu"));

    }

    @Override
    public JPanel getPanel() {
        return R_Menu_InvoicingPanel;
    }
}
