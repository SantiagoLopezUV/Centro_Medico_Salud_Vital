package view.receptionist_templates;

import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;

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

        this.R_AppointmentFulfillment_IdPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_AppointmentFulfillment_IdPatientField, "Número de Identificación"));

        this.R_AppointmentFulfillment_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu_Appointment"));

        this.R_AppointmentFulfillment_RegisterFulfillmentBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Fulfillment_StatusAppointment"));
    }

    @Override
    public JPanel getPanel() {
        return R_AppointmentFulfillmentPanel;
    }
}
