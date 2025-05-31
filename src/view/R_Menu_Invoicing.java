package view;

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

        R_Menu_Invoicing_PendingPaymentBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Pending_Payment");
            }
        });
        R_Menu_Invoicing_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu");
            }
        });
        R_Menu_Invoicing_IssueInvoiceBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {AccessPanel.changeContent("R_GenerateInvoice");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_Menu_InvoicingPanel;
    }
}
