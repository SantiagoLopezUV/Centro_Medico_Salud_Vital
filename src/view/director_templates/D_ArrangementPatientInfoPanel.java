package view.director_templates;

import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
                "Ingrese DNI del Paciente"));
        this.D_ArrangementPatient_ReturnBttn.addActionListener(e -> AccessPanel.changeContent("D_menu"));
        initTables();
    }

    @Override
    public JPanel getPanel() {
        return D_ArrangementPatientIBG;
    }

    //Se debe modificar para cuando se hagan las respectivas querys
    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] data= {
                {"2025-05-10", "general", "Camila Ríos", "Pagado", 120000, 0.10},
                {"2025-04-28", "urgencias", "Juan Martínez", "En curso", 5600000, 0.40},
                {"2025-03-15", "odontología", "Laura Gómez", "Anulado", 350000, 0.0},
                {"2025-05-05", "especializada", "Andrés Pérez", "Pendiente por pago", 220000, 0.20},
                {"2025-02-20", "general", "Silvia Torres", "Pagado", 150000, 0.15}
        };

        //----
        //table name
        this.D_ArrangementPatient_lblNames.setText("Jhon Doe");
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

}

