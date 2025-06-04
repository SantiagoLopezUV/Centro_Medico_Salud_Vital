package view.receptionist_templates;

import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;

public class R_PendingPayment implements AccessPanel {
    private JPanel R_PendingPaymentPanel;
    private JLabel R_PendingPayment_SubTitleRecep;
    private JLabel R_PendingPaymentRecepcionistTitle;
    private JTextField R_PendingPayment_IDPatientField;
    private JTextArea R_PendingPaymenttextArea1;
    private JButton R_PendingPayment_ReturnBttn;
    private JLabel R_PendingPayment_lblPatient;
    private JButton R_PendingPayment_SearchBttn;

    public R_PendingPayment() {

        this.R_PendingPayment_IDPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_PendingPayment_IDPatientField, "Ingrese DNI del Paciente"));

        this.R_PendingPayment_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu_Invoicing"));

    }

    @Override
    public JPanel getPanel() {
        return R_PendingPaymentPanel;
    }
}
