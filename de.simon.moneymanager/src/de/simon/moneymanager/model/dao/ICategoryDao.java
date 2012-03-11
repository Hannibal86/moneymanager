package de.simon.moneymanager.model.dao;

import java.util.List;

import de.simon.moneymanager.model.ICategory;
import de.simon.moneymanager.model.IInvestment;

public interface ICategoryDao {

	public int insertInvestment(ICategory category);

	public boolean deleteCategory(ICategory category);

	public IInvestment findCategoryByParentCategory(ICategory parentCategory);

	public boolean updateInvestment(int id, ICategory newCategory);

	public List<ICategory> getCategories();
}
