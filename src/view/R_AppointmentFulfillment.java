package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_AppointmentFulfillment implements AccessPanel {
    private JPanel R_AppointmentFulfillmentPanel;
    private JLabel R_AppointmentFulfillment_SubTitleRecep;
    private JLabel R_AppointmentFulfillmentRecepcionistTitle;
    private JButton R_AppointmentFulfillment_ReturnBttn;
    private JButton R_AppointmentFulfillment_RegisterFulfillmentBttn;
    private JFormattedTextField R_AppointmentFulfillment_IdPatientField;
    private JLabel R_AppointmentFulfillment_lblPatient;
    private JLabel R_AppoinmentFulfillment_IdIcon;

    public R_AppointmentFulfillment() {
        R_AppointmentFulfillment_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu_Appointment");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_AppointmentFulfillmentPanel;
    }
}
