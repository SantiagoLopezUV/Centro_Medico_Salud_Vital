package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_Fulfillment_StatusAppointment implements AccessPanel {
    private JPanel R_Fulfillment_StatusAppointmentPanel;
    private JLabel R_Fulfillment_StatusAppointmentRecepcionistTitle;
    private JButton R_Fulfillment_StatusAppointment_ConfirmBttn;
    private JLabel R_Fulfillment_StatusAppointmentSubTitleRecep;
    private JButton R_Fulfillment_StatusAppointment_NewAppointmentBttn;
    private JLabel R_Fulfillment_StatusAppointment_lblStatusAppointment;
    private JFormattedTextField R_Fulfillment_StatusAppointment_StatusAppointmentField;
    private JLabel R_Fulfillment_StatusAppointment_lblAddNewService;
    private JLabel R_Fulfillment_StatusAppointment_ChatIcon;

    public R_Fulfillment_StatusAppointment() {
        R_Fulfillment_StatusAppointment_ConfirmBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_AppointmentFulfillment");
            }
        });
        R_Fulfillment_StatusAppointment_NewAppointmentBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {AccessPanel.changeContent("R_AddService");}
        });
    }

    @Override
    public JPanel getPanel() {
        return R_Fulfillment_StatusAppointmentPanel;
    }
}
