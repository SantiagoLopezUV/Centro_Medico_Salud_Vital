package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class R_Menu_Consultation implements AccessPanel {
    private JPanel R_MedConsultationPanel;
    private JButton R_Menu_Consultation_ServiceConsultationBttn;
    private JButton R_Menu_Consultation_DiscountsBenefitsConsultationBttn;
    private JButton R_Menu_Consultation_ReturnBttn;
    private JLabel R_Menu_Consultation_SubTitleRecep;
    private JLabel R_Menu_ConsultationRecepcionistTitle;
    private JLabel R_Menu_Consultation_MoneyIcon;

    public R_Menu_Consultation() {

        this.R_Menu_Consultation_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu"));

        this.R_Menu_Consultation_ServiceConsultationBttn.addActionListener(e ->
                AccessPanel.changeContent("R_CostService"));

        this.R_Menu_Consultation_DiscountsBenefitsConsultationBttn.addActionListener(e ->
                    AccessPanel.changeContent("R_DiscountCostService"));
    }

    @Override
    public JPanel getPanel() {
        return R_MedConsultationPanel;
    }
}
