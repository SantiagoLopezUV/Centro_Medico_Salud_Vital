package view.receptionist_templates;

import dao.ReceptionistDao;
import model.AppointmentStatus;
import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class R_GenerateInvoice implements AccessPanel {
    private JPanel R_GenerateInvPanel;
    private JLabel R_Menu_Invoicing_SubTitleRecep;
    private JLabel R_Menu_InvoicingRecepcionistTitle;
    private JTextArea textArea1;
    private JButton R_GenerateInvBttn;
    private JButton R_GenerateInvBackBttn;
    private JButton R_GenerateInvPayBttn;
    private JTextField R_GenerateInv_IDPatientField;
    private JLabel R_GenerateInv_lblPatient;


    public R_GenerateInvoice() {

        this.R_GenerateInvBackBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu_Invoicing"));

        this.R_GenerateInv_IDPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_GenerateInv_IDPatientField, "Ingrese DNI del Paciente"));

        this.R_GenerateInvBttn.addActionListener(e -> {
            long PatientID = Long.parseLong(this.R_GenerateInv_IDPatientField.getText());
            try{
                String namePatient = ReceptionistDao.getNamePatient(PatientID);
                if(namePatient.isBlank()){
                    throw new SQLException();
                } else{
                    int idPatient = Integer.parseInt(R_GenerateInv_IDPatientField.getText());
                    ReceptionistDao.updateStatusAppointmentForPendingPayment(idPatient);
                }
            } catch (SQLException ex) {
                launchErrorFetchingDBPopUp(ex);
            }

        });

        this.R_GenerateInvPayBttn.addActionListener(e -> {
            int idPatient = Integer.parseInt(R_GenerateInv_IDPatientField.getText());
            boolean flag = false;
            try {
                int confirm = JOptionPane.showConfirmDialog(this.R_GenerateInvPanel,
                        "¿Seguro Quieres Pagar?");
                if (confirm == JOptionPane.YES_OPTION) {
                    String newStatus = AppointmentStatus.PAID.getValue();
                    flag = ReceptionistDao.updateStatusAppointmentForPendingPayment(idPatient);
                    if (flag) {
                        JOptionPane.showMessageDialog(this.R_GenerateInvPanel,
                                "¡Pago Exitoso!");
                        destroyData();
                        // Limpiamos todos los campos de la factura
                    } else JOptionPane.showMessageDialog(this.R_GenerateInvPanel, "¡No se Realizo el Pago!");
                } else {
                    String newStatus = "Pendiente por pago";
                    try {
                        ReceptionistDao.updateStatusAppointmentForPendingPayment(idPatient);
                        JOptionPane.showMessageDialog(this.R_GenerateInvPanel,
                                "¡Pendiente por Pagar!");
                    } catch (SQLException ex) {
                        launchErrorFetchingDBPopUp(ex);
                    } catch (HeadlessException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


    private void launchErrorFetchingDBPopUp(SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
                "Un error se ha presentado al tratar de consultar la base de datos," +
                        "contacta al administrador de la misma",
                "Error al consultar la base de datos",
                JOptionPane.ERROR_MESSAGE);
        destroyData();
    }

    private void destroyData() {
        this.R_GenerateInv_IDPatientField.setText("Ingrese DNI del Paciente");
    }


    @Override
    public JPanel getPanel() {
        return R_GenerateInvPanel;
    }
}
