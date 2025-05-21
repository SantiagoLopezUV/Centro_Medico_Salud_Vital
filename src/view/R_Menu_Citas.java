package view;

import utils.AccessPanel;

import javax.swing.*;

public class R_Menu_Citas implements AccessPanel {
    private JPanel R_Menu_CitasPanel;
    private JLabel R_Menu_Citas_SubTitleRecep;
    private JLabel R_Menu_CitasRecepcionistTitle;
    private JButton R_Menu_Citas_ScheduleAppointmentBttn;
    private JButton R_Menu_Citas_AppointmentFulfillmentBttn;
    private JButton R_Menu_Citas_ReturnBttn;
    private JLabel R_Menu_Citas_ScheduleIcon;

    @Override
    public JPanel getPanel() {
        return R_Menu_CitasPanel;
    }
}
