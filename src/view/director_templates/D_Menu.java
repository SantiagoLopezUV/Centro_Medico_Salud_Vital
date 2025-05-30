package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class D_Menu implements AccessPanel {
    private JPanel D_Menu_PanelBG;
    private JButton D_Menu_ArrangementScheduledBttn;
    private JButton D_Menu_StatisticsBttn;
    private JButton D_Menu_MonthlyIncomeBttn;
    private JButton D_Menu_MedicServiceAndSpecialitiesBttn;
    private JLabel D_Menu_CeoIcon;
    private JButton D_Menu_LogOutBttn;
    private JLabel D_Menu_SubTitleDir;
    private JLabel D_Menu_DirectorTitle;

    public D_Menu() {

        this.D_Menu_MedicServiceAndSpecialitiesBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_MED_ADDITIONAL_SERVICES_AND_SPECIALTIES"));

        this.D_Menu_ArrangementScheduledBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_ARRANGEMENT_PATIENT_INFO"));

        this.D_Menu_MonthlyIncomeBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_MONTHLY_ADD_SERVICES_INCOME"));

        this.D_Menu_StatisticsBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS"));
        this.D_Menu_LogOutBttn.addActionListener(e -> AccessPanel.changeContent("M_Menu"));
    }


    @Override
    public JPanel getPanel() {
        return this.D_Menu_PanelBG;
    }
}
