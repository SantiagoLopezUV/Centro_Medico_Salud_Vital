package utils.security;

import utils.db.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {

    public AuthenticationService() throws SQLException {
    }

    public String authenticate(String username, String password) throws SQLException {
        String query = "SELECT check_creds(?,?)";
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(query)) {

                if(HashGen.checkForInvalidChars(username) || HashGen.checkForInvalidChars(password))
                    return null;
                statement.setString(1, username);
                statement.setString(2, HashGen.gen(password));
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    } else {
                        return null;
                    }
                }
            }
        }
    }

}
