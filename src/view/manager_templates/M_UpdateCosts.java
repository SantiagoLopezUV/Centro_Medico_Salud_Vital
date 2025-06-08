package view.manager_templates;

import dao.ManagerDao;
import model.ConsultationType;
import utils.AccessPanel;
import utils.KeyListenerParaDouble;

import javax.swing.*;
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
            List<ConsultationType> TypeConsultations = ManagerDao.getTypeConsultations();
            for (ConsultationType TypeConsultation : TypeConsultations) {
                this.M_UpdateCosts_comboBoxService.addItem(TypeConsultation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                JOptionPane.showConfirmDialog(this.M_UpdateCostsPanel, "¿Seguro Quieres Actualizar?");
            }else {
                JOptionPane.showMessageDialog(this.M_UpdateCostsPanel, "Selecciona lo que deseas Actualizar");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return M_UpdateCostsPanel;
    }
}
