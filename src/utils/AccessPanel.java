package utils;

import view.MainPanel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public interface AccessPanel {

    MainPanel MAIN_PANEL = new MainPanel();
    JPanel getPanel();

    static void changeContent (String panelName) {
        try{
            JPanel panelToPresent = PanelsMap.valueOf(panelName.toUpperCase()).getPanelToRender();
            panelToPresent.setSize(800,600);
            panelToPresent.setMaximumSize(new Dimension(800,600));
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
            int lastColumnExtraPiece = totalPanelWidth - colWidth*colCount;

            for (int i = 0; i < colCount; i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                if(i == colCount - 1){
                    colWidth += lastColumnExtraPiece;
                }
                column.setPreferredWidth(colWidth);
                column.setMinWidth(colWidth);
            }
    }

    default void establishComboBoxesMonthYearValues(JComboBox monthComboBox, JComboBox yearComboBox) {
        String[] months = new String[12];
        for (Month month : Month.values()) {
            months[month.getValue() - 1] = month.getDisplayName(TextStyle.FULL, new Locale("es"));
        }
        new InitComboBoxes(monthComboBox, months);

        new InitComboBoxes(yearComboBox, "2024", "2025");
    }


}
