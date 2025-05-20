package utils;

import view.MainPanel;

import javax.swing.*;
import java.awt.*;

public interface AccessPanel {

    MainPanel MAIN_PANEL = new MainPanel();
    JPanel getPanel();

    static void changeContent (String panelName) {
        try{
            JPanel panelToPresent = PanelsMap.valueOf(panelName.toUpperCase()).getPanelToRender();
            panelToPresent.setSize(600,400);
            panelToPresent.setMaximumSize(new Dimension(600,400));
            panelToPresent.setLocation(0,0);
            panelToPresent.setFocusable(false);
            JPanel mainPanel = MAIN_PANEL.getMainContent();
            mainPanel.removeAll();
            mainPanel.add(panelToPresent,BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
            panelToPresent.setFocusable(true);
        }catch (Exception e){
            System.err.println(panelName + " - change content\n");
            e.printStackTrace();
        }
    }

    static ImageIcon scaleImage(ImageIcon icon, int height, int with ){
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(with, height, Image.SCALE_FAST);
        return new ImageIcon(scaledImage);
    }

}
