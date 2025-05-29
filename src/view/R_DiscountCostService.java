package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_DiscountCostService implements AccessPanel {
    private JPanel R_DiscountCostServicePanel;
    private JComboBox R_DiscountCostService_comboBoxAgreement;
    private JComboBox R_DiscountCostService_comboBoxService;
    private JFormattedTextField R_DiscountCostService_ValueField;
    private JLabel R_DiscountCostServiceRecepcionistTitle;
    private JLabel R_DiscountCostServiceSubTitleRecep;
    private JLabel R_DiscountCostService_lblService;
    private JLabel R_DiscountCostService_lblAgreement;
    private JLabel R_DiscountCostService_lblValue;
    private JButton R_DiscountService_ReturnBttn;
    private JLabel R_DiscountCostService_lblPatient;
    private JFormattedTextField R_DiscountCostService_IdPatientField;
    private JLabel R_DiscountCostService_lblStatus;
    private JFormattedTextField R_DiscountCostService_StatusField;
    private JLabel R_DiscountCostService_lblDiscount;
    private JFormattedTextField R_DiscountCostServiceValueDiscountField;
    private JButton R_DiscountCostService_CalculateBttn;
    private JLabel R_DiscountCostService_dealIcon;

    public R_DiscountCostService() {
        R_DiscountService_ReturnBttn.addActionListener(new ActionListener() {
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
