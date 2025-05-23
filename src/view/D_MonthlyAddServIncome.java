package view;

import utils.AccessPanel;
import utils.InitComboBoxes;
import utils.ResizeColumnsTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class D_MonthlyAddServIncome implements AccessPanel {
    private JPanel D_monthlyAddServIncomeBG;
    private JLabel monthlyAddServIncomeIcon;
    private JLabel directiveIcon;
    private JScrollPane monthlyIncomeScrollPanel;
    private JTable monthlyIncomeTable;
    private JButton backBttn;
    private JComboBox yearComoBox;
    private JComboBox monthComboBox;
    private JButton searchMonthBttn;
    private JTextField totalIncome;


    public D_MonthlyAddServIncome() {
        this.totalIncome.setText("Ingreso Total");
        this.backBttn.addActionListener(e -> {
            AccessPanel.changeContent("D_menu");
            this.totalIncome.setText("Ingreso Total");
        });
        initTables();
        establishComboBoxesValues();
    }

    @Override
    public JPanel getPanel() {
        return this.D_monthlyAddServIncomeBG;
    }

    private void establishComboBoxesValues(){
        String[] months = new String[12];
        for (Month month : Month.values()) {
            months[month.getValue()-1] = month.getDisplayName(TextStyle.FULL, new Locale("es"));
        }
        new InitComboBoxes(this.monthComboBox, months);

        new InitComboBoxes(this.yearComoBox, "2024", "2025");

    }


    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] dataPatientPerArrangement= {
                {"2025-04-11", "Rayos X", "FAC-00125", 85000},
                {"2025-04-09", "Pruebas de sangre", "FAC-00112", 45000},
                {"2025-04-30", "Ortopedia", "FAC-00098", 120000},
                {"2025-04-01", "Electrocardiograma", "FAC-00103", 67000},
                {"2025-04-25", "Fisioterapia", "FAC-00089", 95000}
        };

        int income = 0;

        for(int i=0; i<dataPatientPerArrangement.length; i++){
            income += Integer.parseInt(dataPatientPerArrangement[i][3].toString());
        }
        this.totalIncome.setText(String.valueOf(income));
        //----
        // table heads
        String[] columnNamePatientsPerArrangement = {"Fecha", "Servicio", "Ref Factura",
                "Valor facturado"};
        // adding into to table
        DefaultTableModel tableModelPatientArrang = new DefaultTableModel(dataPatientPerArrangement,
                columnNamePatientsPerArrangement);
        this.monthlyIncomeTable.setModel(tableModelPatientArrang);
        monthlyIncomeTable.addComponentListener(new ResizeColumnsTable(this.monthlyIncomeTable, MAIN_PANEL.getWidth()*2/3));


    }
}
