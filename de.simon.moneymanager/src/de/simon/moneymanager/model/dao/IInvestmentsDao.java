package de.simon.moneymanager.model.dao;

import java.sql.SQLException;
import java.util.List;

import de.simon.moneymanager.model.ICategory;
import de.simon.moneymanager.model.IInvestment;

public interface IInvestmentsDao {

	public int insertInvestment(IInvestment investment)
			throws ClassNotFoundException, SQLException;

	public boolean deleteInvestment(IInvestment investment)
			throws SQLException, ClassNotFoundException;

	public List<IInvestment> findInvestmentsByCategory(ICategory category)
			throws SQLException, ClassNotFoundException;

	public boolean updateInvestment(int id, IInvestment newInvestment);

	public List<IInvestment> getInvestments() throws SQLException,
			ClassNotFoundException;
}
