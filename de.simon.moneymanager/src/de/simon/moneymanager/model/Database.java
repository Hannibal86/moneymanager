package de.simon.moneymanager.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author Simon
 * @deprecated use the daoFactories
 */
public class Database {

	private Connection con = null;

	private static Database instance = new Database();

	public static Database getInstance() {
		return instance;
	}

	private Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager
					.getConnection("jdbc:sqlite:C:\\runtime-MoneyManager.product\\MoneyManager.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Category> getRootCategories() {
		List<Category> categories = new ArrayList<Category>();

		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM InvestmentCategories WHERE ParentID = 0;");
			while (rs.next()) {
				// Category category = new Category(rs.getInt("ID"),
				// rs.getInt("ParentID"), rs.getString("Name"));
				// categories.add(category);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public void appendSubCategories(Category parent) {
		//
		// Statement stat;
		// try {
		// stat = con.createStatement();
		// ResultSet rs = stat
		// .executeQuery("SELECT * FROM InvestmentCategories WHERE ParentID = "
		// + parent.getId() + ";");
		// while (rs.next()) {
		// Category category = new Category(rs.getInt("ID"),
		// rs.getInt("ParentID"), rs.getString("Name"));
		// parent.addChild(category);
		// }
		// rs.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	public void insertInvestment(int category, String name, String description,
			Date date, int userID, double amount) {
		try {

			PreparedStatement prep = con
					.prepareStatement("insert into Investments ('UserID','CategoryID','Name','Description','Date','Amount','CreateDate','ModDate') values (?, ?, ?, ?, ?, ?, ?, ? );");

			prep.setInt(1, userID);
			prep.setInt(2, category);
			prep.setString(3, name);
			prep.setString(4, description);
			prep.setDate(5, date);
			prep.setDouble(6, amount);
			prep.setDate(7, getToday());
			prep.setDate(8, getToday());

			prep.addBatch();

			con.setAutoCommit(false);
			prep.executeBatch();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertCategory(int parentId, int userId, String name,
			String description) {
		try {
			PreparedStatement prep = con
					.prepareStatement("INSERT INTO InvestmentCategories ('Name','Description','CreateDate','ModDate','ParentID','UserID') VALUES (?, ?, ?, ?, ?, ?);");

			prep.setString(1, name);
			prep.setString(2, description);
			prep.setDate(3, getToday());
			prep.setDate(4, getToday());
			prep.setInt(5, parentId);
			prep.setInt(6, userId);

			prep.addBatch();

			con.setAutoCommit(false);
			prep.executeBatch();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printInvestments() {
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from investments;");
			while (rs.next()) {
				System.out.println("Date = " + rs.getDate("Date").toString());
				System.out.println("Name = " + rs.getString("Name"));
				System.out.println("Amount = " + rs.getString("Amount"));
				System.out.println("-----");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dispose() throws SQLException {
		if (this.con != null)
			this.con.close();
	}

	private Date getToday() {
		Calendar calendar = Calendar.getInstance(Locale.GERMANY);

		StringBuilder sb = new StringBuilder();
		sb.append(calendar.get(Calendar.YEAR));
		sb.append("-");
		sb.append(calendar.get(Calendar.MONTH));
		sb.append("-");
		sb.append(calendar.get(Calendar.DAY_OF_MONTH));

		return Date.valueOf(sb.toString());
	}
}
