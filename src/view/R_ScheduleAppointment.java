package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_ScheduleAppointment implements AccessPanel {
    private JPanel R_ScheduleAppointmentPanel;
    private JLabel R_ScheduleAppointment_SubTitleRecep;
    private JLabel R_ScheduleAppointmentRecepcionistTitle;
    private JComboBox R_ScheduleAppointment_comboBoxDoctors;
    private JComboBox R_ScheduleAppointment_comboBoxCalendar;
    private JComboBox R_ScheduleAppointment_comboBoxAgreement;
    private JFormattedTextField R_ScheduleAppointment_IdPatientField;
    private JButton R_ScheduleAppointment_ReturnBttn;
    private JLabel R_ScheduleAppointment_lblDoctors;
    private JLabel R_ScheduleAppointment_lblPatient;
    private JLabel R_ScheduleAppointment_lblAgreement;
    private JButton R_ScheduleAppointment_SheduleBttn;
    private JLabel R_ScheduleAppointment_lblCalendar;
    private JLabel R_ScheduleAppointment_lblSpecialty;
    private JComboBox R_ScheduleAppointment_comboBoxSpecialty;

    public R_ScheduleAppointment() {
        R_ScheduleAppointment_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu_Appointment");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_ScheduleAppointmentPanel;
    }
}
