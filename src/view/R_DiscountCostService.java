package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_DiscountCostService implements AccessPanel {
    private JPanel R_DiscountCostServicePanel;
    private JComboBox R_AgreementComboBox;
    private JComboBox R_ServiceComboBox;
    private JFormattedTextField R_ValueFormattedTextField;
    private JLabel R_Menu_ConsultationRecepcionistTitle;
    private JLabel R_Menu_Consultation_SubTitleRecep;
    private JLabel R_DiscountCostService_ServiceLabel;
    private JLabel R_DiscountCostService_AgreementLabel;
    private JLabel R_DiscountCostService_ValueLabel;
    private JButton R_DiscountServiceReturnBttn;

    public R_DiscountCostService() {
        R_DiscountServiceReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu_Consultation");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_DiscountCostServicePanel;
    }
}
