package view.director_templates;

import dao.DirectorDao;
import utils.AccessPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class D_MedicAdditionalServsAndEspecialtiesPanel implements AccessPanel {
    private JPanel D_MedicAdiServiAndSpecialtiesBG;
    private JButton D_AdiServiceAndEspecialities_ReturnBttn;
    private JLabel D_AddServiceEspecialities_lblAdditionalServi;
    private JLabel D_AddServiceEspecialities_lblSpecialties;
    private JLabel D_AddServiceEspecialities_TitleIcon;
    private JScrollPane specialtiesScrollPanel;
    private JScrollPane servAddiScrollPanel;
    private JTable servAddiTable;
    private JTable specialtiesTable;
    private JLabel D_Menu_SubTitleDir;
    private JButton exeQuery;

    public D_MedicAdditionalServsAndEspecialtiesPanel() {
        int maxPanelWidth = 350;
        int maxPanelHeight = 400;

        servAddiScrollPanel.setPreferredSize(new Dimension(maxPanelWidth, maxPanelHeight));
        specialtiesScrollPanel.setPreferredSize(new Dimension(maxPanelWidth, maxPanelHeight));
        servAddiScrollPanel.setMaximumSize(new Dimension(maxPanelWidth, maxPanelHeight));
        specialtiesScrollPanel.setMaximumSize(new Dimension(maxPanelWidth, maxPanelHeight));

        this.exeQuery.addActionListener(e ->
                initTables());

        this.D_AdiServiceAndEspecialities_ReturnBttn.addActionListener(e ->{
                    destroyTablesData();
                    AccessPanel.changeContent("D_menu");
                });
    }

    @Override
    public JPanel getPanel() {
        return D_MedicAdiServiAndSpecialtiesBG;
    }

    private void initTables(){
        DirectorDao directorDao = new DirectorDao();

        Object[][] dataServAdd= null;
        Object[][] dataSpe = null;
        try {
            dataServAdd = directorDao.getAdditionalServs();
            dataSpe = directorDao.getMedicSpecilities();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }
        directorDao = null;
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



        resizeColumnsTable(this.servAddiTable, this.servAddiScrollPanel.getWidth());
        resizeColumnsTable(this.specialtiesTable, this.specialtiesScrollPanel.getWidth());


    }

    private void destroyTablesData(){
        this.servAddiTable.setModel(new DefaultTableModel());
        this.specialtiesTable.setModel(new DefaultTableModel());
    }

}
