package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_Statistics_ServsUsePercentage implements AccessPanel {
    private JPanel D_ServUsePercentageBG;
    private JScrollPane D_ServUsePercentage_ScrollPanel;
    private JTable addServsPercentageTable;
    private JLabel D_ServUsePercentage_TitleIcon;
    private JButton D_ServUsePercentage_ReturnBttn;
    private JComboBox D_ServUsePercentage_comboBoxYear;
    private JComboBox D_ServUsePercentage_comboBoxMonth;
    private JButton D_ServUsePercentage_SearchMonthBttn;
    private JTextField D_ServUsePercentage_TotalTextField;
    private JLabel D_ServUsePercentage_SubTitleDir;

    public D_Statistics_ServsUsePercentage() {
        this.D_ServUsePercentage_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("D_statistics"));
        initTables();
        establishComboBoxesMonthYearValues(D_ServUsePercentage_comboBoxMonth, D_ServUsePercentage_comboBoxYear);
        this.D_ServUsePercentage_TotalTextField.setText("Total uso de servicios");
    }

    @Override
    public JPanel getPanel() {
        return this.D_ServUsePercentageBG;
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
                {"Fisioterapia", 0.70}
        };

        //----
        // table heads
        String[] columnNames = {"Servicio adicional","% uso vs el total"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.addServsPercentageTable.setModel(tableModel);

        int maxWidth = MAIN_PANEL.getWidth()*2/3;
        int maxHeight = MAIN_PANEL.getHeight()*2/3;


        this.D_ServUsePercentage_ScrollPanel.setPreferredSize(new Dimension(maxWidth,
                maxHeight));
        this.D_ServUsePercentage_ScrollPanel.setMaximumSize(new Dimension(maxWidth,
                maxHeight));
        resizeColumnsTable(addServsPercentageTable, maxWidth);



    }
}
