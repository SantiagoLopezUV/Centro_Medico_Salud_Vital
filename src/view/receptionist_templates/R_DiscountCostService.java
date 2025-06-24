package view.receptionist_templates;

import dao.ReceptionistDao;
import model.Arrangement;
import model.ConsultationType;
import utils.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class R_DiscountCostService implements AccessPanel {
    private JPanel R_DiscountCostServicePanel;
    private JFormattedTextField R_DiscountCostService_TextField_Agreement;
    private JComboBox R_DiscountCostService_comboBoxService;
    private JFormattedTextField R_DiscountCostService_ValueField;
    private JLabel R_DiscountCostServiceRecepcionistTitle;
    private JLabel R_DiscountCostServiceSubTitleRecep;
    private JLabel R_DiscountCostService_lblService;
    private JLabel R_DiscountCostService_lblAgreement;
    private JLabel R_DiscountCostService_lblValue;
    private JButton R_DiscountService_ReturnBttn;
    private JLabel R_DiscountCostService_lblPatient;
    private JFormattedTextField R_DiscountCostService_IdPatientField;
    private JLabel R_DiscountCostService_lblStatus;
    private JFormattedTextField R_DiscountCostService_StatusField;
    private JLabel R_DiscountCostService_lblDiscount;
    private JFormattedTextField R_DiscountCostServiceValueDiscountField;
    private JButton R_DiscountCostService_CalculateBttn;
    private JLabel R_DiscountCostService_dealIcon;

    private static final String placeHolderPatientId = "Número de Identificación";

    public R_DiscountCostService() {

        this.R_DiscountCostService_IdPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_DiscountCostService_IdPatientField, placeHolderPatientId));
        this.R_DiscountCostService_IdPatientField.addKeyListener(
                new KeyListenerParaInt());

        this.R_DiscountService_ReturnBttn.addActionListener(e -> {
                    AccessPanel.changeContent("R_Menu_Consultation");
                    destroyData();
                });
        try {
            ReceptionistDao receptionistDao = new ReceptionistDao();
            InitComboBoxes<ConsultationType> iCombo = new InitComboBoxes<>();
            iCombo.InitComboBoxesWithArrayList(this.R_DiscountCostService_comboBoxService, receptionistDao.getConsultationTypes());



        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }

        this.R_DiscountCostService_CalculateBttn.addActionListener(e -> startCalc());


    }

    private void startCalc() {
        ReceptionistDao receptionistDao = new ReceptionistDao();
        Arrangement arrangement;

        try {
            arrangement = receptionistDao.getArrangementForPatient(Long.parseLong(this.R_DiscountCostService_IdPatientField.getText()));

            this.R_DiscountCostService_StatusField.setText(
                    (arrangement.isValid()) ? "Valido" : "No válido"
            );
            this.R_DiscountCostServiceValueDiscountField.setText(
                    Math.round(arrangement.getPercentage() * 100) + "%"
            );
            this.R_DiscountCostService_TextField_Agreement.setText(arrangement.toString());

            ConsultationType ct = (ConsultationType) Objects.requireNonNull(this.R_DiscountCostService_comboBoxService.getSelectedItem());

            this.R_DiscountCostService_ValueField.setText(
                    "$" + ((arrangement.isValid()) ?
                            Math.round((1 - arrangement.getPercentage()) * ct.getConsultationPrice())
                            : ct.getConsultationPrice())
            );


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
            destroyData();
        } catch (NumberFormatException | NullPointerException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Verifica que el documento de identidad sea válido",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
            destroyData();
        }

    }


    private void destroyData() {
        this.R_DiscountCostService_IdPatientField.setText(placeHolderPatientId);
        this.R_DiscountCostService_comboBoxService.setSelectedIndex(0);
        this.R_DiscountCostService_StatusField.setText("");
        this.R_DiscountCostService_ValueField.setText("");
        this.R_DiscountCostServiceValueDiscountField.setText("");
        this.R_DiscountCostService_TextField_Agreement.setText("");
    }

    @Override
    public JPanel getPanel() {
        return R_DiscountCostServicePanel;
    }
}
