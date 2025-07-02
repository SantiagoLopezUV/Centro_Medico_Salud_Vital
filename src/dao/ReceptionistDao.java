package dao;

import model.*;
import utils.db.ConnectionSource;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ReceptionistDao {


    public record infoDebts(String status, double value){}


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

    private static final String OUTSTANDING_DEBTS_EXIST_FOR_PATIENT_ID = "select * from cita where pacienteid = ? AND estado = 'Pendiente por pago';";

    private static final String GET_STATUS_FOR_PATIENT_ID = "SELECT pacienteid, estado, costoconsreg FROM cita WHERE pacienteid = ?;";

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

    public static infoDebts consultsDebts(long patientId) throws SQLException {
        try(Connection con = ConnectionSource.getConnection();) {
            try (PreparedStatement ps = con.prepareStatement(GET_STATUS_FOR_PATIENT_ID)){
                ps.setLong(1, patientId);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()){
                        String Status = rs.getString("estado");
                        double costConsult= rs.getDouble("costoconsreg");
                        infoDebts debtInformation = new infoDebts(Status, costConsult);
                        return debtInformation;
                    }
                }
            }
        }
        return null;
    }

}
