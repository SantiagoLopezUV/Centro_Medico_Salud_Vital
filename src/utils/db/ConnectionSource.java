package utils.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSource {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASSWORD");

    private static HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(10000);
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closeConnPool() {
        if (ds != null) {
            ds.close();
        }
    }

}
