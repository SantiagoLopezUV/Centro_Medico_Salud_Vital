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
    private JLabel M_DiscountBenefits_lblPercent;
    private JLabel M_DiscountsBenefits_lblPercent2;

    public M_DiscountsBenefits() {

        //  Configuración de label para no dejar editar
        this.M_DiscountsBenefits_CostDiscountField.setEditable(false);
        this.M_DiscountsBenefits_CostDiscountField.setFocusable(false);

        // Configuración de Text Field con Funcion KeyListener
        this.M_DiscountsBenefits_NewCostDiscountField.addKeyListener(
                new KeyListenerParaDouble(this.M_DiscountsBenefits_NewCostDiscountField));

        //  Try Catch para for each que envia los nombres capturados por el DAO al comboBox
        try {
            List<Arrangement> Agreements = ManagerDao.getAgreement();
            for (Arrangement iArrangement : Agreements) {
                this.M_DiscountsBenefits_comboBoxAgreement.addItem(iArrangement);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel, "Error al Cargar Información");

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
                try {
                    float percent = Float.parseFloat(M_DiscountsBenefits_CostDiscountField.getText());
                    float newPercent = Float.parseFloat(M_DiscountsBenefits_NewCostDiscountField.getText());
                    if (newPercent > 0.5 || newPercent < 0)
                        throw new ArithmeticException("descuento no válido");

                    if (Float.compare(percent, newPercent) == 0) {
                        JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel,
                                "Los Valores ya Estan Actualizados");
                    } else {

                        int confirm = JOptionPane.showConfirmDialog(this.M_DiscountsBenefitsPanel,
                                "¿Seguro Quieres Actualizar?");
                        if (confirm == JOptionPane.YES_OPTION) {
                            int idArrangement = selectedArrangement.getArrangementCode();
                            float newDiscount = Float.parseFloat(this.M_DiscountsBenefits_NewCostDiscountField.getText());
                            boolean flag = ManagerDao.updatePercentageArrangement(idArrangement, newDiscount);

                            if (flag) {
                                JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel,
                                        "¡Actualización Exitosa!");

                                this.M_DiscountsBenefits_comboBoxAgreement.removeAllItems();
                                List<Arrangement> newListArrangements = ManagerDao.getAgreement();
                                for (Arrangement iArrangement : newListArrangements) {
                                    this.M_DiscountsBenefits_comboBoxAgreement.addItem(iArrangement);
                                }

                                this.M_DiscountsBenefits_comboBoxAgreement.setSelectedIndex(-1);
                                this.M_DiscountsBenefits_CostDiscountField.setText("");
                                this.M_DiscountsBenefits_NewCostDiscountField.setText("");
                            } else {
                                JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel,
                                        "No se Actualizó. No Existe el Convenio ó El Valor ya Estaba Actualizado");
                            }
                        } else if (confirm == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel,
                                    "No se Pudo Actualizar");
                            this.M_DiscountsBenefits_NewCostDiscountField.setText(String.valueOf(percent));
                        } else {
                            JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel,
                                    "Acción Cancelada");
                            this.M_DiscountsBenefits_comboBoxAgreement.setSelectedIndex(-1);
                            this.M_DiscountsBenefits_CostDiscountField.setText("");
                            this.M_DiscountsBenefits_NewCostDiscountField.setText("");
                        }
                    }
                } catch (ArithmeticException ex){
                    JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel,
                            "El valor debe ser entre 0 y 0.5, contactar a soporte para aplicar un valor más alto");
                } catch (NumberFormatException | HeadlessException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                JOptionPane.showMessageDialog(this.M_DiscountsBenefitsPanel,
                        "Selecciona el Convenio que Deseas Actualizar");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return M_DiscountsBenefitsPanel;
    }
}
