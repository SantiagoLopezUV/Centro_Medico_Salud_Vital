package view.receptionist_templates;

import dao.ReceptionistDao;
import model.Arrangement;
import utils.AccessPanel;
import utils.KeyListenerParaInt;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class R_ScheduleAppointment implements AccessPanel {
    private JPanel R_ScheduleAppointmentPanel;
    private JLabel R_ScheduleAppointment_SubTitleRecep;
    private JLabel R_ScheduleAppointmentRecepcionistTitle;
    private JComboBox R_ScheduleAppointment_comboBoxDoctors;
    private JComboBox R_ScheduleAppointment_comboBoxDate;
    private JFormattedTextField R_ScheduleAppointment_TextfieldAgreement;
    private JFormattedTextField R_ScheduleAppointment_IdPatientField;
    private JButton R_ScheduleAppointment_ReturnBttn;
    private JLabel R_ScheduleAppointment_lblDoctors;
    private JLabel R_ScheduleAppointment_lblPatient;
    private JLabel R_ScheduleAppointment_lblAgreement;
    private JButton R_ScheduleAppointment_SheduleBttn;
    private JLabel R_ScheduleAppointment_lblDate;
    private JLabel R_ScheduleAppointment_lblSpecialty;
    private JComboBox R_ScheduleAppointment_comboBoxSpecialty;
    private JLabel R_ScheduleAppointment_lblHour;
    private JComboBox R_ScheduleAppointment_comboBoxHour;

    private static final String patientIdTextFieldPlaceHolderMsg = "Ingrese # de ID y presione enter";

    public R_ScheduleAppointment() {

        this.R_ScheduleAppointment_IdPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_ScheduleAppointment_IdPatientField, patientIdTextFieldPlaceHolderMsg));
        this.R_ScheduleAppointment_IdPatientField.addKeyListener(
                new KeyListenerParaInt(){
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            checkPatientId();
                        }
                    }
                });


        this.R_ScheduleAppointment_ReturnBttn.addActionListener(e ->{
            AccessPanel.changeContent("R_Menu_Appointment");
            destroyData();
        });



    }

    private void checkPatientId(){
        try {
            String patientId = this.R_ScheduleAppointment_IdPatientField.getText();
            if(patientId.isBlank()) return;
            Long idPatient = Long.parseLong(patientId);
            ReceptionistDao receptionistDao = new ReceptionistDao();
            Arrangement arrangement = receptionistDao.getArrangementForPatient(idPatient);
            if(arrangement == null){
                int confirm = JOptionPane.showConfirmDialog(this.R_ScheduleAppointmentPanel,
                        "No se encuentra al paciente en la base de datos\n¿Deseas registrarlo?");
                if (confirm == JOptionPane.YES_OPTION) {
                    destroyData();
                    AccessPanel.changeContent("SignUp");
                }
            }else {
                this.R_ScheduleAppointment_TextfieldAgreement.setText(
                        ((arrangement.isValid()) ? arrangement.toString() : "No válido")
                );
///continuar agregando la logica para cuando existe el paciente
            }



        }catch (NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Verifica que el documento de identidad sea válido",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
            destroyData();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
            destroyData();
        }

    }



    private void destroyData(){
        this.R_ScheduleAppointment_IdPatientField.setText( patientIdTextFieldPlaceHolderMsg);
        this.R_ScheduleAppointment_comboBoxDate.removeAllItems();
        this.R_ScheduleAppointment_comboBoxSpecialty.removeAllItems();
        this.R_ScheduleAppointment_comboBoxDoctors.removeAllItems();
        this.R_ScheduleAppointment_comboBoxHour.removeAllItems();
        this.R_ScheduleAppointment_TextfieldAgreement.setText("");
        this.R_ScheduleAppointment_comboBoxHour.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxSpecialty.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxDoctors.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxDate.setEnabled(false);
    }

    @Override
    public JPanel getPanel() {
        return R_ScheduleAppointmentPanel;
    }
}
