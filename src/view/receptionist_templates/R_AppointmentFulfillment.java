package view.receptionist_templates;

import dao.ReceptionistDao;
import model.AdditionalService;
import model.Appointment;
import model.Invoice;
import utils.AccessPanel;
import utils.InitComboBoxes;
import utils.KeyListenerParaInt;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.sql.SQLException;

public class R_AppointmentFulfillment implements AccessPanel {
    private JPanel R_AppointmentFulfillmentPanel;
    private JLabel R_AppointmentFulfillment_SubTitleRecep;
    private JLabel R_AppointmentFulfillmentRecepcionistTitle;
    private JButton R_AppointmentFulfillment_ReturnBttn;
    private JButton R_AppointmentFulfillment_RegisterFulfillmentBttn;
    private JTextField R_AppointmentFulfillment_IdPatientField;
    private JLabel R_AppointmentFulfillment_lblPatient;
    private JLabel R_AppoinmentFulfillment_IdIcon;
    private JLabel R_AppointmentFulfillment_appointmentStatus;
    private JTextField R_AppointmentFulfillment_StatusAppointmentField;
    private JLabel R_Fulfillment_StatusAppointment_ChatIcon;
    private JLabel R_Fulfillment_StatusAppointment_lblAddNewService;
    private JButton R_Fulfillment_StatusAppointment_AddAdditionalServ_Bttn;
    private JComboBox R_AddService_comboBoxServices;
    private JLabel R_AddService_lblServices;
    private JTextField R_AppointmentFulfillment_Total_Invoice_TextField;
    private JLabel R_AppointmentFulfillment_Total_Invoiec_Lbl;

    private static final String PATIENT_ID_TEXTFIELD_PLACEHOLDER_MSG = "Ingrese # de Identificación";
    private Invoice invoice;
    ReceptionistDao receptionistDao = new ReceptionistDao();
    public R_AppointmentFulfillment() {

        this.R_AppointmentFulfillment_IdPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_AppointmentFulfillment_IdPatientField, PATIENT_ID_TEXTFIELD_PLACEHOLDER_MSG));

        this.R_AppointmentFulfillment_IdPatientField.addKeyListener(new KeyListenerParaInt());


        this.R_AppointmentFulfillment_ReturnBttn.addActionListener(e -> {
            destroyData();
            AccessPanel.changeContent("R_Menu_Appointment");
        });

        this.R_AppointmentFulfillment_RegisterFulfillmentBttn.addActionListener(e -> {
            checkPatientId();
        });

        this.R_Fulfillment_StatusAppointment_AddAdditionalServ_Bttn.addActionListener(e -> {
            if(this.R_AddService_comboBoxServices.isEnabled()
                    && this.R_Fulfillment_StatusAppointment_AddAdditionalServ_Bttn.isEnabled()){
                AdditionalService adServToReg = (AdditionalService) this.R_AddService_comboBoxServices.getSelectedItem();

                int option = JOptionPane.showConfirmDialog(this.R_AddService_comboBoxServices,
                        "Estas seguro que deseas agregar " + adServToReg.getNameAdditionalService() +
                        " a tu factura?");
                if(option == JOptionPane.YES_OPTION){
                    try {
                        if(receptionistDao.verifyIfAddServAsAlreadyInInvoice(adServToReg.getAdditionalServiceId(), invoice.getInvoiceNumber()))
                            throw new InterruptedException("Ya existe el servicio en la factura");
                        if(receptionistDao.insertNewRegInvoice_AddServ(adServToReg.getAdditionalServiceId(),
                                invoice.getInvoiceNumber(),
                                adServToReg.getCostAdditionalService())){
                            JOptionPane.showMessageDialog(null, "Servicio adicional agregado exitosamente",
                                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                            invoice = receptionistDao.getInvoiceById(invoice.getInvoiceNumber());
                            this.R_AppointmentFulfillment_Total_Invoice_TextField.setText(String.valueOf(invoice.getTotal()));
                        }else{
                            JOptionPane.showMessageDialog(null, "no fue posible agregar el servicio adicional",
                                    "Registro cancelado", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        launchErrorFetchingDBPopUp(ex);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),
                                "Registro cancelado", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

    }


    private void checkPatientId(){
        try {
            if(R_AppointmentFulfillment_IdPatientField.getText().equals(PATIENT_ID_TEXTFIELD_PLACEHOLDER_MSG)) return;
            long id= Long.parseLong(R_AppointmentFulfillment_IdPatientField.getText());

            if(!receptionistDao.patientExist(id)){
                JOptionPane.showMessageDialog(null,
                        "No se encuentra al paciente en la base de datos\nregistralo en agendar citas.");
                destroyData();
            }else {
                Appointment appointment = receptionistDao.getScheduledAppointment(id);

                if(appointment != null){
                    this.R_AppointmentFulfillment_IdPatientField.setEnabled(false);
                    this.R_AppointmentFulfillment_RegisterFulfillmentBttn.setEnabled(false);
                    this.R_AddService_comboBoxServices.setEnabled(true);
                    this.R_Fulfillment_StatusAppointment_AddAdditionalServ_Bttn.setEnabled(true);

                    InitComboBoxes<AdditionalService> additionalServiceInitComboBoxes = new InitComboBoxes<>();
                    additionalServiceInitComboBoxes.InitComboBoxesWithArrayList(this.R_AddService_comboBoxServices,
                            receptionistDao.getAdditionalServs());
                    this.R_AppointmentFulfillment_StatusAppointmentField.setText(appointment.getStatus().getValue());
                    invoice = receptionistDao.getInvoiceById(appointment.getInvoiceNumber());
                    this.R_AppointmentFulfillment_Total_Invoice_TextField.setText(String.valueOf(invoice.getTotal()));
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Puede que el paciente haya llegado tarde o mucho antes \n " +
                                    "de la hora asignada (lapso aceptable entre la hora citada y \n" +
                                    "una hora antes de la misma. o simplemente no tiene cita para este día.",
                            "Error al consultar la cita del paciente",
                            JOptionPane.ERROR_MESSAGE);

                }
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Verifica que el documento de identidad sea válido",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
            destroyData();
        } catch (SQLException e) {
            launchErrorFetchingDBPopUp(e);
        }

    }



    private void destroyData() {
        this.R_AppointmentFulfillment_IdPatientField.setText(PATIENT_ID_TEXTFIELD_PLACEHOLDER_MSG);
        this.R_AppointmentFulfillment_StatusAppointmentField.setText("");
        this.R_AppointmentFulfillment_Total_Invoice_TextField.setText("");
        this.R_AppointmentFulfillment_IdPatientField.setEnabled(true);
        this.R_AppointmentFulfillment_RegisterFulfillmentBttn.setEnabled(true);
        this.R_AddService_comboBoxServices.setEnabled(false);
        this.R_AddService_comboBoxServices.removeAllItems();
        this.R_Fulfillment_StatusAppointment_AddAdditionalServ_Bttn.setEnabled(false);
        this.invoice = null;
    }

    private void launchErrorFetchingDBPopUp(SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null,
                "Un error se ha presentado al tratar de consultar la base de datos," +
                        "contacta al administrador de la misma",
                "Error al consultar la base de datos",
                JOptionPane.ERROR_MESSAGE);
        destroyData();
    }

    @Override
    public JPanel getPanel() {
        return R_AppointmentFulfillmentPanel;
    }
}
