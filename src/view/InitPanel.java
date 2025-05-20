package view;

import utils.AccessPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitPanel implements AccessPanel {
    private JLabel welcomeLabel;
    private JLabel welcomeGif;
    private JPanel initPanel_BG;
    private JButton authButton;

    public InitPanel() {

        this.authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccessPanel.changeContent("Login");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return initPanel_BG;
    }
}
