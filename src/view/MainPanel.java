package view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame{
    private JPanel mainBG;
    private JPanel contentPanel;

    public MainPanel(){
        this.setContentPane(this.mainBG);
        this.setTitle("Centro médico Salud Vital");
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        setSize(600,400);
        setMaximumSize(new Dimension(600,400));
        //center window
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel getMainContent(){
        return contentPanel;
    }
}
