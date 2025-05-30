package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;

public class R_PendingPayment implements AccessPanel {
    private JPanel R_PendingPaymentPanel;
    private JLabel R_Menu_Invoicing_SubTitleRecep;
    private JLabel R_Menu_InvoicingRecepcionistTitle;

    @Override
    public JPanel getPanel() {
        return R_PendingPaymentPanel;
    }
}
