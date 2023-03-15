package connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupaBaseDB {
	private final static String dbPassword = "Pi6MbaCjgNVSm4lg";
	private static Connection connection;

	protected static void initializeDatabase() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		String dbURL = "jdbc:postgresql://db.angvabhqbpkwlumpkhuw.supabase.co:5432/postgres?user=postgres&password="
				+ dbPassword;
		connection = DriverManager.getConnection(dbURL);
	}

	public static PreparedStatement getStatement(String query) {
		try {
			if (connection == null)
				initializeDatabase();
			return connection.prepareStatement(query);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static CallableStatement getCallableStatement(String query) {
		try {
			if (connection == null)
				initializeDatabase();
			return connection.prepareCall(query);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
