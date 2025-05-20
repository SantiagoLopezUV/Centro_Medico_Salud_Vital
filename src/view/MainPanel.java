package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MainPanel extends JFrame {
    private JPanel mainBG;
    private JPanel contentPanel;
    private JPanel titleBar;
    private JLabel titleBarName;
    private JButton titleBarCloseBttn;
    private JLabel barTitleIcon;
    private Point initialClick;

    public MainPanel() {
        titleBarDragMove();
        closeBttnInit();
        this.setContentPane(this.mainBG);
        this.setUndecorated(true);
        this.setTitle("Centro médico Salud Vital");
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        setSize(600, 430);
        setMaximumSize(new Dimension(600, 430));
        //center window
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JPanel getMainContent() {
        return contentPanel;
    }

    private void closeBttnInit(){
        this.titleBarCloseBttn.setText("x");
        this.titleBarCloseBttn.addActionListener(e -> System.exit(0));
        this.titleBarCloseBttn.setContentAreaFilled(false);
        this.titleBarCloseBttn.setBorderPainted(false);
        this.titleBarCloseBttn.setFocusPainted(false);
        this.titleBarCloseBttn.setOpaque(true);

    }

    private void titleBarDragMove() {

        this.titleBar.addMouseListener(new MouseAdapter() {


            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                initialClick = null;
            }
        });

        this.titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point actual = e.getLocationOnScreen();
                setLocation(actual.x - initialClick.x, actual.y - initialClick.y);
            }
        });
    }


}
