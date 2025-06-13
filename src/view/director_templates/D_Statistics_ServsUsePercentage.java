package view.director_templates;

import dao.DirectorDao;
import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

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
        this.D_ServUsePercentage_ReturnBttn.addActionListener(e -> {
                AccessPanel.changeContent("D_statistics");
                destroyData();
        });
        this.D_ServUsePercentage_SearchMonthBttn.addActionListener(e -> initTables());
        establishComboBoxesMonthYearValues(D_ServUsePercentage_comboBoxMonth, D_ServUsePercentage_comboBoxYear);
        this.D_ServUsePercentage_TotalTextField.setText("Total uso de servicios");
    }

    @Override
    public JPanel getPanel() {
        return this.D_ServUsePercentageBG;
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

    private void destroyData() {
        this.D_ServUsePercentage_TotalTextField.setText("Total uso de servicios");
        this.D_ServUsePercentage_comboBoxMonth.setSelectedIndex(0);
        this.D_ServUsePercentage_comboBoxYear.setSelectedIndex(0);
        this.addServsPercentageTable.setModel(new DefaultTableModel());
    }

}
