package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;

public class D_Menu_Statistics implements AccessPanel {
    private JPanel D_Menu_StatisticsBG;
    private JButton D_Menu_Statistics_ServsPercentajeBttn;
    private JButton D_Menu_Statistics_CancelledArrangementBttn;
    private JButton D_Menu_Statistics_MedicOcupationPercentageBttn;
    private JButton D_Menu_Statistics_ReturnBttn;
    private JLabel D_Menu_Statistics_StatisticsIcon;
    private JLabel D_Menu_SubTitleDir;
    private JLabel D_Menu_Statistics_DirectorTitle;
    private JLabel D_Menu_Statistics_OrgIcon;


    public D_Menu_Statistics() {
        this.D_Menu_Statistics_MedicOcupationPercentageBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS_MEDIC_OCCUPATION_PERCENTAGE"));

        this.D_Menu_Statistics_ServsPercentajeBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS_ADD_SERV_PERCENTAGE"));

        this.D_Menu_Statistics_CancelledArrangementBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS_ARRANGEMENT_ANNULLED_RATE"));

        this.D_Menu_Statistics_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("D_menu"));
    }

    @Override
    public JPanel getPanel() {
        return this.D_Menu_StatisticsBG;
    }
}
