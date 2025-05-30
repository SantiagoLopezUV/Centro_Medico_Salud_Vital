package view;

import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;

public class LogInPanel implements AccessPanel {
    private JPanel LogInBG;
    private JTextField profileNameField;
    private JTextField passwordField;
    private JButton authCredentialsBttn;
    private JLabel LogInTitle;
    private JLabel stethoscopeIcon;

    public LogInPanel(){
        this.profileNameField
                .addFocusListener(new PlaceHoldersAction(profileNameField, "Usuario"));
        this.passwordField
                .addFocusListener(new PlaceHoldersAction(passwordField, "Contraseña"));
    }

    @Override
    public JPanel getPanel() {
        return LogInBG;
    }
}
