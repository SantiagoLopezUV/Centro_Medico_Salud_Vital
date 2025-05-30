package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_Statistics_MedicOccupationPercentage implements AccessPanel {
    private JPanel D_MedicOccupationPercentageBG;
    private JLabel D_MedicOccupationPercentage_TitleIcon;
    private JButton D_MedicOccupationPercentage_ReturnBttn;
    private JTable medicOccupationPercentageTable;
    private JScrollPane D_MedicOccupationPercentage_ScrollPanel;
    private JComboBox yearComoBox;
    private JComboBox monthComboBox;
    private JButton searchMonthBttn;
    private JLabel D_ServUsePercentage_SubTitleDir;

    public D_Statistics_MedicOccupationPercentage() {
        this.D_MedicOccupationPercentage_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("D_statistics"));
        initTables();
        establishComboBoxesMonthYearValues(monthComboBox, yearComoBox);
    }

    @Override
    public JPanel getPanel() {
        return this.D_MedicOccupationPercentageBG;
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

        this.D_MedicOccupationPercentage_ScrollPanel.setPreferredSize(new Dimension(maxWidth,
                maxHeight));
        this.D_MedicOccupationPercentage_ScrollPanel.setMaximumSize(new Dimension(maxWidth,
                maxHeight));
        resizeColumnsTable(medicOccupationPercentageTable, maxWidth);



    }
}
