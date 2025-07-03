package view.receptionist_templates;

import dao.ReceptionistDao;
import model.*;
import utils.AccessPanel;
import utils.InitComboBoxes;
import utils.KeyListenerParaInt;
import utils.PlaceHoldersAction;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.stream.Collectors;

public class R_ScheduleAppointment implements AccessPanel {
    private JPanel R_ScheduleAppointmentPanel;
    private JLabel R_ScheduleAppointment_SubTitleRecep;
    private JLabel R_ScheduleAppointmentRecepcionistTitle;
    private JComboBox R_ScheduleAppointment_comboBoxDoctors;
    private JComboBox R_ScheduleAppointment_comboBoxDay;
    private JTextField R_ScheduleAppointment_TextfieldAgreement;
    private JTextField R_ScheduleAppointment_IdPatientField;
    private JButton R_ScheduleAppointment_ReturnBttn;
    private JLabel R_ScheduleAppointment_lblDoctors;
    private JLabel R_ScheduleAppointment_lblPatient;
    private JLabel R_ScheduleAppointment_lblAgreement;
    private JButton R_ScheduleAppointment_SheduleBttn;
    private JLabel R_ScheduleAppointment_lblDay;
    private JLabel R_ScheduleAppointment_lblSpecialty;
    private JComboBox R_ScheduleAppointment_comboBoxSpecialty;
    private JLabel R_ScheduleAppointment_lblHour;
    private JComboBox R_ScheduleAppointment_comboBoxHour;
    private JLabel R_ScheduleAppointment_lblConsultationType;
    private JComboBox R_ScheduleAppointment_comboBoxConsultationTypes;
    private JLabel R_ScheduleAppointment_lblMonthYear;
    private JComboBox R_ScheduleAppointment_comboBoxMonthYear;

    private static final String PATIENT_ID_TEXTFIELD_PLACEHOLDER_MSG = "Ingrese # de ID y presione enter";
    private Long idVerified;
    private final ReceptionistDao receptionistDao = new ReceptionistDao();
    private  ArrayList<MedicalSpeciality> medicalSpecialities;
    private MedicalSpeciality general_medicalSpeciality;
    private MedicalSpeciality odontology_medicalSpeciality;
    private final InitComboBoxes<MedicalSpeciality> iComboForSpecialities = new InitComboBoxes<>();
    private final InitComboBoxes<MedicBasicInfo> medicsBySpeciality = new InitComboBoxes<>();
    private Date appointmentDate;
    private Arrangement arrangement;


