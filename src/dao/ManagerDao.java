package dao;

import model.Arrangement;
import model.ConsultationType;
import utils.db.ConnectionSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDao {

    private static final String GET_TYPES_CONSULTATIONS = "SELECT * FROM Tipo_Consulta";
    private static final String GET_AGREEMENT = "SELECT * FROM Convenio";

    private static final String UPDATE_PRICE_CONSULTATION = "UPDATE Tipo_Consulta SET costoConsulta = ? WHERE codTipoCons = ?";
    private static final String UPDATE_PERCENT_AGREEMENT = "UPDATE Convenio SET porcentaje = ? WHERE codConvenio = ?";

    public static List<ConsultationType> getTypeConsultations() throws SQLException {
        List<ConsultationType> listTypeConsultations = new ArrayList<>();

        try(Connection con = ConnectionSource.getConnection();){
            try(PreparedStatement ps = con.prepareStatement(GET_TYPES_CONSULTATIONS)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        int idTypeConsultation = rs.getInt("codTipoCons");
                        String nameTypeConsultation = rs.getString("nomTipoCons");
                        double costTypeConsultation = rs.getDouble("costoConsulta");
                        listTypeConsultations.add(new ConsultationType(
                                idTypeConsultation, nameTypeConsultation, costTypeConsultation));
                    }
                }
            }
        }
        return listTypeConsultations;
    }


    public static List<Arrangement> getAgreement() throws SQLException {
        List<Arrangement> listAgreement = new ArrayList<>();

        try(Connection con = ConnectionSource.getConnection();){
            try(PreparedStatement ps = con.prepareStatement(GET_AGREEMENT)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        int ArrangementCode = rs.getInt("codConvenio");
                        String corpName = rs.getString("nomEmpresa");
                        float percentage = rs.getFloat("porcentaje");
                        listAgreement.add(new Arrangement(
                                ArrangementCode, corpName, percentage, true));
                    }
                }
            }
        }
        return listAgreement;
    }


    public static boolean updatePriceTypeConsultations(int idTypeCons, double newPrice) throws SQLException {
        try(Connection con = ConnectionSource.getConnection();){
            try(PreparedStatement ps = con.prepareStatement(UPDATE_PRICE_CONSULTATION)){
                ps.setDouble(1, newPrice);
                ps.setInt(2, idTypeCons);
                return true;
            }
        }
    }

    public static boolean updatePercentageArrangement(int idArrangement, float newPrice) throws SQLException {
        try(Connection con = ConnectionSource.getConnection();){
            try(PreparedStatement ps = con.prepareStatement(UPDATE_PERCENT_AGREEMENT)){
                ps.setDouble(1, newPrice);
                ps.setInt(2, idArrangement);
                return true;
            }
        }
    }


}
