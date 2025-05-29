package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_ConsultationCosts implements AccessPanel {
    private JPanel R_ConsultationCostsPanel;
    private JLabel R_ConsultationCosts_SubTitleRecep;
    private JLabel R_ConsultationCostsRecepcionistTitle;
    private JComboBox R_ConsultationCosts_comboBoxAgreement;
    private JLabel R_ConsultationCosts_lblAgreement;
    private JLabel R_ConsultationCosts_lblPatient;
    private JFormattedTextField R_ConsultationCosts_IdPatientField;
    private JLabel R_ConsultationCosts_lblStatus;
    private JFormattedTextField R_ConsultationCosts_StatusField;
    private JButton R_ConsultationCosts_ReturnBttn;
    private JLabel R_ConsultationCosts_lblDiscount;
    private JFormattedTextField R_ConsultationCosts_ValueDiscountField;
    private JLabel R_ConsultationCosts_lblFinalValue;
    private JFormattedTextField R_ConsultationCosts_FinalValueField;
    private JButton R_ConsultationCosts_CalculateBttn;


    public R_ConsultationCosts() {
        R_ConsultationCosts_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {AccessPanel.changeContent("R_Menu_Consultation");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_ConsultationCostsPanel;
    }
}

