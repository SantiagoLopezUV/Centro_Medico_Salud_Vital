package view;

import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class D_MedicAdditionalServsAndEspecialtiesPanel implements AccessPanel {
    private JPanel D_MedicAdiServiAndSpecialtiesBG;
    private JButton backBttn;
    private JLabel additionalServlbl;
    private JLabel specialtiesLbl;
    private JLabel medicServAdiAndSpeIcon;
    private JScrollPane specialtiesScrollPanel;
    private JScrollPane servAddiScrollPanel;
    private JTable servAddiTable;
    private JTable specialtiesTable;
    private JLabel breadCrumbLbl;

    public D_MedicAdditionalServsAndEspecialtiesPanel() {
        initTables();

        this.backBttn.addActionListener(e ->
                AccessPanel.changeContent("D_menu"));
    }

    @Override
    public JPanel getPanel() {
        return D_MedicAdiServiAndSpecialtiesBG;
    }

    //Se debe modificar para cuando se hagam las respectivas querys
    private void initTables(){

        //----reemplazar por las listas obtenias por las respectivas consultas
        //----para cada tabla
        Object[][] dataServAdd= {
                {0, "Rayos X", 50_000.},
                {1, "Examen de sangre", 40_000.},
                {2, "Conteo de defensas", 40_000.}
        };

        Object[][] dataSpe = {
                { 22, "Oncología"},
                { 24, "Pediatría"},
                { 20, "Ortopedia"}
        };
        //----

        // tables heads
        String[] columnNamesServAdds = {"Código", "Nombre", "Valor"};
        String[] columnNamesSpecialties = {"Código", "Nombre"};
        // adding into to tables
        DefaultTableModel tableModelSerAdd = new DefaultTableModel(dataServAdd, columnNamesServAdds);
        assert servAddiTable != null;
        servAddiTable.setModel(tableModelSerAdd);
        DefaultTableModel tableModelSpecialties = new DefaultTableModel(dataSpe, columnNamesSpecialties);
        assert specialtiesTable != null;
        specialtiesTable.setModel(tableModelSpecialties);

        int maxPanelWidth = 250;
        int maxPanelHeight = 200;

        servAddiScrollPanel.setPreferredSize(new Dimension(maxPanelWidth, maxPanelHeight));
        specialtiesScrollPanel.setPreferredSize(new Dimension(maxPanelWidth, maxPanelHeight));
        servAddiScrollPanel.setMaximumSize(new Dimension(maxPanelWidth, maxPanelHeight));
        specialtiesScrollPanel.setMaximumSize(new Dimension(maxPanelWidth, maxPanelHeight));

        resizeColumnsTable(this.servAddiTable, maxPanelWidth);
        resizeColumnsTable(this.specialtiesTable, maxPanelWidth);



    }

}
