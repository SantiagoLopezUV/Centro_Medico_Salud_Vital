package view.manager_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class M_UpdateCosts implements AccessPanel{
    private JPanel M_UpdateCostsPanel;
    private JButton M_UpdateCosts_SaveBttn;
    private JButton M_UpdateCosts_ReturnBttn;
    private JLabel M_UpdateCosts_ManagerTitle;
    private JFormattedTextField M_UpdateCosts_NewCostField;
    private JLabel M_UpdateCosts_lblNewCost;
    private JLabel M_UpdateCosts_lblCost;
    private JFormattedTextField M_UpdateCosts_CostField;
    private JLabel M_UpdateCosts_lblConsultations;
    private JComboBox M_UpdateCosts_comboBoxService;
    private JLabel M_UpdateCosts_SubTitleAdmUpdateCosts;
    private JLabel M_UpdateCosts_NotesIcon;

    public M_UpdateCosts() {

        this.M_UpdateCosts_ReturnBttn.addActionListener(e -> AccessPanel.changeContent("M_Menu"));


    }

    @Override
    public JPanel getPanel() {
        return M_UpdateCostsPanel;
    }
}
