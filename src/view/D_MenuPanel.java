package view;

import utils.AccessPanel;

import javax.swing.*;

public class D_MenuPanel implements AccessPanel {
    private JPanel D_menuBG;
    private JButton arrangementScheduledAttn;
    private JButton statisticsBttn;
    private JButton monthlyIncomeBttn;
    private JButton medicServiceAndSpecialitiesBttn;
    private JLabel directorMenuTitle;
    private JLabel CeoMenuIcon;
    private JButton logOutBttn;

    public D_MenuPanel() {

        this.medicServiceAndSpecialitiesBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_MED_ADDITIONAL_SERVICES_AND_SPECIALTIES"));

        this.arrangementScheduledAttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_ARRANGEMENT_PATIENT_INFO"));

        this.monthlyIncomeBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_MONTHLY_ADD_SERVICES_INCOME"));

        this.statisticsBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS"));
    }


    @Override
    public JPanel getPanel() {
        return this.D_menuBG;
    }
}
