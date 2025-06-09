package view.manager_templates;

import dao.ManagerDao;
import model.ConsultationType;
import utils.AccessPanel;
import utils.KeyListenerParaDouble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class M_UpdateCosts implements AccessPanel{
    private JPanel M_UpdateCostsPanel;
    private JButton M_UpdateCosts_SaveBttn;
    private JButton M_UpdateCosts_ReturnBttn;
    private JLabel M_UpdateCosts_ManagerTitle;
    private JFormattedTextField M_UpdateCosts_NewCostField;
    private JLabel M_UpdateCosts_lblNewCost;
    private JLabel M_UpdateCosts_lblCost;
    private JFormattedTextField M_UpdateCosts_CostField;
    private JLabel M_UpdateCosts_lblConsultations;
    private JComboBox M_UpdateCosts_comboBoxService;
    private JLabel M_UpdateCosts_SubTitleAdmUpdateCosts;
    private JLabel M_UpdateCosts_NotesIcon;

    public M_UpdateCosts() {

        //  Configuración de label para no dejar editar
        this.M_UpdateCosts_CostField.setEditable(false);
        this.M_UpdateCosts_CostField.setFocusable(false);

        // Configuración de Text Field con Funcion KeyListener
        this.M_UpdateCosts_NewCostField.addKeyListener(
                new KeyListenerParaDouble(this.M_UpdateCosts_NewCostField));

        //  Try Catch para for each que envia los nombres al comboBox
        try{
            List<ConsultationType> listTypeConsultations = ManagerDao.getTypeConsultations();
            for (ConsultationType TypeConsultation : listTypeConsultations) {
                this.M_UpdateCosts_comboBoxService.addItem(TypeConsultation);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this.M_UpdateCostsPanel, "Error al Cargar Información");
            //throw new RuntimeException(e);
        }

        //  Consiguración de ComboBox para garantizar selección de algo
        this.M_UpdateCosts_comboBoxService.setSelectedIndex(-1);

        // Botón para retornar al menu anterior utilizando AccesPanel
        this.M_UpdateCosts_ReturnBttn.addActionListener(e -> {
            this.M_UpdateCosts_comboBoxService.setSelectedIndex(-1);
            this.M_UpdateCosts_CostField.setText("");
            this.M_UpdateCosts_NewCostField.setText("");
            AccessPanel.changeContent("M_Menu");
        });

        //  Configuración ComboBox para listar Tipos de Consultas
        this.M_UpdateCosts_comboBoxService.addActionListener(e -> {
            ConsultationType selectedConsultationType = (ConsultationType) this.M_UpdateCosts_comboBoxService.getSelectedItem();
            if (selectedConsultationType != null) {
                this.M_UpdateCosts_CostField.setText(String.valueOf(selectedConsultationType.getConsultationPrice()));
                this.M_UpdateCosts_NewCostField.setText(String.valueOf(selectedConsultationType.getConsultationPrice()));
            }
        });

        // Pendiente Funcionalidad de Boton Guardar 'Update'
        this.M_UpdateCosts_SaveBttn.addActionListener(e -> {
            ConsultationType selectedConsultationType = (ConsultationType) this.M_UpdateCosts_comboBoxService.getSelectedItem();
            if (selectedConsultationType != null) {
                try{
                    double cost = Double.parseDouble(M_UpdateCosts_CostField.getText());
                    double newCost = Double.parseDouble(M_UpdateCosts_NewCostField.getText());

                    if (Double.compare(cost, newCost) == 0) {
                        JOptionPane.showMessageDialog(this.M_UpdateCostsPanel,
                                "Los Valores ya Estan Actualizados");
                    }

                    int confirm = JOptionPane.showConfirmDialog(this.M_UpdateCostsPanel,
                            "¿Seguro Quieres Actualizar?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        int idConsul = selectedConsultationType.getIdConsultationType();
                        double newPrice = Double.parseDouble((this.M_UpdateCosts_NewCostField.getText()));

                        boolean flag = ManagerDao.updatePriceTypeConsultations(idConsul, newPrice);

                        if (flag) {
                            JOptionPane.showMessageDialog(this.M_UpdateCostsPanel,
                                    "¡Actualización Exitosa!");

                            this.M_UpdateCosts_comboBoxService.removeAllItems();
                            List<ConsultationType> newListTypeConsultations = ManagerDao.getTypeConsultations();
                            for (ConsultationType TypeConsultation : newListTypeConsultations) {
                                this.M_UpdateCosts_comboBoxService.addItem(TypeConsultation);
                            }

                            this.M_UpdateCosts_comboBoxService.setSelectedIndex(-1);
                            this.M_UpdateCosts_CostField.setText("");
                            this.M_UpdateCosts_NewCostField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(this.M_UpdateCostsPanel,
                                    "No se Actualizó. No existe la Consulta ó Valor ya estaba Actualizado");
                        }
                    } else if (confirm == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(this.M_UpdateCostsPanel,
                                "No se pudo Actualizar");
                        this.M_UpdateCosts_NewCostField.setText(String.valueOf(cost));
                    } else {
                        JOptionPane.showMessageDialog(this.M_UpdateCostsPanel,
                                "Acción Cancelada");
                        this.M_UpdateCosts_comboBoxService.setSelectedIndex(-1);
                        this.M_UpdateCosts_CostField.setText("");
                        this.M_UpdateCosts_NewCostField.setText("");
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this.M_UpdateCostsPanel,
                        "Elige el tipo de consulta para actualizar");
            }
        });


    }

/*
                    */


    @Override
    public JPanel getPanel() {
        return M_UpdateCostsPanel;
    }
}
