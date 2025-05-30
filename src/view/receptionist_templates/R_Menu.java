package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;

public class R_Menu implements AccessPanel {
    private JButton R_Menu_AppointmentBttn;
    private JPanel R_MenuPanel;
    private JButton R_Menu_ConsultationBttn;
    private JButton R_Menu_InvoiceBttn;
    private JLabel R_Menu_GroupICON;
    private JLabel R_Menu_SubTitleRecep;
    private JButton R_Menu_LogOutBttn;
    private JLabel R_Menu_RecepcionistTitle;

    public R_Menu() {

        this.R_Menu_AppointmentBttn.addActionListener(e -> AccessPanel.changeContent("R_Menu_Appointment"));

        this.R_Menu_ConsultationBttn.addActionListener(e -> AccessPanel.changeContent("R_Menu_Consultation"));

        this.R_Menu_LogOutBttn.addActionListener(e -> AccessPanel.changeContent("Login"));

        this.R_Menu_InvoiceBttn.addActionListener(e -> AccessPanel.changeContent("R_Menu_Invoicing"));
    }

    @Override
    public JPanel getPanel() {
        return R_MenuPanel;
    }
}
