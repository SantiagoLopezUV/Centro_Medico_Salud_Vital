package view.receptionist_templates;

import dao.ReceptionistDao;
import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.sql.SQLException;

public class R_PendingPayment implements AccessPanel {
    private JPanel R_PendingPaymentPanel;
    private JLabel R_PendingPayment_SubTitleRecep;
    private JLabel R_PendingPaymentRecepcionistTitle;
    private JTextField R_PendingPayment_IDPatientField;
    private JButton R_PendingPayment_ReturnBttn;
    private JLabel R_PendingPayment_lblPatient;
    private JButton R_PendingPayment_SearchBttn;
    private JTextField R_PendingPayment_valuePendingField;

    public R_PendingPayment() {

        this.R_PendingPayment_valuePendingField.setEditable(false);
        this.R_PendingPayment_valuePendingField.setFocusable(false);

        this.R_PendingPayment_IDPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_PendingPayment_IDPatientField, "Ingrese DNI del Paciente"));

        this.R_PendingPayment_ReturnBttn.addActionListener(e -> {
            this.R_PendingPayment_IDPatientField.setText("");
            this.R_PendingPayment_valuePendingField.setText("");
            this.R_PendingPayment_IDPatientField.addFocusListener(new PlaceHoldersAction(
                    this.R_PendingPayment_IDPatientField, "Ingrese DNI del Paciente"));
            AccessPanel.changeContent("R_Menu_Invoicing");
        });

        this.R_PendingPayment_SearchBttn.addActionListener(e -> {
            long idPatient = 0;
            try {
                idPatient = Long.parseLong(this.R_PendingPayment_IDPatientField.getText());
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "DNI Invalido");
            }

            try {
                ReceptionistDao.infoDebts infoDebtPatient = ReceptionistDao.consultsDebts(idPatient);

                if(infoDebtPatient == null) {
                    JOptionPane.showMessageDialog(null, "No se encontró el ID");
                    this.R_PendingPayment_valuePendingField.setText("");
                }
                switch (infoDebtPatient.status()) {
                    case "Pagada" -> {
                        JOptionPane.showMessageDialog(null, "No Tiene Deudas Por Pagar");
                        R_PendingPayment_valuePendingField.setText("");
                        R_PendingPayment_IDPatientField.setText("");
                    }
                    case "Pendiente por pago" ->
                        R_PendingPayment_valuePendingField.setText(String.valueOf(infoDebtPatient.value()));
                    default -> {
                        System.out.println(infoDebtPatient.status());
                        JOptionPane.showMessageDialog(null,
                                "Estado actual de la cita: " + infoDebtPatient.status());
                        R_PendingPayment_valuePendingField.setText("");
                    }
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    @Override
    public JPanel getPanel() {
        return R_PendingPaymentPanel;
    }
}
