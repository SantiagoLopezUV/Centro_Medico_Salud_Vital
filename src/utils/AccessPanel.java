package utils;

import view.MainPanel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ComponentEvent;

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

    default void resizeColumnsTable(JTable table, int totalPanelWidth){
            int colCount = table.getColumnCount();
            int colWidth = totalPanelWidth / colCount;
            for (int i = 0; i < colCount; i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                column.setPreferredWidth(colWidth);
                column.setMinWidth(colWidth);
            }
    }


}
