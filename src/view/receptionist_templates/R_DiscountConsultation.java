package view.receptionist_templates;

import dao.ReceptionistDao;
import model.Arrangement;
import model.ConsultationType;
import utils.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class R_DiscountConsultation implements AccessPanel {
    private JPanel R_DiscountCostServicePanel;
    private JFormattedTextField R_DiscountCostConsultation_TextField_Agreement;
    private JComboBox R_DiscountCostConsultation_comboBoxConsultation;
    private JFormattedTextField R_DiscountCostConsultation_ValueField;
    private JLabel R_DiscountCostConsultationRecepcionistTitle;
    private JLabel R_DiscountCostConsultationSubTitleRecep;
    private JLabel R_DiscountCostConsultation_lblConsultation;
    private JLabel R_DiscountCostConsultation_lblAgreement;
    private JLabel R_DiscountCostConsultation_lblValue;
    private JButton R_DiscountConsultation_ReturnBttn;
    private JLabel R_DiscountCostConsultation_lblPatient;
    private JFormattedTextField R_DiscountCostConsultation_IdPatientField;
    private JLabel R_DiscountCostConsultation_lblStatus;
    private JFormattedTextField R_DiscountCostConsultation_StatusField;
    private JLabel R_DiscountCostConsultation_lblDiscount;
    private JFormattedTextField R_DiscountCostConsultationValueDiscountField;
    private JButton R_DiscountCostConsultation_CalculateBttn;
    private JLabel R_DiscountCostConsultation_dealIcon;

    private static final String placeHolderPatientId = "Número de Identificación";

    public R_DiscountConsultation() {

        this.R_DiscountCostConsultation_IdPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_DiscountCostConsultation_IdPatientField, placeHolderPatientId));
        this.R_DiscountCostConsultation_IdPatientField.addKeyListener(
                new KeyListenerParaInt());

        this.R_DiscountConsultation_ReturnBttn.addActionListener(e -> {
                    AccessPanel.changeContent("R_Menu_Consultation");
                    destroyData();
                });
        try {
            ReceptionistDao receptionistDao = new ReceptionistDao();
            InitComboBoxes<ConsultationType> iCombo = new InitComboBoxes<>();
            iCombo.InitComboBoxesWithArrayList(this.R_DiscountCostConsultation_comboBoxConsultation, receptionistDao.getConsultationTypes());



        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }

        this.R_DiscountCostConsultation_CalculateBttn.addActionListener(e -> startCalc());


    }

    private void startCalc() {
        ReceptionistDao receptionistDao = new ReceptionistDao();
        Arrangement arrangement;

        try {
            arrangement = receptionistDao.getArrangementForPatient(Long.parseLong(this.R_DiscountCostConsultation_IdPatientField.getText()));

            this.R_DiscountCostConsultation_StatusField.setText(
                    (arrangement.isValid()) ? "Valido" : "No válido"
            );
            this.R_DiscountCostConsultationValueDiscountField.setText(
                    Math.round(arrangement.getPercentage() * 100) + "%"
            );
            this.R_DiscountCostConsultation_TextField_Agreement.setText(arrangement.toString());

            ConsultationType ct = (ConsultationType) Objects.requireNonNull(this.R_DiscountCostConsultation_comboBoxConsultation.getSelectedItem());

            this.R_DiscountCostConsultation_ValueField.setText(
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
        this.R_DiscountCostConsultation_IdPatientField.setText(placeHolderPatientId);
        this.R_DiscountCostConsultation_comboBoxConsultation.setSelectedIndex(0);
        this.R_DiscountCostConsultation_StatusField.setText("");
        this.R_DiscountCostConsultation_ValueField.setText("");
        this.R_DiscountCostConsultationValueDiscountField.setText("");
        this.R_DiscountCostConsultation_TextField_Agreement.setText("");
    }

    @Override
    public JPanel getPanel() {
        return R_DiscountCostServicePanel;
    }
}
