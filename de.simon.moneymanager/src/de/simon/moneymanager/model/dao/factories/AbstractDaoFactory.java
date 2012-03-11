package de.simon.moneymanager.model.dao.factories;

import de.simon.moneymanager.model.dao.ICategoryDao;
import de.simon.moneymanager.model.dao.IInvestmentsDao;

public abstract class AbstractDaoFactory {

	public final static int SQLite = 1;

	public AbstractDaoFactory() {
	}

	public abstract ICategoryDao getCategoryDao();

	public abstract IInvestmentsDao getInvestmentsDao();

	public static AbstractDaoFactory getDaoFactory(int whichDoaFactory) {
		AbstractDaoFactory doaFactory;
		switch (whichDoaFactory) {
		case SQLite:
			doaFactory = new SQLiteDaoFactory();
			break;
		default:
			doaFactory = null;
			break;
		}

		return doaFactory;
	}
}
