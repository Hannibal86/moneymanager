package de.simon.moneymanager.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.simon.moneymanager.model.ICategory;
import de.simon.moneymanager.model.IInvestment;
import de.simon.moneymanager.model.Investment;
import de.simon.moneymanager.model.dao.factories.SQLiteDaoFactory;

public class SQLiteInvestmentDao implements IInvestmentsDao {

	@Override
	public int insertInvestment(IInvestment investment)
			throws ClassNotFoundException, SQLException {

		int generatedId = 0;

		Connection con = SQLiteDaoFactory.createConnection();

		PreparedStatement prep = con
				.prepareStatement("INSERT INTO Investments ('UserID','CategoryID','Name','Description','Date','Amount','CreateDate','ModDate') values (?, ?, ?, ?, ?, ?, ?, ? );");

		prep.setInt(1, 0);
		ICategory category = investment.getCategory();
		prep.setInt(2, category == null ? 0 : category.getCategoryId());
		prep.setString(3, investment.getName());
		prep.setString(4, investment.getDescription());
		prep.setDate(5, new Date(investment.getInvestmentDate().getTime()));
		prep.setDouble(6, investment.getAmount());
		prep.setDate(7, getToday());
		prep.setDate(8, getToday());

		prep.addBatch();

		con.setAutoCommit(false);
		prep.executeBatch();
		con.setAutoCommit(true);

		ResultSet generatedKeys = prep.getGeneratedKeys();
		if (generatedKeys.next()) {
			generatedId = generatedKeys.getInt(1);
			investment.setInvestmentId(generatedId);
		}

		con.close();

		return generatedId;
	}

	@Override
	public boolean deleteInvestment(IInvestment investment)
			throws SQLException, ClassNotFoundException {

		boolean execute = false;
		if (investment != null) {
			int investmentId = investment.getInvestmentId();
			Connection con = SQLiteDaoFactory.createConnection();
			Statement statement = con.createStatement();
			execute = statement.execute("DELETE FROM Investments WHERE id = "
					+ investmentId);
			if (con != null && !con.isClosed()) {
				con.close();
			}
		}

		return execute;
	}

	@Override
	public List<IInvestment> findInvestmentsByCategory(ICategory category)
			throws SQLException, ClassNotFoundException {
		List<IInvestment> investments = new ArrayList<IInvestment>();

		Connection con = SQLiteDaoFactory.createConnection();
		Statement statement = con.createStatement();
		ResultSet result = statement
				.executeQuery("SELECT * FROM Investments WHERE categoryid = "
						+ category.getCategoryId());

		while (result.next()) {
			Investment investment = createInvestmentFromDB(result);
			investment.setCategory(category);
			investments.add(investment);
		}

		return investments;
	}

	@Override
	public boolean updateInvestment(int id, IInvestment newInvestment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IInvestment> getInvestments() throws SQLException,
			ClassNotFoundException {
		List<IInvestment> investments = new ArrayList<IInvestment>();

		Connection con = SQLiteDaoFactory.createConnection();
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Investments");

		while (result.next()) {
			Investment investment = createInvestmentFromDB(result);
			investments.add(investment);
		}

		return investments;
	}

	private Date getToday() {

		java.util.Date date = new java.util.Date();

		return new Date(date.getTime());
	}

	private Investment createInvestmentFromDB(ResultSet resultSet)
			throws SQLException {
		Investment investment = new Investment();

		int id = resultSet.getInt("ID");
		investment.setInvestmentId(id);
		String name = resultSet.getString("Name");
		investment.setName(name);
		String description = resultSet.getString("Description");
		investment.setDescription(description);
		Date date = resultSet.getDate("Date");
		investment.setInvestmentDate(date);
		double amount = resultSet.getDouble("Amount");
		investment.setAmount(amount);

		return investment;
	}

}
