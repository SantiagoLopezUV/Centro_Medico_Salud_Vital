package view.manager_templates;

import dao.ManagerDao;
import model.Arrangement;
import model.ConsultationType;
import utils.AccessPanel;
import utils.KeyListenerParaDouble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class M_DiscountsBenefits implements AccessPanel {
    private JPanel M_DiscountsBenefitsPanel;
    private JLabel M_DiscountsBenefits_ManagerTitle;
    private JButton M_DiscountsBenefits_ReturnBttn;
    private JButton M_DiscountsBenefits_SaveBttn;
    private JFormattedTextField M_DiscountsBenefits_NewCostDiscountField;
    private JFormattedTextField M_DiscountsBenefits_CostDiscountField;
    private JComboBox M_DiscountsBenefits_comboBoxAgreement;
    private JLabel M_DiscountsBenefits_lblAgreement;
    private JLabel M_DiscountsBenefits_lblActualDiscount;
    private JLabel M_DiscountBenefits_lblNewDiscount;
    private JLabel M_DiscountsBenefits_NotesIcon;
    private JLabel M_DiscountsBenefits_SubTitleAdmDiscountsBenefits;

    public M_DiscountsBenefits() {

        //  Configuración de label para no dejar editar
        this.M_DiscountsBenefits_CostDiscountField.setEditable(false);
        this.M_DiscountsBenefits_CostDiscountField.setFocusable(false);

        // Configuración de Text Field con Funcion KeyListener
        this.M_DiscountsBenefits_NewCostDiscountField.addKeyListener(
                new KeyListenerParaDouble(this.M_DiscountsBenefits_NewCostDiscountField));

        //  Try Catch para for each que envia los nombres al comboBox
        try {
            List<Arrangement> Agreements = ManagerDao.getAgreement();
            for (Arrangement iArrangement : Agreements) {
                this.M_DiscountsBenefits_comboBoxAgreement.addItem(iArrangement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //  Consiguración de ComboBox para garantizar selección de algo
        this.M_DiscountsBenefits_comboBoxAgreement.setSelectedIndex(-1);

        // Botón para retornar al menu anterior utilizando AccesPanel
        this.M_DiscountsBenefits_ReturnBttn.addActionListener(e -> {
            this.M_DiscountsBenefits_comboBoxAgreement.setSelectedIndex(-1);
            this.M_DiscountsBenefits_CostDiscountField.setText("");
            this.M_DiscountsBenefits_NewCostDiscountField.setText("");
            AccessPanel.changeContent("M_Menu");
        });

        //  Configuración ComboBox para listar los convenios
        this.M_DiscountsBenefits_comboBoxAgreement.addActionListener(e -> {
            Arrangement selectedArrangement = (Arrangement) this.M_DiscountsBenefits_comboBoxAgreement.getSelectedItem();
            if (selectedArrangement != null) {
                this.M_DiscountsBenefits_CostDiscountField.setText(String.valueOf(selectedArrangement.getPercentage()));
                this.M_DiscountsBenefits_NewCostDiscountField.setText(String.valueOf(selectedArrangement.getPercentage()));
            }
        });

        // Pendiente Funcionalidad de Boton Guardar 'Update'
        this.M_DiscountsBenefits_SaveBttn.addActionListener(e -> {
            Arrangement selectedArrangement = (Arrangement) this.M_DiscountsBenefits_comboBoxAgreement.getSelectedItem();
            if (selectedArrangement != null) {
                JOptionPane.showConfirmDialog(this.M_DiscountsBenefitsPanel, "¿Seguro Quieres Actualizar?");
            }else {
                JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel, "Selecciona lo que deseas Actualizar");
            }
        });

    }

    @Override
    public JPanel getPanel() {
        return M_DiscountsBenefitsPanel;
    }
}
