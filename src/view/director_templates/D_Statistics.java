package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;

public class D_Statistics implements AccessPanel {
    private JPanel D_StatisticsBG;
    private JButton addServsPercentajeBttn;
    private JButton cancelledArrangRateBttn;
    private JButton medicOcupationPercentageBttn;
    private JButton backBttn;
    private JLabel StatisticsMenuTitle;
    private JLabel statisticsIcon;
    private JLabel breadCrumbLbl;


    public D_Statistics() {
        this.medicOcupationPercentageBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS_MEDIC_OCCUPATION_PERCENTAGE"));

        this.addServsPercentajeBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS_ADD_SERV_PERCENTAGE"));

        this.cancelledArrangRateBttn
                .addActionListener(e ->
                        AccessPanel.changeContent("D_STATISTICS_ARRANGEMENT_ANNULLED_RATE"));

        this.backBttn.addActionListener(e ->
                AccessPanel.changeContent("D_menu"));
    }

    @Override
    public JPanel getPanel() {
        return this.D_StatisticsBG;
    }
}
