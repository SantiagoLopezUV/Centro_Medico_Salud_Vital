package view.director_templates;

import dao.DirectorDao;
import utils.AccessPanel;
import utils.KeyListenerParaInt;
import utils.PlaceHoldersAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class D_ArrangementPatientInfoPanel implements AccessPanel {
    private JPanel D_ArrangementPatientIBG;
    private JLabel D_ArrangementPatient_TitleIcon;
    private JButton D_ArrangementPatient_SearchPatientBttn;
    private JButton D_ArrangementPatient_ReturnBttn;
    private JTextField D_ArrangementPatient_SearchPatientField;
    private JTable patientsArrangementTable;
    private JScrollPane patientPerArrangementScrollPanel;
    private JLabel D_ArrangementPatient_lblNames;
    private JLabel D_ArrangementPatient_SubTitleDir;

    public D_ArrangementPatientInfoPanel() {
        this.D_ArrangementPatient_SearchPatientField.addFocusListener(new PlaceHoldersAction(this.D_ArrangementPatient_SearchPatientField,
                "Ingrese DNI del paciente"));
        this.D_ArrangementPatient_SearchPatientField.addKeyListener(new KeyListenerParaInt(this.D_ArrangementPatient_SearchPatientField));
        this.D_ArrangementPatient_SearchPatientBttn.addActionListener(e -> initTables());
        this.D_ArrangementPatient_ReturnBttn.addActionListener(e -> {
            destroyData();
            AccessPanel.changeContent("D_menu");
        });
    }

    @Override
    public JPanel getPanel() {
        return D_ArrangementPatientIBG;
    }

    private void initTables(){
        DirectorDao directorDao = new DirectorDao();

        Object[][] data= null;
        String pFullName = null;

        try {
            long id = Long.parseLong(this.D_ArrangementPatient_SearchPatientField.getText());
            pFullName = directorDao.getPatientFullName(id);
            if(pFullName == null) throw new NoSuchFieldException();
            data = directorDao.getArrgmtsPerPatient(id);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Solo se admite números, intenta nuevamente",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "El paciente no existe en la base de datos",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }

        this.D_ArrangementPatient_lblNames.setText(pFullName);
        this.D_ArrangementPatient_lblNames.setVisible(true);
        // table heads
        String[] columnNames = {"Fecha", "Tipo", "Médico", "Estado", "Valor factura", "% convenio"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data,
                columnNames);
        this.patientsArrangementTable.setModel(tableModel);

        int maxPanelWidth = 400;
        int maxPanelHeight = 500;

        patientPerArrangementScrollPanel.setPreferredSize(new Dimension(maxPanelWidth, maxPanelHeight));
        patientPerArrangementScrollPanel.setMaximumSize(new Dimension(maxPanelWidth, maxPanelHeight));

        resizeColumnsTable(this.patientsArrangementTable, maxPanelWidth);


     }
    private void destroyData(){
        this.D_ArrangementPatient_SearchPatientField.setText("Ingrese DNI del paciente");
        this.D_ArrangementPatient_lblNames.setText("");
        this.D_ArrangementPatient_lblNames.setVisible(false);
        this.patientsArrangementTable.setModel(new DefaultTableModel());
    }
}

