package de.simon.moneymanager.model.dao;

import java.util.List;

import de.simon.moneymanager.model.ICategory;
import de.simon.moneymanager.model.IInvestment;

public class SQLiteCategoryDao implements ICategoryDao {

	@Override
	public int insertInvestment(ICategory category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCategory(ICategory category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IInvestment findCategoryByParentCategory(ICategory parentCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInvestment(int id, ICategory newCategory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ICategory> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
