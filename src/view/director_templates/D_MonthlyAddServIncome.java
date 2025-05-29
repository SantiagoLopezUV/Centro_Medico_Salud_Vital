package view.director_templates;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class D_MonthlyAddServIncome implements AccessPanel {
    private JPanel D_monthlyAddServIncomeBG;
    private JLabel monthlyAddServIncomeIcon;
    private JScrollPane monthlyIncomeScrollPanel;
    private JTable monthlyIncomeTable;
    private JButton backBttn;
    private JComboBox yearComoBox;
    private JComboBox monthComboBox;
    private JButton searchMonthBttn;
    private JTextField totalIncome;
    private JLabel breadCrumbLbl;


    public D_MonthlyAddServIncome() {
        this.totalIncome.setText("Ingreso Total");
        this.backBttn.addActionListener(e -> {
            AccessPanel.changeContent("D_menu");
            this.totalIncome.setText("Ingreso Total");
        });
        initTables();
        establishComboBoxesMonthYearValues(monthComboBox, yearComoBox);
    }

    @Override
    public JPanel getPanel() {
        return this.D_monthlyAddServIncomeBG;
    }



    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] data= {
                {"2025-04-11", "Rayos X", "125", 85000},
                {"2025-04-09", "Pruebas de sangre", "112", 45000},
                {"2025-04-30", "Ortopedia", "98", 120000},
                {"2025-04-01", "Electrocardiograma", "103", 67000},
                {"2025-04-25", "Fisioterapia", "89", 95000}
        };

        int income = 0;

        for(int i=0; i<data.length; i++){
            income += Integer.parseInt(data[i][3].toString());
        }
        this.totalIncome.setText(String.valueOf(income));
        //----
        // table heads
        String[] columnNames = {"Fecha", "Servicio", "Ref Factura", "Valor facturado"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.monthlyIncomeTable.setModel(tableModel);
        resizeColumnsTable(this.monthlyIncomeTable, MAIN_PANEL.getWidth()*2/3);


    }
}
