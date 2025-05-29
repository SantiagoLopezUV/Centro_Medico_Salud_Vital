package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_Statistics_ArrangementAnnulledRate implements AccessPanel {
    private JPanel D_Statistics_arrangementAnnulledRateBG;
    private JLabel arrangementAnnulledRateTitle;
    private JScrollPane arrangementAnnulledRateScrollPanel;
    private JTable arrangementAnnulledRateTable;
    private JButton backBttn;
    private JLabel breadCrumbLbl;
    private JComboBox yearComoBox;
    private JComboBox monthComboBox;
    private JButton searchMonthBttn;
    private JTextField totalTextField;

    public D_Statistics_ArrangementAnnulledRate() {
        this.backBttn.addActionListener(e ->
                AccessPanel.changeContent("D_statistics"));
        initTables();
        establishComboBoxesMonthYearValues(monthComboBox, yearComoBox);
        this.totalTextField.setText("Total citas anuladas");
    }

    @Override
    public JPanel getPanel() {
        return this.D_Statistics_arrangementAnnulledRateBG;
    }

    //Se debe modificar para cuando se hagan las respectivas querys
    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] data= {
                {"general", 0.10},
                {"urgencias",0.40},
                {"odontología", 0.05},
                {"especializada", 0.20}
        };

        //----
        // table heads
        String[] columnNames = {"Tipo de consulta","% de cancelación"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.arrangementAnnulledRateTable.setModel(tableModel);

        int maxWidth = MAIN_PANEL.getWidth()/2;
        int maxHeight = MAIN_PANEL.getHeight()/2;

        this.arrangementAnnulledRateScrollPanel.setPreferredSize(new Dimension(maxWidth,
                maxHeight));
        this.arrangementAnnulledRateScrollPanel.setMaximumSize(new Dimension(maxWidth,
                maxHeight));
        resizeColumnsTable(arrangementAnnulledRateTable, maxWidth);



    }
}
