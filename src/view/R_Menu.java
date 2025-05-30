package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_Menu implements AccessPanel {
    private JButton R_Menu_AppointmentBttn;
    private JPanel R_MenuPanel;
    private JButton R_Menu_ConsultationBttn;
    private JButton R_Menu_InvoiceBttn;
    private JLabel R_Menu_GroupICON;
    private JLabel R_Menu_SubTitleRecep;
    private JButton R_Menu_ReturnBttn;
    private JLabel R_Menu_RecepcionistTitle;

    public R_Menu() {
        R_Menu_AppointmentBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu_Appointment");
            }
        });
        R_Menu_ConsultationBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent(("R_Menu_Consultation"));
            }
        });
        R_Menu_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("Login");
            }
        });
        R_Menu_InvoiceBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent(("R_Menu_Invoicing"));
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
