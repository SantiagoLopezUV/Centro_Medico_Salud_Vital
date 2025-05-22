package view;

import utils.AccessPanel;

import javax.swing.*;

public class R_Menu_Appointment implements AccessPanel {
    private JPanel R_Menu_AppointmentPanel;
    private JLabel R_Menu_Appointment_SubTitleRecep;
    private JLabel R_Menu_AppointmentRecepcionistTitle;
    private JButton R_Menu_Appointment_ScheduleAppointmentBttn;
    private JButton R_Menu_Appointment_AppointmentFulfillmentBttn;
    private JButton R_Menu_Appointment_ReturnBttn;
    private JLabel R_Menu_Appointment_ScheduleIcon;

    @Override
    public JPanel getPanel() {
        return R_Menu_AppointmentPanel;
    }
}
