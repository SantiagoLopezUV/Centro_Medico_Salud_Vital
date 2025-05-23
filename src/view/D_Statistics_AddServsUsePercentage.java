package view;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_Statistics_AddServsUsePercentage implements AccessPanel {
    private JPanel D_statistics_AddServsUsePercentageBG;
    private JScrollPane addServsPercentageScrollPanel;
    private JTable addServsPercentageTable;
    private JLabel addServsPercentageTitle;
    private JLabel directiveIcon;
    private JButton backBttn;

    public D_Statistics_AddServsUsePercentage() {
        this.backBttn.addActionListener(e ->
                AccessPanel.changeContent("D_statistics"));
        initTables();
    }

    @Override
    public JPanel getPanel() {
        return this.D_statistics_AddServsUsePercentageBG;
    }

    //Se debe modificar para cuando se hagam las respectivas querys
    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] data= {
                {"Rayos X", 0.10},
                {"Pruebas de sangre",0.40},
                {"Ortopedia", 0.0},
                {"Electrocardiograma", 0.20},
                {"Fisioterapia", 0.15}
        };

        //----
        // table heads
        String[] columnNames = {"Servicio adicional","% Uso"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.addServsPercentageTable.setModel(tableModel);

        int maxWidth = MAIN_PANEL.getWidth()/2;
        int maxHeight = MAIN_PANEL.getHeight()/2;

        this.addServsPercentageScrollPanel.setPreferredSize(new Dimension(maxWidth,
                maxHeight));
        this.addServsPercentageScrollPanel.setMaximumSize(new Dimension(maxWidth,
                maxHeight));
        resizeColumnsTable(addServsPercentageTable, maxWidth);



    }
}
