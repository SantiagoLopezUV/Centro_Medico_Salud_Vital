package view.receptionist_templates;

import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;

public class R_CostService implements AccessPanel {
    private JPanel R_CostServicePanel;
    private JLabel R_CostServiceRecepcionistTitle;
    private JLabel R_CostServiceSubTitleRecep;
    private JLabel R_CostService_lblConsult;
    private JButton R_CostService_ReturnBttn;
    private JLabel R_CostService_lblValues;
    private JFormattedTextField R_CostService_IdPatientField;
    private JButton R_CostService_CalculateBttn;
    private JList list1;
    private JList list2;

    public R_CostService() {

        this.R_CostService_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu_Consultation"));



    }

    @Override
    public JPanel getPanel() {
        return R_CostServicePanel;
    }
}
