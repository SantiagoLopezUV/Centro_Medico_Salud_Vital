package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_Menu_Appointment implements AccessPanel {
    private JPanel R_Menu_AppointmentPanel;
    private JLabel R_Menu_Appointment_SubTitleRecep;
    private JLabel R_Menu_AppointmentRecepcionistTitle;
    private JButton R_Menu_Appointment_ScheduleAppointmentBttn;
    private JButton R_Menu_Appointment_AppointmentFulfillmentBttn;
    private JButton R_Menu_Appointment_ReturnBttn;
    private JLabel R_Menu_Appointment_ScheduleIcon;

    public R_Menu_Appointment() {
        R_Menu_Appointment_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu");
            }
        });
        R_Menu_Appointment_ScheduleAppointmentBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_ScheduleAppointment");
            }
        });

        R_Menu_Appointment_AppointmentFulfillmentBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_AppointmentFulfillment");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_Menu_AppointmentPanel;
    }
}
