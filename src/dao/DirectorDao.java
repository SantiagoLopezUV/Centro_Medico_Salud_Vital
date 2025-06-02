package dao;

import utils.db.ConnectionSource;

import java.sql.*;

public class DirectorDao {

    private static final String GET_MEDIC_SPECIALITIES = "SELECT * FROM especialidad";
    private static final String GET_ADDITIONAL_SERVICES = "SELECT * FROM servicio_adicional";




    public Object[][] getMedicSpecilities() throws SQLException {

        try(Connection conn = ConnectionSource.getConnection();){
            try(Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)){
                try(ResultSet rs = statement.executeQuery(GET_MEDIC_SPECIALITIES)) {
                    int columns = rs.getMetaData().getColumnCount();
                    rs.last();
                    int rows = rs.getRow();
                    rs.beforeFirst();
                    Object[][] medicSpecilities = new Object[rows][columns];
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
        try(Connection conn = ConnectionSource.getConnection();) {
            try (Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                try(ResultSet rs = statement.executeQuery(GET_ADDITIONAL_SERVICES)) {
                    int columns = rs.getMetaData().getColumnCount();
                    rs.last();
                    int rows = rs.getRow();
                    rs.beforeFirst();
                    Object[][] addServices = new Object[rows][columns];
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
}
