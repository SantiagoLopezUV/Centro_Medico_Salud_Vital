package view.receptionist_templates;

import dao.ManagerDao;
import dao.ReceptionistDao;
import model.ConsultationType;
import utils.AccessPanel;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class R_CostService implements AccessPanel {
    private JPanel R_CostServicePanel;
    private JLabel R_CostServiceRecepcionistTitle;
    private JLabel R_CostServiceSubTitleRecep;
    private JLabel R_CostService_lblConsult;
    private JButton R_CostService_ReturnBttn;
    private JLabel R_CostService_lblValues;
    private JFormattedTextField R_CostService_IdPatientField;
    private JButton R_CostService_CalculateBttn;
    private JList<ConsultationType> list1;
    private JList list2;
    private JScrollPane scroll1;
    private JScrollPane scroll2;

    public R_CostService() {

        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list1.setVisibleRowCount(8);
        list2.setVisibleRowCount(8);

        this.R_CostService_CalculateBttn.addActionListener(e -> {
            try{
                List<ConsultationType> listTypeConsultations = ReceptionistDao.getConsultationTypes();

                DefaultListModel<ConsultationType> listModelNames = new DefaultListModel<>();
                DefaultListModel<String> listModelValues = new DefaultListModel<>();

                for (ConsultationType TypeConsultation : listTypeConsultations) {
                    listModelNames.addElement(TypeConsultation);
                    listModelValues.addElement(String.valueOf(TypeConsultation.getConsultationPrice()));
                }
                list1.setModel(listModelNames);
                list2.setModel(listModelValues);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this.R_CostServicePanel, "Error al Cargar Información");
            }
        });


        this.R_CostService_ReturnBttn.addActionListener(e ->
                AccessPanel.changeContent("R_Menu_Consultation"));



    }

    @Override
    public JPanel getPanel() {
        return R_CostServicePanel;
    }
}
