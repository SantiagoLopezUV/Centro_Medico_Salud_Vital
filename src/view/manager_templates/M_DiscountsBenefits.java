package view.manager_templates;

import utils.AccessPanel;
import utils.KeyListenerParaDouble;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class M_DiscountsBenefits implements AccessPanel {
    private JPanel M_DiscountsBenefitsPanel;
    private JLabel M_DiscountsBenefits_ManagerTitle;
    private JButton M_DiscountsBenefits_ReturnBttn;
    private JButton M_DiscountsBenefits_SaveBttn;
    private JFormattedTextField M_DiscountsBenefits_NewCostDiscountField;
    private JFormattedTextField M_DiscountsBenefits_CostDiscountField;
    private JComboBox M_DiscountsBenefits_comboBoxAgreement;
    private JLabel M_DiscountsBenefits_lblAgreement;
    private JLabel M_DiscountsBenefits_lblActualDiscount;
    private JLabel M_DiscountBenefits_lblNewDiscount;
    private JLabel M_DiscountsBenefits_NotesIcon;
    private JLabel M_DiscountsBenefits_SubTitleAdmDiscountsBenefits;

    public M_DiscountsBenefits() {

        this.M_DiscountsBenefits_ReturnBttn.addActionListener(e -> AccessPanel.changeContent("M_Menu"));

        this.M_DiscountsBenefits_CostDiscountField.addKeyListener(
                new KeyListenerParaDouble(this.M_DiscountsBenefits_CostDiscountField));

        this.M_DiscountsBenefits_NewCostDiscountField.addKeyListener(
                new KeyListenerParaDouble(this.M_DiscountsBenefits_NewCostDiscountField));
    }

    @Override
    public JPanel getPanel() {
        return M_DiscountsBenefitsPanel;
    }
}
