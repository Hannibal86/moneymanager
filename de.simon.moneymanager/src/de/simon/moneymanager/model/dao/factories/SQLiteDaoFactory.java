package de.simon.moneymanager.model.dao.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.simon.moneymanager.model.dao.SQLiteCategoryDao;
import de.simon.moneymanager.model.dao.ICategoryDao;
import de.simon.moneymanager.model.dao.IInvestmentsDao;
import de.simon.moneymanager.model.dao.SQLiteInvestmentDao;

public class SQLiteDaoFactory extends AbstractDaoFactory {

	public SQLiteDaoFactory() {
	}

	public static Connection createConnection() throws ClassNotFoundException,
			SQLException {

		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager
				.getConnection("jdbc:sqlite:C:\\runtime-MoneyManager.product\\MoneyManager.db");

		return con;
	}

	@Override
	public ICategoryDao getCategoryDao() {
		return new SQLiteCategoryDao();
	}

	@Override
	public IInvestmentsDao getInvestmentsDao() {
		return new SQLiteInvestmentDao();
	}
}
