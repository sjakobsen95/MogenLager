package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import service.Service;
import storage.SQLCon;

public class TestApp {
	static Service service;
	static SQLCon sqlcon;
	private static String userName = "root";

	private static String password = "root";

	private static String serverName = "localhost";

	private static int portNumber = 8889;

	private static String dbName = "lager";

	public static void main(String[] args) throws SQLException {
		createSQL();
	}

	private static void createSQL() throws SQLException {
		// service.createProduct(123, "Dette er et test product", 2, 32,
		// "Test Produkt");
		createProduct(getConnection(), 123, "Dette er et test product", 2, 32,
				"Test Produkt");
	}

	private static Connection getConnection() throws SQLException {
		Connection conn = null;
		// Her laves en forbindelse til sql serveren.

		conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":"
				+ portNumber + "/" + dbName, userName, password);

		return conn;
	}

	private static void createProduct(Connection conn, int barcode, String dec,
			int amount, double price, String name) throws SQLException {
		PreparedStatement stmt = null;
		try {

			// Her bruges et prepared statement til at oprette en bil udfra de
			// opslyninger der er givet.
			stmt = conn
					.prepareStatement("INSERT INTO products (product_barcode, product_dec, product_amount, product_price, product_name) VALUES ( ?, ?, ?, ?, ?)");
			stmt.setInt(1, barcode);
			stmt.setString(2, dec);
			stmt.setInt(3, amount);
			stmt.setDouble(4, price);
			stmt.setString(5, name);
			stmt.execute();
		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}
}
