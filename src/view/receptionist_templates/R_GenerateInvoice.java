package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_GenerateInvoice implements AccessPanel {
    private JPanel R_GenerateInvPanel;
    private JLabel R_Menu_Invoicing_SubTitleRecep;
    private JLabel R_Menu_InvoicingRecepcionistTitle;
    private JTextArea textArea1;
    private JButton R_GenerateInvBttn;
    private JButton R_GenerateInvBackBttn;

    public R_GenerateInvoice() {
        R_GenerateInvBackBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {AccessPanel.changeContent("R_Menu_Invoicing");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_GenerateInvPanel;
    }
}
