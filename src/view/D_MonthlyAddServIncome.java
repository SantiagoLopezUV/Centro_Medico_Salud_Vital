package view;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class D_MonthlyAddServIncome implements AccessPanel {
    private JPanel D_monthlyAddServIncomeBG;
    private JLabel monthlyAddServIncomeIcon;
    private JLabel directiveIcon;
    private JScrollPane MonthlyIncomeScrollPanel;
    private JTable monthlyIncomeTable;
    private JButton backBttn;
    private JComboBox yearComoBox;
    private JComboBox monthComboBox;
    private JButton searchMonthBttn;


    public D_MonthlyAddServIncome() {

        this.backBttn.addActionListener(e -> AccessPanel.changeContent("D_menu"));
        initTables();

    }

    @Override
    public JPanel getPanel() {
        return this.D_monthlyAddServIncomeBG;
    }


    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] dataPatientPerArrangement= {
                {0, "Rayos X", 50_000.},
                {1, "Examen de sangre", 40_000.},
                {2, "Conteo de defensas", 40_000.}
        };

        //----
        // table heads
        String[] columnNamePatientsPerArrangement = {"Fecha", "Tipo", "Médico",
                "Estado", "Valor factura", "% convenio"};
        // adding into to table
        DefaultTableModel tableModelPatientArrang = new DefaultTableModel(dataPatientPerArrangement,
                columnNamePatientsPerArrangement);
        this.monthlyIncomeTable.setModel(tableModelPatientArrang);



    }
}
