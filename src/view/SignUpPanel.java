package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel implements AccessPanel {
    private JPanel SignUpPanelBG;
    private JLabel SignUp_SubTitleRecep;
    private JLabel SignUp_RecepcionistTitle;
    private JLabel SignUp_lblFirstName;
    private JLabel SignUp_lbl_ID;
    private JFormattedTextField SignUp_FirstNameField;
    private JFormattedTextField SignUp_IDField;
    private JButton SignUp_ReturnBttn;
    private JButton SignUp_RegisterBttn;
    private JRadioButton SignUp_radioButtonAgreement_NOT;
    private JRadioButton SignUp_radioButtonAgreement_YES;
    private JLabel SignUp_lblAgreement;
    private JLabel SignUp_lblGender;
    private JRadioButton SignUp_radioButtonGender_Female;
    private JRadioButton SignUp_radioButtonGender_Male;
    private JLabel SignUp_lblLastName;
    private JFormattedTextField SignUp_LastNameField;
    private JLabel SignUp_lblPhone;
    private JLabel SignUp_lblAddress;
    private JLabel SignUp_lblCity;
    private JFormattedTextField SignUp_PhoneField;
    private JLabel SignUp_lblEmail;
    private JFormattedTextField SignUp_EmailField;
    private JFormattedTextField SignUp_AddressField;
    private JFormattedTextField SignUp_CityField;
    private JFormattedTextField SignUp_CompanyField;
    private JLabel SignUp_lblCompany;

    public SignUpPanel() {
        SignUp_radioButtonAgreement_YES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp_lblCompany.setVisible(true);
                SignUp_CompanyField.setVisible(true);
            }
        });
        SignUp_radioButtonAgreement_NOT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp_lblCompany.setVisible(false);
                SignUp_CompanyField.setVisible(false);
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return SignUpPanelBG;
    }
}
