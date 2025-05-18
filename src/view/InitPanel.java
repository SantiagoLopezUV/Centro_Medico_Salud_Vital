package view;

import utils.AccessPanel;

import javax.swing.*;

public class InitPanel implements AccessPanel {
    private JLabel welcomeLabel;
    private JLabel welcomeGif;
    private JPanel initPanel_BG;
    private JButton logInButton;





    @Override
    public JPanel getPanel() {
        return initPanel_BG;
    }
}
