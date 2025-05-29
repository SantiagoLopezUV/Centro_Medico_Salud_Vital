package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_Statistics_MedicOccupationPercentage implements AccessPanel {
    private JPanel D_Statistics_medicOccupationPercentageBG;
    private JLabel MedicPercentageTitle;
    private JButton backBttn;
    private JTable medicOccupationPercentageTable;
    private JScrollPane medicOccupationPercentageScrollPanel;
    private JLabel breadCrumbLbl;
    private JComboBox yearComoBox;
    private JComboBox monthComboBox;
    private JButton searchMonthBttn;

    public D_Statistics_MedicOccupationPercentage() {
        this.backBttn.addActionListener(e ->
                AccessPanel.changeContent("D_statistics"));
        initTables();
        establishComboBoxesMonthYearValues(monthComboBox, yearComoBox);
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
                {123411234, "Camila Ríos", 0.10},
                {12333567, "Juan Martínez",0.40},
                {3455666, "Laura Gómez", 0.0},
                {2342555, "Andrés Pérez", 0.20},
                {23425555, "Silvia Torres", 0.15}
        };

        //----
        // table heads
        String[] columnNames = {"Doc Identidad", "Médico","% ocupación"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.medicOccupationPercentageTable.setModel(tableModel);

        int maxWidth = MAIN_PANEL.getWidth()*2/3;
        int maxHeight = MAIN_PANEL.getHeight()*2/3;

        this.medicOccupationPercentageScrollPanel.setPreferredSize(new Dimension(maxWidth,
                maxHeight));
        this.medicOccupationPercentageScrollPanel.setMaximumSize(new Dimension(maxWidth,
                maxHeight));
        resizeColumnsTable(medicOccupationPercentageTable, maxWidth);



    }
}
