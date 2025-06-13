package view.director_templates;

import dao.DirectorDao;
import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

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
        this.D_ArrangementCancelled_TotalTextField.setText("Total citas anuladas");
        this.D_ArrangementCancelled_ReturnBttn.addActionListener(e -> {
                AccessPanel.changeContent("D_statistics");
                destroyData();
        });
        this.D_ArrangementCancelled_SearchMonthBttn.addActionListener(e -> initTables());
        establishComboBoxesMonthYearValues(D_ArrangementCancelled_comboBoxMonth, D_ArrangementCancelled_comboBoxYear);
    }

    @Override
    public JPanel getPanel() {
        return this.D_Statistics_arrangementAnnulledRateBG;
    }

    private void initTables(){

        DirectorDao directorDao = new DirectorDao();
        Object[][] data =null;
        String year;
        int month;
        int totalMonthlyArrangementCount = 0;

        try {

            if(Objects.requireNonNull(this.D_ArrangementCancelled_comboBoxYear.getSelectedItem()).toString().isBlank()
                    || Objects.requireNonNull(this.D_ArrangementCancelled_comboBoxMonth.getSelectedItem()).toString().isBlank())
                throw new NoSuchFieldException();

            year = this.D_ArrangementCancelled_comboBoxYear.getSelectedItem().toString();
            month = this.D_ArrangementCancelled_comboBoxMonth.getSelectedIndex();

            totalMonthlyArrangementCount = directorDao.getArrangementCount(year, month);
            data = directorDao.getCancelledArrgRates(year, month, totalMonthlyArrangementCount);


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

        this.D_ArrangementCancelled_TotalTextField.setText(Double.toString(totalMonthlyArrangementCount));

        String[] columnNames = {"Tipo de consulta","%"};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.arrangementAnnulledRateTable.setModel(tableModel);

        resizeColumnsTable(arrangementAnnulledRateTable, this.D_ArrangementCancelled_ScrollPanel.getWidth() /*maxWidth*/);

    }

    private void destroyData() {
        this.D_ArrangementCancelled_TotalTextField.setText("Total citas anuladas");
        this.D_ArrangementCancelled_comboBoxYear.setSelectedIndex(0);
        this.D_ArrangementCancelled_comboBoxYear.setSelectedIndex(0);
        this.arrangementAnnulledRateTable.setModel(new DefaultTableModel());
    }

}
