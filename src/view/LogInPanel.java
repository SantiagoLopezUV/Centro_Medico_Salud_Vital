package view;

import utils.AccessPanel;
import utils.PlaceHoldersAction;
import utils.security.AuthenticationService;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LogInPanel implements AccessPanel {
    private JPanel LogInBG;
    private JTextField profileNameField;
    private JPasswordField passwordField;
    private JButton authCredentialsBttn;
    private JLabel LogInTitle;
    private JLabel stethoscopeIcon;

    public LogInPanel(){
        this.profileNameField
                .addFocusListener(new PlaceHoldersAction(profileNameField, "Usuario"));
        this.passwordField
                .addFocusListener(new PlaceHoldersAction(passwordField, "Contraseña"));
        this.authCredentialsBttn.addActionListener(e -> authAction());


        Action enterAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                authAction();
            }
        };
        InputMap inputMap = LogInBG.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = LogInBG.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "EnterCap");
        actionMap.put("EnterCap", enterAction);
    }

    private void authAction(){
        try{
            AuthenticationService authenticationService = new AuthenticationService();
            String rol = authenticationService.authenticate(profileNameField.getText(), passwordField.getText());
            this.profileNameField.setText("Usuario");
            this.passwordField.setText("Contraseña");
            switch(rol){
                case "receptionist" -> AccessPanel.changeContent("r_menu");
                case "manager" -> AccessPanel.changeContent("m_menu");
                case "director" -> AccessPanel.changeContent("d_menu");
                default ->  JOptionPane.showMessageDialog(null,
                        "Usuario o contraseña invalidos, intente nuevamente",
                        "Error al autenticar",
                        JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException | NullPointerException e ) {
            JOptionPane.showMessageDialog(null,
                    "Algo ha ido mal! verifica que los campos hayan sido rellenados correctamente solo se aceptan" +
                            "letras, espacios y numeros",
                    "Error al autenticar",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public JPanel getPanel() {
        return LogInBG;
    }
}
