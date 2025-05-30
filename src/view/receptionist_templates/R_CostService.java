package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_CostService implements AccessPanel {
    private JPanel R_CostServicePanel;
    private JComboBox R_CostService_comboBoxServices;
    private JFormattedTextField R_CostService_ValueServiceField;
    private JLabel R_CostServiceRecepcionistTitle;
    private JLabel R_CostServiceSubTitleRecep;
    private JLabel R_CostService_lblValue;
    private JLabel R_CostService_lblService;
    private JButton R_CostService_ReturnBttn;
    private JLabel R_CostService_lblPatient;
    private JFormattedTextField R_CostService_IdPatientField;
    private JLabel R_CostService_lblAgreement;
    private JComboBox R_CostService_comboBoxAgreement;
    private JButton R_CostService_CalculateBttn;
    private JLabel R_CostService_lblStatus;
    private JFormattedTextField R_CostService_StatusField;
    private JLabel R_CostService_lblDiscount;
    private JFormattedTextField R_CostService_ValueDiscountField;

    public R_CostService() {
        R_CostService_ReturnBttn.addActionListener(new ActionListener() {
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
