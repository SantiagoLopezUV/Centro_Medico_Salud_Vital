package dao;

import utils.MathUtils;
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
    private static final String GET_MONTHLY_ARRANGEMENT_COUNT = "SELECT COUNT(*) FROM cita" +
            " WHERE extract(YEAR FROM fechacita) = ?" +
            " AND extract(MONTH FROM fechacita) = ?";
    private static final String GET_ARRANGEMENT_COUNT_BY_GROUP = "SELECT estado, COUNT(*) FROM cita" +
            " WHERE extract(YEAR FROM fechacita) = ?" +
            " AND extract(MONTH FROM fechacita) = ?" +
            " group by estado";

    private static final String GET_MEDIC_OCCUPATION_RATE = "SELECT * FROM porcentaje_ocupacion_medicos(?, ?) ";
    private static final String GET_MONTHLY_TOTAL_ADDSERVS_SUM = "SELECT count(*) FROM registro_factura_servadicionales rfs" +
            " INNER JOIN factura f ON f.reffactura = rfs.reffactura" +
            " WHERE extract(YEAR FROM fechafactura) = ?" +
            "        AND extract(MONTH FROM fechafactura) = ?; ";
    private static final String GET_ADDSERVS_COUNT_PER_MONTH = "SELECT a.nomservadi, COUNT(f.*) FROM registro_factura_servadicionales rfs" +
            " INNER JOIN factura f ON f.reffactura = rfs.reffactura" +
            "   AND extract(YEAR FROM f.fechafactura) = ?" +
            "   AND extract(MONTH FROM f.fechafactura) = ?" +
            " RIGHT JOIN servicio_adicional a ON a.codservadi = rfs.codservadi" +
            " GROUP BY a.nomservadi" +
            " ORDER BY COUNT(f.*) DESC; ";


    private Object[][] buildBlankTable(ResultSet rs, int minusColumns) throws SQLException {
        int columns = rs.getMetaData().getColumnCount() - (minusColumns);
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
                    Object[][] servsIncome = buildBlankTable(rs, 1);
                    while (rs.next()) {
                        servsIncome[rs.getRow() - 1] = new Object[]{
                                rs.getDate(1),
                                rs.getString(2),
                                rs.getLong(3),
                                rs.getDouble(4)};
                    }
                    return servsIncome;
                }
            }
        }
    }

    public Object[][] getCancelledArrgRates(String year, int month, int totalMonthlyArrangementCount) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_ARRANGEMENT_COUNT_BY_GROUP,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                statement.setInt(1, Integer.parseInt(year));
                statement.setInt(2, month+1);
                try(ResultSet rs = statement.executeQuery()) {
                    Object[][] arrRates = buildBlankTable(rs, 1);
                    while (rs.next()) {
                        arrRates[rs.getRow() - 1] = new Object[]{
                                rs.getString(1),
                                MathUtils.roundDPercentageFixed(rs.getDouble(2),
                                    totalMonthlyArrangementCount,
                                    2)
                        };
                    }
                    return arrRates;
                }
            }
        }

    }

    public Object[][] getMedicOcupationRate(String year, int month) throws SQLException {

        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_MEDIC_OCCUPATION_RATE,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                statement.setInt(1, Integer.parseInt(year));
                statement.setInt(2, month+1);
                try(ResultSet rs = statement.executeQuery()) {
                    Object[][] occupationRates = buildBlankTable(rs, 0);
                    while (rs.next()) {
                        occupationRates[rs.getRow() - 1] = new Object[]{
                                rs.getLong(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getDouble(4)};
                    }
                    return occupationRates;
                }
            }
        }
    }

    public Object[][] getMonthlyAdditionalServsCounts(String year, int month, int totalServs) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_ADDSERVS_COUNT_PER_MONTH,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                statement.setInt(1, Integer.parseInt(year));
                statement.setInt(2, month+1);
                try(ResultSet rs = statement.executeQuery()) {
                    Object[][] additionalServsCounts = buildBlankTable(rs, -1);
                    while (rs.next()) {
                        additionalServsCounts[rs.getRow() - 1] = new Object[]{
                                rs.getString(1),
                                rs.getInt(2),
                                MathUtils.roundDPercentageFixed(rs.getInt(2), totalServs, 2)};
                    }
                    return additionalServsCounts;
                }
            }
        }
    }

    public int getSumOfAdditionalServsPerMonth(String year, int month) throws SQLException {
        return PrepareQueryWithMonthYearCondition(year, month, GET_MONTHLY_TOTAL_ADDSERVS_SUM);
    }

    public int getArrangementCount(String year, int month) throws SQLException {
        return PrepareQueryWithMonthYearCondition(year, month, GET_MONTHLY_ARRANGEMENT_COUNT);
    }

    private int PrepareQueryWithMonthYearCondition(String year, int month, String getMonthlyArrangementCount) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(getMonthlyArrangementCount)) {
                statement.setInt(1, Integer.parseInt(year));
                statement.setInt(2, month+1);
                try(ResultSet rs = statement.executeQuery()){
                        if (rs.next()) return rs.getInt(1);
                }
            }
        }
        return 0;
    }


    public String getPatientFullName(long id) throws SQLException {
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(GET_PATIENT_FULLNAME)) {
                statement.setLong(1, id);
                try(ResultSet rs = statement.executeQuery()) {
                    if (rs.next())
                        return rs.getString(1) + " " + rs.getString(2);
                }
            }
        }
        return "";
    }


}
