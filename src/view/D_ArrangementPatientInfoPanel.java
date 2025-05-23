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
        Object[][] data= {
                {"2025-05-10", "general", "Camila Ríos", "Pagado", 120000, 0.10},
                {"2025-04-28", "urgencias", "Juan Martínez", "En curso", 5600000, 0.40},
                {"2025-03-15", "odontología", "Laura Gómez", "Anulado", 350000, 0.0},
                {"2025-05-05", "especializada", "Andrés Pérez", "Pendiente por pago", 220000, 0.20},
                {"2025-02-20", "general", "Silvia Torres", "Pagado", 150000, 0.15}
        };

        //----
        //table name
        this.patientNamesLbl.setText("Jhon Doe");
        this.patientNamesLbl.setVisible(true);
        // table heads
        String[] columnNames = {"Fecha", "Tipo", "Médico", "Estado", "Valor factura", "% convenio"};
        // adding into to table
        DefaultTableModel tableModel = new DefaultTableModel(data,
                columnNames);
        this.patientsArrangementTable.setModel(tableModel);



     }

}

