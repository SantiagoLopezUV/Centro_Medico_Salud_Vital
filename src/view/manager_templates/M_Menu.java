package view.manager_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class M_Menu implements AccessPanel {
    private JPanel M_MenuPanel;
    private JButton M_Menu_UpdateCostsBttn;
    private JButton M_Menu_DiscountsAndBenefitsBttn;
    private JLabel M_Menu_ManagerTitle;
    private JButton M_Menu_LogOutBttn;
    private JLabel M_Menu_DocsIcon;
    private JLabel M_Menu_SubTitleAdm;

    public M_Menu() {

        this.M_Menu_LogOutBttn.addActionListener(e -> AccessPanel.changeContent("R_Menu"));

        this.M_Menu_DiscountsAndBenefitsBttn.addActionListener(e -> AccessPanel.changeContent("M_Discounts_Benefits"));

        this.M_Menu_UpdateCostsBttn.addActionListener(e -> AccessPanel.changeContent("M_Update_Costs"));

    }

    @Override
    public JPanel getPanel() {
        return M_MenuPanel;
    }

}
