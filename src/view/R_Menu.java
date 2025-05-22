package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_Menu implements AccessPanel {
    private JButton R_Menu_AppointmentBttn;
    private JPanel R_MenuPanel;
    private JButton R_Menu_MedicAppointmentBttn;
    private JButton R_Menu_BillingBttn;
    private JLabel R_MedicCenter;
    private JLabel R_GroupICON;

    public R_Menu() {
        R_Menu_AppointmentBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu_Appointment");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public JPanel getPanel() {
        return R_MenuPanel;
    }
}
