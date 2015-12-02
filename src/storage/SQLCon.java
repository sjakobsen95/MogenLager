package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCon {

	private String userName = "smlager";

	private String password = "smhackveng";

	private String serverName = "db4free.net";

	private int portNumber = 3306;

	private String dbName = "lager";

	public SQLCon() {

	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		// Her laves en forbindelse til sql serveren.

		conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":"
				+ portNumber + "/" + dbName, userName, password);

		return conn;
	}

	public boolean checkBarcode(Connection conn, int barcode)
			throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM products WHERE product_barcode="
							+ "'" + barcode + "';");
			return rs.next();

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void createProduct(Connection conn, int barcode, String dec,
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

	public void removeProduct(Connection conn, int barcode) throws SQLException {
		PreparedStatement stmt = null;
		try {

			// Her bruges et prepared statement til at oprette en bil udfra de
			// opslyninger der er givet.
			stmt = conn
					.prepareStatement("DELETE FROM products WHERE product_barcode=?");
			stmt.setInt(1, barcode);

			stmt.execute();
		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public String getDec(Connection conn, int barcode) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT product_dec FROM products WHERE product_barcode="
							+ barcode + ";");
			rs.next();
			return rs.getString("product_dec");

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}

	}

	public void updateDec(Connection conn, int barcode, String dec)
			throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn
					.prepareStatement("UPDATE products SET product_dec=? WHERE product_barcode= ?");
			stmt.setString(1, dec);
			stmt.setInt(2, barcode);

			stmt.execute();

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public int getAmount(Connection conn, int barcode) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT product_amount FROM products WHERE product_barcode="
							+ barcode + ";");
			rs.next();
			return rs.getInt("product_dec");

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void updateAmount(Connection conn, int barcode, int amount)
			throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn
					.prepareStatement("UPDATE products SET product_amount=? WHERE product_barcode= ?");
			stmt.setInt(1, amount);
			stmt.setInt(2, barcode);

			stmt.execute();

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public double getPrice(Connection conn, int barcode) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT product_price FROM products WHERE product_barcode="
							+ barcode + ";");
			rs.next();
			return rs.getDouble("product_price");

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void updatePrice(Connection conn, int barcode, double price)
			throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn
					.prepareStatement("UPDATE products SET product_price=? WHERE product_barcode= ?");
			stmt.setDouble(1, price);
			stmt.setInt(2, barcode);

			stmt.execute();

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public String getName(Connection conn, int barcode) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT product_name FROM products WHERE product_barcode="
							+ barcode + ";");
			rs.next();
			return rs.getString("product_name");

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void updateName(Connection conn, int barcode, String name)
			throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn
					.prepareStatement("UPDATE products SET product_name=? WHERE product_barcode= ?");
			stmt.setString(1, name);
			stmt.setInt(2, barcode);

			stmt.execute();

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void deleteProduct(Connection conn, int barcode) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn
					.prepareStatement("DELETE FROM products WHERE product_barcode= ?");
			stmt.setInt(1, barcode);
			stmt.execute();

		} finally {

			if (stmt != null) {
				stmt.close();
			}
		}
	}

}
