package view.receptionist_templates;

import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_ScheduleAppointment implements AccessPanel {
    private JPanel R_ScheduleAppointmentPanel;
    private JLabel R_ScheduleAppointment_SubTitleRecep;
    private JLabel R_ScheduleAppointmentRecepcionistTitle;
    private JComboBox R_ScheduleAppointment_comboBoxDoctors;
    private JComboBox R_ScheduleAppointment_comboBoxDate;
    private JComboBox R_ScheduleAppointment_comboBoxAgreement;
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

    public R_ScheduleAppointment() {

        this.R_ScheduleAppointment_IdPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_ScheduleAppointment_IdPatientField, "Número de Identificación"));

        this.R_ScheduleAppointment_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu_Appointment"));

    }

    @Override
    public JPanel getPanel() {
        return R_ScheduleAppointmentPanel;
    }
}
