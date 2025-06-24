package utils;

import view.MainPanel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public interface AccessPanel {

    MainPanel MAIN_PANEL = new MainPanel();
    JPanel getPanel();

    Set<LocalDate> COLOMBIAN_HOLYDAYS = Set.of(
            LocalDate.of(2025, 1, 1),   // Año Nuevo
            LocalDate.of(2025, 1, 6),   // Reyes Magos
            LocalDate.of(2025, 3, 24),  // San José (movido al lunes)
            LocalDate.of(2025, 4, 17),  // Jueves Santo
            LocalDate.of(2025, 4, 18),  // Viernes Santo
            LocalDate.of(2025, 5, 1),   // Día del Trabajo
            LocalDate.of(2025, 6, 2),   // Ascensión del Señor (movido al lunes)
            LocalDate.of(2025, 6, 23),  // Corpus Christi (movido al lunes)
            LocalDate.of(2025, 6, 30),  // Sagrado Corazón / San Pedro y San Pablo (movido al lunes)
            LocalDate.of(2025, 7, 20),  // Independencia
            LocalDate.of(2025, 8, 7),   // Batalla de Boyacá
            LocalDate.of(2025, 8, 18),  // La Asunción (movido al lunes)
            LocalDate.of(2025, 10, 13), // Día de la Raza (movido al lunes)
            LocalDate.of(2025, 11, 3),  // Todos los Santos (movido al lunes)
            LocalDate.of(2025, 11, 17), // Independencia de Cartagena (movido al lunes)
            LocalDate.of(2025, 12, 8),  // Inmaculada Concepción
            LocalDate.of(2025, 12, 25)  // Navidad
    );

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
        InitComboBoxes.InitComboBoxes(monthComboBox, months);
        InitComboBoxes.InitComboBoxes(yearComboBox, "2024", "2025");
    }


}
