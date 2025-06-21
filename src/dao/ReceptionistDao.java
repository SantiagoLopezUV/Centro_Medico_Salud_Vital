package dao;

import model.Arrangement;
import model.ConsultationType;
import model.Patient;
import utils.db.ConnectionSource;

import java.sql.*;
import java.util.ArrayList;

public class ReceptionistDao {

    private static final String GET_CONSULT_TYPES = "SELECT * FROM tipo_consulta ORDER BY nomtipocons;";
    private static final String GET_ARRANGEMENT_BY_PATIENT_ID = "SELECT c.* FROM convenio c, paciente p" +
            " WHERE p.docidentidad = ?" +
            " AND p.codconvenio = c.codconvenio;";
    private static final String GET_ALL_ARRANGEMENTS = "SELECT * FROM convenio ORDER BY nomempresa ;";
    private static final String INSERT_NEW_PATIENT = "INSERT INTO persona(" +
            "docidentidad, nombres, apellidos, sexo, telefono, email, dirresidencia)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";


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
}
