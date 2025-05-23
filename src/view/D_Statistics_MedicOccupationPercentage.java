package view;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_Statistics_MedicOccupationPercentage implements AccessPanel {
    private JPanel D_Statistics_medicOccupationPercentageBG;
    private JLabel directiveIcon;
    private JLabel MedicPercentageTitle;
    private JButton backBttn;
    private JTable medicOccupationPercentageTable;
    private JScrollPane medicOccupationPercentageScrollPanel;

    public D_Statistics_MedicOccupationPercentage() {
        this.backBttn.addActionListener(e ->
                AccessPanel.changeContent("D_statistics"));
        initTables();
    }

    @Override
    public JPanel getPanel() {
        return this.D_Statistics_medicOccupationPercentageBG;
    }

    //Se debe modificar para cuando se hagam las respectivas querys
    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] data= {
                {"Camila Ríos", 0.10},
                {"Juan Martínez",0.40},
                {"Laura Gómez", 0.0},
                {"Andrés Pérez", 0.20},
                {"Silvia Torres", 0.15}
        };

        //----
        // table heads
        String[] columnNames = {"Médico","% ocupación"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.medicOccupationPercentageTable.setModel(tableModel);

        int maxWidth = MAIN_PANEL.getWidth()/2;
        int maxHeight = MAIN_PANEL.getHeight()/2;

        this.medicOccupationPercentageScrollPanel.setPreferredSize(new Dimension(maxWidth,
                maxHeight));
        this.medicOccupationPercentageScrollPanel.setMaximumSize(new Dimension(maxWidth,
                maxHeight));
        resizeColumnsTable(medicOccupationPercentageTable, maxWidth);



    }
}
