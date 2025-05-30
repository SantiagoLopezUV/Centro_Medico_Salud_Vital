package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_AddService implements AccessPanel {
    private JPanel R_AddServicePanel;
    private JButton R_AddService_ReturnBttn;
    private JButton R_AddService_ScheduleBttn;
    private JComboBox R_AddService_comboBoxServices;
    private JLabel R_AddServiceRecepcionistTitle;
    private JLabel R_AddServiceSubTitleRecep;
    private JLabel R_AddService_ScheduleIcon;
    private JLabel R_AddService_lblServices;
    private JLabel R_AddService_lblHour;
    private JComboBox R_AddService_comboBoxHour;

    public R_AddService() {

        this.R_AddService_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_AppointmentFulfillment"));

        //this.R_AddService_ScheduleBttn.addActionListener(e -> ());

    }

    @Override
    public JPanel getPanel() {
        return R_AddServicePanel;
    }
}
