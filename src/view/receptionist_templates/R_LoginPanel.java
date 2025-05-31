package view.receptionist_templates;

import utils.AccessPanel;

import javax.swing.*;

public class R_LoginPanel implements AccessPanel {
    private JTextField R_PasswordField;
    private JTextField R_NumberIDUserField;
    private JButton R_LoginBttn;
    private JLabel R_MedicCenter;
    private JLabel R_DocsICON;
    private JPanel R_LoginPanel;

    @Override
    public JPanel getPanel() {
        return R_LoginPanel;
    }
}
