package view;

import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class D_ArrangementPatientInfoPanel implements AccessPanel {
    private JPanel D_arrangementPatientInfoBG;
    private JLabel directiveIcon;
    private JLabel arrangementPatientsIcon;
    private JButton searchPatientBttn;
    private JButton backBttn;
    private JTextField searchPatientField;
    private JTable patientsArrangementTable;
    private JScrollPane patientPerArrangementScrollPanel;
    private JLabel patientNamesLbl;

    public D_ArrangementPatientInfoPanel() {
        this.searchPatientField.addFocusListener(new PlaceHoldersAction(this.searchPatientField,
                "Ingrese DNI del paciente"));
        this.backBttn.addActionListener(e -> AccessPanel.changeContent("D_menu"));
        initTables();
    }

    @Override
    public JPanel getPanel() {
        return D_arrangementPatientInfoBG;
    }

    //Se debe modificar para cuando se hagam las respectivas querys
    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] dataPatientPerArrangement= {
                {0, "Rayos X", 50_000.},
                {1, "Examen de sangre", 40_000.},
                {2, "Conteo de defensas", 40_000.}
        };

        //----
        //table name
        this.patientNamesLbl.setText("Jhon Doe");
        this.patientNamesLbl.setVisible(true);
        // table heads
        String[] columnNamePatientsPerArrangement = {"Fecha", "Tipo", "Médico",
                "Estado", "Valor factura", "% convenio"};
        // adding into to table
        DefaultTableModel tableModelPatientArrang = new DefaultTableModel(dataPatientPerArrangement,
                columnNamePatientsPerArrangement);
        this.patientsArrangementTable.setModel(tableModelPatientArrang);



     }

}

