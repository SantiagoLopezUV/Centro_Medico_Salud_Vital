package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_CostService implements AccessPanel {
    private JPanel R_CostServicePanel;
    private JComboBox R_CostServiceCombobox;
    private JFormattedTextField R_CostServiceFormattedTextfield;
    private JLabel R_CostServiceTitle;
    private JLabel R_CostServiceSubtitleRecep;
    private JLabel R_CostServiceValueLabel;
    private JLabel R_CostServiceLabel;
    private JButton R_CostServiceReturnBttn;

    public R_CostService() {
        R_CostServiceReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu_Consultation");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_CostServicePanel;
    }
}
