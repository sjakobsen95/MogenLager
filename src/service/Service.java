package service;

import java.sql.SQLException;

import storage.SQLCon;

public class Service {
	SQLCon sqlcon;

	public Service() {
		sqlcon = new SQLCon();
	}

	public boolean exists(int barcode) throws SQLException {
		return sqlcon.checkBarcode(sqlcon.getConnection(), barcode);
	}

	public String getName(int barcode) throws SQLException {
		return sqlcon.getName(sqlcon.getConnection(), barcode);
	}

	public double getPrice(int barcode) throws SQLException {
		return sqlcon.getPrice(sqlcon.getConnection(), barcode);
	}

	public String getDec(int barcode) throws SQLException {
		return sqlcon.getDec(sqlcon.getConnection(), barcode);
	}

	public void addProduct(int barcode) throws SQLException {
		sqlcon.updateAmount(sqlcon.getConnection(), barcode,
				(sqlcon.getAmount(sqlcon.getConnection(), barcode)) + 1);
	}

	public void createProduct(int barcode, String dec, int amount,
			double price, String name) throws SQLException {
		sqlcon.createProduct(sqlcon.getConnection(), barcode, dec, amount,
				price, name);

	}

	public int getAmount(int barcode) throws SQLException {
		return sqlcon.getAmount(sqlcon.getConnection(), barcode);
	}

	public void deleteProduct(int barcode) throws SQLException {
		sqlcon.deleteProduct(sqlcon.getConnection(), barcode);
	}

}
