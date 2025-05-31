package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_PendingPayment implements AccessPanel {
    private JPanel R_PendingPaymentPanel;
    private JLabel R_Menu_Invoicing_SubTitleRecep;
    private JLabel R_Menu_InvoicingRecepcionistTitle;
    private JLabel R_InvoiceConsultDebtsTitle;
    private JTextField IDTextField;
    private JTextArea textArea1;
    private JButton R_PPReturnBttn;
    private JButton consultarButton;

    public R_PendingPayment() {

        this.R_PPReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu_Invoicing"));

//        this.consultarButton.addActionListener();

    }

    @Override
    public JPanel getPanel() {
        return R_PendingPaymentPanel;
    }
}
