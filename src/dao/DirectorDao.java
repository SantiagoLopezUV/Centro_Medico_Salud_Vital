package dao;

import utils.db.ConnectionSource;

import java.sql.*;

public class DirectorDao {

    private static final String GET_MEDIC_SPECIALITIES = "SELECT * FROM especialidad";
    private static final String GET_ADDITIONAL_SERVICES = "SELECT * FROM servicio_adicional";
    private static final String GET_PATIENT_FULLNAME = "SELECT p.nombres, p.apellidos FROM paciente pm" +
            " INNER JOIN persona p ON p.docidentidad = pm.docidentidad" +
            " WHERE  p.docidentidad = ?";
    private static final String GET_ARRGMTS_PER_PATIENT = "SELECT * FROM citas_info WHERE dni = ?";
    private static final String GET_REG_ADISERV_PER_INVOICE = "SELECT * FROM reg_adicional_por_factura" +
            " WHERE extract(YEAR FROM fechafactura) = ?" +
            " AND extract(MONTH FROM fechafactura) = ?" ;
    private Object[][] buildBlankTable(ResultSet rs, int minusColumns) throws SQLException {
        int columns = rs.getMetaData().getColumnCount() - minusColumns;
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        return new Object[rows][columns];
    }

    public Object[][] getMedicSpecilities() throws SQLException {

        try(Connection conn = ConnectionSource.getConnection()){
            try(Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)){
                try(ResultSet rs = statement.executeQuery(GET_MEDIC_SPECIALITIES)) {
                    Object[][] medicSpecilities = buildBlankTable(rs, 0);
                    while (rs.next()) {
                        medicSpecilities[rs.getRow() - 1] = new Object[]{rs.getInt("codespecialidad"),
                                rs.getString("titulo")};
                    }
                    return medicSpecilities;
                }
            }
        }

    }

    public Object[][] getAdditionalServs() throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                try(ResultSet rs = statement.executeQuery(GET_ADDITIONAL_SERVICES)) {
                    Object[][] addServices = buildBlankTable(rs, 0);
                    while (rs.next()) {
                        addServices[rs.getRow() - 1] = new Object[]{rs.getInt("codservadi"),
                                rs.getString("nomservadi"),
                                rs.getString("valorservadiactual")};
                    }
                    return addServices;
                }
            }
        }
    }


    public Object[][] getArrgmtsPerPatient(long pId) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_ARRGMTS_PER_PATIENT,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                statement.setLong(1, pId);
                try(ResultSet rs = statement.executeQuery()) {
                    Object[][] PatientArrgmts = buildBlankTable(rs, 1);
                    while (rs.next()) {
                        PatientArrgmts[rs.getRow() - 1] = new Object[]{
                                rs.getDate(2),
                                rs.getString(3),
                                rs.getString(4) + " " + rs.getString(5),
                                rs.getString(6),
                                rs.getDouble(7),
                                rs.getDouble(8)};
                    }

                    return PatientArrgmts;
                }
            }
        }
    }

    public Object[][] getMonthlyAdditionalServsIncome(String year, int month) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_REG_ADISERV_PER_INVOICE,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                statement.setInt(1, Integer.parseInt(year));
                statement.setInt(2, month+1);
                try(ResultSet rs = statement.executeQuery()) {
                    Object[][] AddServInfo = buildBlankTable(rs, 1);
                    while (rs.next()) {
                        AddServInfo[rs.getRow() - 1] = new Object[]{
                                rs.getDate(1),
                                rs.getString(2),
                                rs.getLong(3),
                                rs.getDouble(4)};
                    }
                    return AddServInfo;
                }
            }
        }
    }


    public String getPatientFullName(long id) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_PATIENT_FULLNAME)) {
                statement.setLong(1, id);
                try(ResultSet rs = statement.executeQuery()) {
                    String fullName = null;
                    if (rs.next())
                        fullName = rs.getString(1) + " " + rs.getString(2);
                    return fullName;
                }
            }
        }
    }
}
