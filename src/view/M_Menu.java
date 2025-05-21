package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class M_Menu implements AccessPanel {
    private JPanel M_MenuPanel;
    private JButton M_Menu_UpdateCostsBttn;
    private JButton M_Menu_DiscountsAndBenefitsBttn;
    private JLabel M_ManagerTitle;
    private JButton M_Menu_ReturnBttn;
    private JLabel M_Menu_DocsIcon;

    public M_Menu() {
        M_Menu_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("Login");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return M_MenuPanel;
    }
}