    public R_ScheduleAppointment() {

        this.R_ScheduleAppointment_IdPatientField.addFocusListener(new PlaceHoldersAction(
                this.R_ScheduleAppointment_IdPatientField, PATIENT_ID_TEXTFIELD_PLACEHOLDER_MSG){
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (idVerified != null && Long.parseLong(super.inField.getText()) != idVerified ){
                        throw new NumberFormatException("El id del id del paciente no esta verificado!");
                    }
                }catch (NumberFormatException exception){
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Debes verificar el ID del paciente primero,\n" +
                                    "presiona enter cuando termines de ingresar al paciente.",
                            "Cuidado!",
                            JOptionPane.WARNING_MESSAGE);
                    destroyData();
                }
                super.focusLost(e);
            }
        });

        this.R_ScheduleAppointment_IdPatientField.addKeyListener(
                new KeyListenerParaInt(){
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            idVerified = Long.valueOf(R_ScheduleAppointment_IdPatientField.getText());
                            checkPatientId();
                        }
                    }
                });

        this.R_ScheduleAppointment_ReturnBttn.addActionListener(e ->{
            AccessPanel.changeContent("R_Menu_Appointment");
            destroyData();
        });

        this.R_ScheduleAppointment_comboBoxConsultationTypes.addItemListener(e -> {
            if (R_ScheduleAppointment_comboBoxConsultationTypes.isEnabled()
                    && R_ScheduleAppointment_comboBoxConsultationTypes.getItemCount() > 0){
                String consultationType = R_ScheduleAppointment_comboBoxConsultationTypes.getSelectedItem().toString().toLowerCase();
                //necesario para que se actualice el combobox de medicos
                this.R_ScheduleAppointment_comboBoxSpecialty.setEnabled(true);
                //necesario para rehabilitar el cambio en mes-año y día
                this.R_ScheduleAppointment_comboBoxMonthYear.setEnabled(true);
                this.R_ScheduleAppointment_comboBoxDay.setEnabled(true);

                if(this.R_ScheduleAppointment_comboBoxSpecialty.getItemCount() > 0)
                    this.R_ScheduleAppointment_comboBoxSpecialty.removeAllItems();

                if(consultationType.contains("general")) {

                    this.R_ScheduleAppointment_comboBoxSpecialty.addItem(this.general_medicalSpeciality);
                    this.R_ScheduleAppointment_comboBoxSpecialty.setEnabled(false);

                }else if (consultationType.contains("odontolog")){

                    this.R_ScheduleAppointment_comboBoxSpecialty.addItem(this.odontology_medicalSpeciality);
                    this.R_ScheduleAppointment_comboBoxSpecialty.setEnabled(false);
                }else{
                    if(consultationType.contains("urgencia")) {
                        this.R_ScheduleAppointment_comboBoxSpecialty.addItem(this.odontology_medicalSpeciality);
                        this.R_ScheduleAppointment_comboBoxSpecialty.addItem(this.general_medicalSpeciality);
                    }
                    iComboForSpecialities.InitComboBoxesWithArrayList(R_ScheduleAppointment_comboBoxSpecialty,
                            medicalSpecialities);
                }
            }
        });

        this.R_ScheduleAppointment_comboBoxSpecialty.addItemListener(e -> startMedicComboBox());
        this.R_ScheduleAppointment_comboBoxMonthYear.addItemListener(e -> startComboBoxForAvailableDays());
        this.R_ScheduleAppointment_comboBoxDoctors.addItemListener(e -> startComboBoxForAvailableHours());
        this.R_ScheduleAppointment_comboBoxDay.addItemListener(e -> startComboBoxForAvailableHours() );

        this.R_ScheduleAppointment_SheduleBttn.addActionListener(e -> checkEnteredInfoForSchedule());

    }

    private void checkEnteredInfoForSchedule() {

        try{
            MedicBasicInfo medic = (MedicBasicInfo) this.R_ScheduleAppointment_comboBoxDoctors.getSelectedItem();
            Time time = (Time) this.R_ScheduleAppointment_comboBoxHour.getSelectedItem();
            ConsultationType consultationType= (ConsultationType) this.R_ScheduleAppointment_comboBoxConsultationTypes.getSelectedItem();

            Appointment appointment = new Appointment(
                    0,
                    AppointmentStatus.SCHEDULED,
                    this.idVerified,
                    medic.Id(),
                    appointmentDate,
                    time,
                    consultationType.getIdConsultationType(),
                    consultationType.getConsultationPrice(),
                    arrangement.getArrangementCode(),
                    arrangement.getPercentage()
            );

            if(receptionistDao.insertNewAppointment(appointment))
                JOptionPane.showMessageDialog(null,
                        "La cita fue registrada en la base de datos",
                        "Exito al registrar cita",
                        JOptionPane.INFORMATION_MESSAGE);;
            this.R_ScheduleAppointment_ReturnBttn.doClick();
        }catch (SQLException es){
            launchErrorFetchingDBPopUp(es);
        }

    }

    private void startComboBoxForAvailableHours(){
        if (R_ScheduleAppointment_comboBoxMonthYear.isEnabled()
                && R_ScheduleAppointment_comboBoxMonthYear.getItemCount() > 0
                && R_ScheduleAppointment_comboBoxDoctors.isEnabled()
                && R_ScheduleAppointment_comboBoxDoctors.getItemCount() > 0
                && R_ScheduleAppointment_comboBoxDay.isEnabled()
                && R_ScheduleAppointment_comboBoxDay.getItemCount() > 0
                && R_ScheduleAppointment_comboBoxDoctors.isEnabled()
        ){
            if(R_ScheduleAppointment_comboBoxHour.getItemCount()>0)
                R_ScheduleAppointment_comboBoxHour.removeAllItems();

            String selectedMontYear = R_ScheduleAppointment_comboBoxMonthYear.getSelectedItem().toString();
            int year = Integer.parseInt(selectedMontYear.substring(3,7));
            int month = Integer.parseInt(selectedMontYear.substring(0,2));
            int day = Integer.parseInt(R_ScheduleAppointment_comboBoxDay.getSelectedItem().toString());

            MedicBasicInfo medicSelected = (MedicBasicInfo) R_ScheduleAppointment_comboBoxDoctors.getSelectedItem();
            appointmentDate = Date.valueOf(LocalDate.of(year, month, day));

            try {
                Time[] availableHours = receptionistDao.getAvailableHoursPerMedicAndDate(
                        medicSelected.Id(),
                        appointmentDate
                );
                InitComboBoxes<Time> comboBoxHours = new InitComboBoxes<>();
                comboBoxHours.InitComboBoxesWithArrayList(R_ScheduleAppointment_comboBoxHour,
                        Arrays.stream(availableHours).collect(Collectors.toList()));

            } catch (SQLException e) {
                launchErrorFetchingDBPopUp(e);
            }


        }
    }

    private void startMedicComboBox() {
        if (R_ScheduleAppointment_comboBoxSpecialty.isEnabled()
                && R_ScheduleAppointment_comboBoxSpecialty.getItemCount() > 0){

            MedicalSpeciality specialty= (MedicalSpeciality) R_ScheduleAppointment_comboBoxSpecialty.getSelectedItem();

            if(R_ScheduleAppointment_comboBoxDoctors.getItemCount() > 0){
                R_ScheduleAppointment_comboBoxDoctors.removeAllItems();
            }

            try {
                medicsBySpeciality.InitComboBoxesWithArrayList(R_ScheduleAppointment_comboBoxDoctors,
                        receptionistDao.getMedicsBySpeciality(specialty.getSpecialtyCode()));

            } catch (SQLException ex) {
                launchErrorFetchingDBPopUp(ex);
            }
        }
    }

    private void checkPatientId(){
        try {
            if(idVerified == null) return;
            arrangement = receptionistDao.getArrangementForPatient(idVerified);
            if(arrangement == null){
                int confirm = JOptionPane.showConfirmDialog(this.R_ScheduleAppointmentPanel,
                        "No se encuentra al paciente en la base de datos\n¿Deseas registrarlo?");
                if (confirm == JOptionPane.YES_OPTION) {
                    AccessPanel.changeContent("SignUp");
                }
                destroyData();
            }else {

                if (receptionistDao.verifyForOutstandingDebts(idVerified)){
                   throw new NoSuchFieldException("Tiene deuda por pagar");
                }

                this.R_ScheduleAppointment_TextfieldAgreement.setText(
                        ((arrangement.isValid()) ? arrangement.toString() : "No válido")
                );


                try {
                    medicalSpecialities = receptionistDao.getSpecialities();

                    general_medicalSpeciality = medicalSpecialities.stream()
                            .filter(p -> p.getTitle().contains("General")).findFirst().orElse(null);
                    odontology_medicalSpeciality = medicalSpecialities.stream()
                            .filter(p -> p.getTitle().contains("Odontolog")).findFirst().orElse(null);
                    medicalSpecialities.remove(general_medicalSpeciality);
                    medicalSpecialities.remove(odontology_medicalSpeciality);
                } catch (SQLException ex) {
                    launchErrorFetchingDBPopUp(ex);
                }

                this.R_ScheduleAppointment_comboBoxConsultationTypes.setEnabled(true);
                InitComboBoxes<ConsultationType> iCombo = new InitComboBoxes<>();
                iCombo.InitComboBoxesWithArrayList(this.R_ScheduleAppointment_comboBoxConsultationTypes,
                        receptionistDao.getConsultationTypes());

                R_ScheduleAppointment_comboBoxDoctors.setEnabled(true);
                R_ScheduleAppointment_comboBoxMonthYear.setEnabled(true);
                startComboBoxForAvailableMonthYear();
                R_ScheduleAppointment_comboBoxDay.setEnabled(true);
                startComboBoxForAvailableDays();
                R_ScheduleAppointment_comboBoxHour.setEnabled(true);
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
        } catch (NoSuchFieldException e) {
            JOptionPane.showMessageDialog(null,
                    "No es posible generar la cita, el paciente tiente deudas pendientes",
                    "Tiene deudas pentiente",
                    JOptionPane.ERROR_MESSAGE);
            this.R_ScheduleAppointment_ReturnBttn.doClick();
        }

    }

    private void startComboBoxForAvailableMonthYear() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-yyyy");
        LocalDateTime localDate = LocalDateTime.now();
        String[] monthsAvailable = new String[]{
                localDate.format(dateFormat),
                localDate.plusMonths(1).format(dateFormat),
                localDate.plusMonths(2).format(dateFormat),
                localDate.plusMonths(3).format(dateFormat),
                localDate.plusMonths(4).format(dateFormat),
                localDate.plusMonths(5).format(dateFormat),
        };

        InitComboBoxes.InitComboBoxes(R_ScheduleAppointment_comboBoxMonthYear,monthsAvailable);
    }

    private void startComboBoxForAvailableDays() {
        if(this.R_ScheduleAppointment_comboBoxDay.isEnabled()){

            if(R_ScheduleAppointment_comboBoxDay.getItemCount() > 0){
                R_ScheduleAppointment_comboBoxDay.removeAllItems();
            }
            String[] availableDays;
            String selectedMontYear = R_ScheduleAppointment_comboBoxMonthYear.getSelectedItem().toString();
            int year = Integer.parseInt(selectedMontYear.substring(3,7));
            int month = Integer.parseInt(selectedMontYear.substring(0,2));

            if(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-yyyy"))
                    .equals(selectedMontYear)){
                availableDays = checkForAvailableDays(LocalDateTime.now().getDayOfMonth(),
                        YearMonth.now().atEndOfMonth().getDayOfMonth(),
                        month,
                        year);

            }else{

                availableDays = checkForAvailableDays(1,
                        YearMonth.of(year, month).atEndOfMonth().getDayOfMonth(),
                        month,
                        year);
            }

            InitComboBoxes.InitComboBoxes(R_ScheduleAppointment_comboBoxDay, availableDays);
        }
    }

    private String[] checkForAvailableDays(int initialDay, int lastDay, int month, int year){
        ArrayList<String> daysAvailable = new ArrayList<>();
        for (int i = initialDay; i <= lastDay; i++){

            LocalDate ld = LocalDate.of(year, month, i);
            if(ld.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                    || ld.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                    || COLOMBIAN_HOLYDAYS.contains(ld)){
                continue;
            }
            daysAvailable.add(String.valueOf(i));
        }
        return daysAvailable.toArray(new String[0]);
    }

    private void destroyData(){
        this.R_ScheduleAppointment_IdPatientField.setText(PATIENT_ID_TEXTFIELD_PLACEHOLDER_MSG);
        this.R_ScheduleAppointment_comboBoxHour.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxSpecialty.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxDoctors.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxMonthYear.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxDay.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxConsultationTypes.setEnabled(false);
        this.R_ScheduleAppointment_comboBoxDay.removeAllItems();
        this.R_ScheduleAppointment_comboBoxSpecialty.removeAllItems();
        this.R_ScheduleAppointment_comboBoxDoctors.removeAllItems();
        this.R_ScheduleAppointment_comboBoxMonthYear.removeAllItems();
        this.R_ScheduleAppointment_comboBoxHour.removeAllItems();
        this.R_ScheduleAppointment_comboBoxConsultationTypes.removeAllItems();
        this.R_ScheduleAppointment_TextfieldAgreement.setText("");

        this.idVerified = null;
    }

    private void launchErrorFetchingDBPopUp(SQLException e){
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
        return R_ScheduleAppointmentPanel;
    }
}
