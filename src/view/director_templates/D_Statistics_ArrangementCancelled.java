package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_Statistics_ArrangementCancelled implements AccessPanel {
    private JPanel D_Statistics_arrangementAnnulledRateBG;
    private JLabel D_ArrangementCancelled_TitleIcon;
    private JScrollPane D_ArrangementCancelled_ScrollPanel;
    private JTable arrangementAnnulledRateTable;
    private JButton D_ArrangementCancelled_ReturnBttn;
    private JComboBox D_ArrangementCancelled_comboBoxYear;
    private JComboBox D_ArrangementCancelled_comboBoxMonth;
    private JButton D_ArrangementCancelled_SearchMonthBttn;
    private JTextField D_ArrangementCancelled_TotalTextField;
    private JLabel D_ArrangementCancelled_SubTitleDir;

    public D_Statistics_ArrangementCancelled() {
        this.D_ArrangementCancelled_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("D_statistics"));
        initTables();
        establishComboBoxesMonthYearValues(D_ArrangementCancelled_comboBoxMonth, D_ArrangementCancelled_comboBoxYear);
        this.D_ArrangementCancelled_TotalTextField.setText("Total citas anuladas");
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

        this.D_ArrangementCancelled_ScrollPanel.setPreferredSize(new Dimension(maxWidth,
                maxHeight));
        this.D_ArrangementCancelled_ScrollPanel.setMaximumSize(new Dimension(maxWidth,
                maxHeight));
        resizeColumnsTable(arrangementAnnulledRateTable, maxWidth);



    }
}
