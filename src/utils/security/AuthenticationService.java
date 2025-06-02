package utils.security;

import utils.db.ConnectionSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {
   // private final Connection conn = ConnectionSource.instance().createConnection();

    public AuthenticationService() throws SQLException {
    }

    public String authenticate(String username, String password) throws SQLException {
        String query = "SELECT password_hash, rol FROM usuarios WHERE username = ?";
        try(Connection conn = ConnectionSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        String Hash = rs.getString("password_hash");
                        String rol = rs.getString("rol");
                        String providedHash = HashGen.gen(password);
                        if (Hash.equals(providedHash)) {
                            return rol;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
            }
        }
    }

}
