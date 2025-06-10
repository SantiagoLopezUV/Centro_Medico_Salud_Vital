package view.director_templates;

import dao.DirectorDao;
import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class D_MonthlyAddServIncome implements AccessPanel {
    private JPanel D_MonthlyServIncomeBG;
    private JLabel D_MonthlyServIncome_TitleIcon;
    private JScrollPane monthlyIncomeScrollPanel;
    private JTable monthlyIncomeTable;
    private JButton D_MonthlyServIncome_ReturnBttn;
    private JComboBox D_MonthlyServIncome_comboBoxYear;
    private JComboBox D_MonthlyServIncome_comboBoxMonth;
    private JButton D_MonthlyServIncome_SearchBttn;
    private JTextField D_MonthlyServIncome_TotalIncomeText;
    private JLabel D_ArrangementPatient_SubTitleDir;


    public D_MonthlyAddServIncome() {
        this.D_MonthlyServIncome_TotalIncomeText.setText("Ingreso Total");
        this.D_MonthlyServIncome_ReturnBttn.addActionListener(e -> {
            AccessPanel.changeContent("D_menu");
            destroyData();
        });
        this.D_MonthlyServIncome_SearchBttn.addActionListener(e -> initTables());
        establishComboBoxesMonthYearValues(D_MonthlyServIncome_comboBoxMonth, D_MonthlyServIncome_comboBoxYear);
    }

    @Override
    public JPanel getPanel() {
        return this.D_MonthlyServIncomeBG;
    }



    private void initTables(){
        DirectorDao directorDao = new DirectorDao();
        Object[][] data =null;
        String year;
        int month;
        double income = 0;


        try {

            if(Objects.requireNonNull(this.D_MonthlyServIncome_comboBoxYear.getSelectedItem()).toString().isBlank()
            || Objects.requireNonNull(this.D_MonthlyServIncome_comboBoxMonth.getSelectedItem()).toString().isBlank())
                throw new NoSuchFieldException();

            year = this.D_MonthlyServIncome_comboBoxYear.getSelectedItem().toString();
            month = this.D_MonthlyServIncome_comboBoxMonth.getSelectedIndex();


            data = directorDao.getMonthlyAdditionalServsIncome(year, month);



            for(int i=0; i<data.length; i++){
                income = income + (double) data[i][3];
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchFieldException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Verifica que los campos año y mes estén correctos",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }


        this.D_MonthlyServIncome_TotalIncomeText.setText(String.valueOf(income));
        // table heads
        String[] columnNames = {"Fecha", "Servicio", "Ref Factura", "Valor facturado"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.monthlyIncomeTable.setModel(tableModel);
        resizeColumnsTable(this.monthlyIncomeTable, MAIN_PANEL.getWidth()*2/3);

    }


    private void destroyData() {
        this.D_MonthlyServIncome_TotalIncomeText.setText("Ingreso Total");
        this.D_MonthlyServIncome_comboBoxYear.setSelectedIndex(0);
        this.D_MonthlyServIncome_comboBoxMonth.setSelectedIndex(0);
        this.monthlyIncomeTable.setModel(new DefaultTableModel());
    }


}
