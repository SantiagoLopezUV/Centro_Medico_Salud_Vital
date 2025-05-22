package view;

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
        R_Menu_Consultation_ReturnBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("R_Menu");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return R_MedConsultationPanel;
    }
}
