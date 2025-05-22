package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class D_MenuPanel implements AccessPanel {
    private JPanel D_menuBG;
    private JButton arrangementScheduledAttn;
    private JButton statisticsBttn;
    private JButton monthlyIncomeBttn;
    private JButton medicServiceAndSpecialitiesBttn;
    private JLabel CEOMenuTitle;
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
                        AccessPanel.changeContent("D_MONTHLY_ADD_SERVS_INCOME"));

        this.statisticsBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("Login"));
    }


    @Override
    public JPanel getPanel() {
        return this.D_menuBG;
    }
}
