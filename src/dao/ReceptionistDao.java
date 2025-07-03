package dao;

import model.*;
import utils.db.ConnectionSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistDao {

    private static final String GET_CONSULT_TYPES = "SELECT * FROM tipo_consulta ORDER BY nomtipocons;";
    private static final String GET_ARRANGEMENT_BY_PATIENT_ID = "SELECT c.* FROM convenio c, paciente p" +
            " WHERE p.docidentidad = ?" +
            " AND p.codconvenio = c.codconvenio;";
    private static final String GET_ALL_ARRANGEMENTS = "SELECT * FROM convenio ORDER BY nomempresa ;";
    private static final String INSERT_NEW_PATIENT = "INSERT INTO persona(" +
            "docidentidad, nombres, apellidos, sexo, telefono, email, dirresidencia)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ALL_AESPECIALITIES = "SELECT * FROM especialidad ORDER BY titulo;";
    private static final String GET_MEDIC_BY_SPECIALITY = "SELECT m.docidentidad, p.nombres, p.apellidos " +
            " FROM medico m LEFT JOIN persona p ON p.docidentidad = m.docidentidad" +
            " WHERE m.codespecialidad = ? ORDER BY p.apellidos;";
    private static final String GET_AVAILABLE_HOURS_BY_MEDIC_AND_DATE = "SELECT  obtener_horarios_disponibles_citas(?, ?);";
    private static final String INSERT_APPOINTMENT = "INSERT INTO cita(" +
            "estado, pacienteid, fechacita, horacita, " +
            "codtipocons, costoconsreg, codconvregistrado, " +
            "convtasaaplicada, medicoid)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String GET_PATIENT_BY_ID = "SELECT * FROM paciente WHERE docidentidad = ?;";

    private static final String OUTSTANDING_DEBTS_EXIST_FOR_PATIENT_ID = "select * from cita where pacienteid = ? AND estado = 'Pendiente por pago';";
    private static final String GET_SCHEDULED_APPOINTMENT = "select * from cita" +
                                                                " WHERE pacienteid = ?" +
                                                                " AND estado = 'Agendada'" +
                                                                " AND fechacita = CURRENT_DATE" +
                                                                " AND CURRENT_TIME between horacita - INTERVAL '1 hour' AND horacita;";

    private static final String UPDATE_APPOINTMENT_STATUS_TO_IN_COURSE = "UPDATE cita SET estado = ? WHERE codcita = ?";

    private static final String GET_STATUS_OF_IN_COURSE_APPOINTMENT = "select estado from cita WHERE codcita = ?;";
    private static final String GET_INVOICE_NUMBER_OF_IN_COURSE_APPOINTMENT = "select reffactura from cita WHERE codcita = ?;";

    private static final String GET_ADDITIONAL_SERVICES = "SELECT * FROM servicio_adicional";
    private static final String INSERT_REG_INVOICE_ADDITIONAL_SERVICE = "INSERT INTO registro_factura_servadicionales(" +
            "codservadi, reffactura, valorservadifac)" +
            "VALUES (?, ?, ?);";
    private static final String GET_INVOICE_BY_ID = "SELECT * FROM factura WHERE reffactura = ?;";
    private static final String VERIFY_REG_ADDSERV_INVOICE = "SELECT * FROM registro_factura_servadicionales WHERE codservadi = ? AND reffactura = ?";

    public ArrayList<ConsultationType> getConsultationTypes() throws SQLException {
        ArrayList<ConsultationType> consultationTypes = new ArrayList<>();
        try(Connection conn = ConnectionSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                try(ResultSet rs = statement.executeQuery(GET_CONSULT_TYPES)) {
                    while (rs.next()) {
                        ConsultationType ct = new ConsultationType(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3)
                        );
                        consultationTypes.add(ct);
                    }
                    return consultationTypes;
                }
            }
        }
    }


    public Arrangement getArrangementForPatient(long id) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_ARRANGEMENT_BY_PATIENT_ID)) {
                statement.setLong(1, id);
                try(ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return new Arrangement(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getFloat(3),
                                rs.getBoolean(4)
                        );
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Arrangement> getArrangements() throws SQLException {
        ArrayList<Arrangement> arrangements = new ArrayList<>();
        try(Connection conn = ConnectionSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                try(ResultSet rs = statement.executeQuery(GET_ALL_ARRANGEMENTS)) {
                    while (rs.next()) {
                        Arrangement ct = new Arrangement(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getFloat(3),
                                rs.getBoolean(4)
                        );
                        arrangements.add(ct);
                    }
                    return arrangements;
                }
            }
        }
    }

    public boolean insertNewPatient(Patient newPatient) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement statement = conn.prepareStatement(
                    INSERT_NEW_PATIENT
                    + ((newPatient.getArrangement() != null) ? "UPDATE paciente SET codconvenio = " + newPatient.getArrangement().getArrangementCode()
                            + " WHERE docidentidad = " + newPatient.getId() + "; "
                            :"")
            )) {
                statement.setLong(1, newPatient.getId());
                statement.setString(2, newPatient.getFirstMiddleName());
                statement.setString(3, newPatient.getLastName());
                statement.setString(4, newPatient.getSex().getValue());
                statement.setString(5, newPatient.getTelephoneNumber());
                statement.setString(6, newPatient.getEmail());
                statement.setString(7, newPatient.getAddress());
                int rowAffected = statement.executeUpdate();
                if(rowAffected == 0){
                    throw new SQLException("No rows affected");
                }
                conn.commit();
                return true;
            }catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }


    public ArrayList<MedicalSpeciality> getSpecialities() throws SQLException {
        ArrayList<MedicalSpeciality> specialities = new ArrayList<>();
        try(Connection conn = ConnectionSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                try(ResultSet rs = statement.executeQuery(GET_ALL_AESPECIALITIES)) {
                    while (rs.next()) {
                        MedicalSpeciality especiality = new MedicalSpeciality(
                                rs.getInt(1),
                                rs.getString(2)
                        );
                        specialities.add(especiality);
                    }
                    return specialities;
                }
            }
        }
    }

    public ArrayList<MedicBasicInfo> getMedicsBySpeciality(int specialtyCode) throws SQLException {
        ArrayList<MedicBasicInfo> medics = new ArrayList<>();
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_MEDIC_BY_SPECIALITY)) {
                statement.setInt(1, specialtyCode);
                try(ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                         MedicBasicInfo medic = new MedicBasicInfo(
                                 rs.getLong(1),
                                 rs.getString(2),
                                 rs.getString(3)
                         );
                         medics.add(medic);
                    }
                    return medics;
                }
            }
        }
    }

    public Time[] getAvailableHoursPerMedicAndDate(long id, Date date) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_AVAILABLE_HOURS_BY_MEDIC_AND_DATE)) {
                statement.setLong(1, id);
                statement.setDate(2, date);
                try(ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        Array hoursArray = rs.getArray(1);
                        return (Time[]) hoursArray.getArray();
                    }
                    return null;
                }
            }
        }
    }

    public boolean insertNewAppointment(Appointment newAppointment) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement statement = conn.prepareStatement(INSERT_APPOINTMENT)) {
                statement.setString(1, newAppointment.getStatus().getValue());
                statement.setLong(2, newAppointment.getPatientId());
                statement.setDate(3, newAppointment.getAppointmentDate());
                statement.setTime(4, newAppointment.getAppointmentTime());
                statement.setInt(5, newAppointment.getConsultationId());
                statement.setDouble(6, newAppointment.getConsultationRegisteredPrice());
                statement.setInt(7, newAppointment.getArrangementCode());
                statement.setDouble(8, newAppointment.getArrangementDiscountApplied());
                statement.setLong(9, newAppointment.getMedicId());
                int rowAffected = statement.executeUpdate();
                conn.commit();
                if(rowAffected == 0){
                    throw new SQLException("No rows affected");
                }
                return true;
            }catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public boolean verifyForOutstandingDebts(long patientId) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement statement = conn.prepareStatement(
                    OUTSTANDING_DEBTS_EXIST_FOR_PATIENT_ID)
            ) {
                statement.setLong(1, patientId);
                conn.commit();
                try(ResultSet rs = statement.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    public boolean patientExist(long patientDoc) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_PATIENT_BY_ID)) {
                statement.setLong(1, patientDoc);
                try(ResultSet rs = statement.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    public Appointment getScheduledAppointment(long patientDoc) throws SQLException {
        Appointment appointment = null;
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_SCHEDULED_APPOINTMENT)) {
                statement.setLong(1, patientDoc);
                try(ResultSet rs = statement.executeQuery()) {
                    if (rs.next()){
                        long appointmentId = rs.getLong(1);
                        AppointmentStatus status = getScheduledAppointmentUpdateStatusSucceded(appointmentId);
                        long patientId = rs.getLong(3);
                        Date appointmentDate = rs.getDate(4);
                        Time appointmentTime = rs.getTime(5);
                        int consultationId = rs.getInt(6);
                        double consultationRegisteredPrice = rs.getDouble(7);
                        Long invoiceId = getInvoiceId(appointmentId);
                        int arrangementCode = rs.getInt(9);
                        double arrangementDiscountApplied = rs.getDouble(10);
                        long medicId = rs.getLong(11);
                        appointment = new Appointment(appointmentId,
                                status,
                                patientId,
                                medicId,
                                appointmentDate,
                                appointmentTime,
                                consultationId,
                                consultationRegisteredPrice,
                                arrangementCode,
                                arrangementDiscountApplied);
                        appointment.setInvoiceNumber(invoiceId);

                    }
                }
            }
        }
        return appointment;
    }

    private AppointmentStatus getScheduledAppointmentUpdateStatusSucceded(long appointmentId) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement statement = conn.prepareStatement(UPDATE_APPOINTMENT_STATUS_TO_IN_COURSE)) {
                statement.setString(1, AppointmentStatus.IN_PROGRESS.getValue());
                statement.setLong(2, appointmentId);
                int rowAffected = statement.executeUpdate();

                if(rowAffected == 0){
                    throw new SQLException("No rows affected");
                }
                conn.commit();
                return getAppointmentStatusByItsID(appointmentId);
            }catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        }
    }

    private AppointmentStatus getAppointmentStatusByItsID(long appointmentId) throws SQLException {
        try (Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_STATUS_OF_IN_COURSE_APPOINTMENT)) {
                statement.setLong(1, appointmentId);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) return AppointmentStatus.valueOf(rs.getString(1));
                }
            }
        }
        return null;
    }

    private Long getInvoiceId(long appointmentId) throws SQLException {
        try (Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_INVOICE_NUMBER_OF_IN_COURSE_APPOINTMENT)) {
                statement.setLong(1, appointmentId);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next())  return rs.getLong(1);
                }
            }
        }
        return null;
    }

    public List<AdditionalService> getAdditionalServs() throws SQLException {
        List<AdditionalService> additionalServices = null;
        try(Connection conn = ConnectionSource.getConnection()) {
            try (Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                try(ResultSet rs = statement.executeQuery(GET_ADDITIONAL_SERVICES)) {
                    while (rs.next()) {
                        additionalServices.add(new AdditionalService(rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3)));
                    }
                    return additionalServices;
                }
            }
        }
    }

    public boolean insertNewRegInvoice_AddServ (int codAddServ, long invoiceId, double addServCost) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement statement = conn.prepareStatement(
                    INSERT_REG_INVOICE_ADDITIONAL_SERVICE)
            ) {
                statement.setInt(1, codAddServ);
                statement.setLong(2, invoiceId);
                statement.setDouble(3, addServCost);

                int rowAffected = statement.executeUpdate();
                if(rowAffected == 0){
                    throw new SQLException("No rows affected");
                }
                conn.commit();
                return true;
            }catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public Invoice getInvoiceById(long invoiceNumber) throws SQLException {
        try (Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_INVOICE_BY_ID)) {
                statement.setLong(1, invoiceNumber);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) return new Invoice(rs.getLong(1),
                            rs.getDate(2),
                            rs.getTime(3),
                            rs.getDouble(4));
                }
            }
        }
        return null;
    }

    public boolean verifyIfAddServAsAlreadyInInvoice(int codAddServ, long invoiceId) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(VERIFY_REG_ADDSERV_INVOICE)) {
                statement.setInt(1, codAddServ);
                statement.setLong(2, invoiceId);
                try(ResultSet rs = statement.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

}
