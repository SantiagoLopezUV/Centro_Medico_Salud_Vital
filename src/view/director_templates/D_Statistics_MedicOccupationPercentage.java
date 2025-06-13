package view.director_templates;

import dao.DirectorDao;
import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class D_Statistics_MedicOccupationPercentage implements AccessPanel {
    private JPanel D_MedicOccupationPercentageBG;
    private JLabel D_MedicOccupationPercentage_TitleIcon;
    private JButton D_MedicOccupationPercentage_ReturnBttn;
    private JTable medicOccupationPercentageTable;
    private JScrollPane D_MedicOccupationPercentage_ScrollPanel;
    private JComboBox D_MedicOccupationPercentage_comboBoxYear;
    private JComboBox D_MedicOccupationPercentage_comboBoxMonth;
    private JButton D_MedicOccupationPercentage_SearchMonthBttn;
    private JLabel D_MedicOccupationPercentage_SubTitleDir;

    public D_Statistics_MedicOccupationPercentage() {
        this.D_MedicOccupationPercentage_ReturnBttn.addActionListener(e -> {
            AccessPanel.changeContent("D_statistics");
            destroyData();
        });
        this.D_MedicOccupationPercentage_SearchMonthBttn.addActionListener(e -> initTables());
        establishComboBoxesMonthYearValues(D_MedicOccupationPercentage_comboBoxMonth, D_MedicOccupationPercentage_comboBoxYear);
    }

    @Override
    public JPanel getPanel() {
        return this.D_MedicOccupationPercentageBG;
    }

    private void initTables(){
        DirectorDao directorDao = new DirectorDao();
        Object[][] data =null;
        String year;
        int month;

        try {

            if(Objects.requireNonNull(this.D_MedicOccupationPercentage_comboBoxYear.getSelectedItem()).toString().isBlank()
                    || Objects.requireNonNull(this.D_MedicOccupationPercentage_comboBoxMonth.getSelectedItem()).toString().isBlank())
                throw new NoSuchFieldException();

            year = this.D_MedicOccupationPercentage_comboBoxYear.getSelectedItem().toString();
            month = this.D_MedicOccupationPercentage_comboBoxMonth.getSelectedIndex();

            //Cada médico tiene proyectado trabajar de lunes a viernes 40h, cada cita tiene un tiempo establecido
            //de 15min, por lo que en una semana haría 160 citas, para un total de 640 citas mensuales proyetadas

            data = directorDao.getMedicOcupationRate(year, month);


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
        String[] columnNames = {"Doc Identidad", "Apellidos", "Nombres","% ocupación"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.medicOccupationPercentageTable.setModel(tableModel);

        resizeColumnsTable(medicOccupationPercentageTable, this.D_MedicOccupationPercentage_ScrollPanel.getWidth());

    }


    private void destroyData() {
        this.D_MedicOccupationPercentage_comboBoxYear.setSelectedIndex(0);
        this.D_MedicOccupationPercentage_comboBoxMonth.setSelectedIndex(0);
        this.medicOccupationPercentageTable.setModel(new DefaultTableModel());
    }

}
