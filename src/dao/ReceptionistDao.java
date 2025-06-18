package dao;

import model.Arrangement;
import model.ConsultationType;
import utils.db.ConnectionSource;

import java.sql.*;
import java.util.ArrayList;

public class ReceptionistDao {

    private static final String GET_CONSULT_TYPES = "SELECT * FROM tipo_consulta ORDER BY nomtipocons;";
    private static final String GET_ARRANGEMENT_BY_PATIENT_ID = "SELECT c.* FROM convenio c, paciente p" +
            " WHERE p.docidentidad = ?" +
            " AND p.codconvenio = c.codconvenio;";


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
}
