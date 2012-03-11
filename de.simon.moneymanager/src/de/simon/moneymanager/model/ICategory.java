package de.simon.moneymanager.model;

import java.util.List;

public interface ICategory {

	public int getCategoryId();

	public void setName(String name);

	public String getName();

	public void setDescription(String description);

	public String getDescription();

	public ICategory getParent();

	public List<ICategory> getSubCategories();

	public void addSubCategory(ICategory category);

	public void removeSubCategory(ICategory category);

	public List<IInvestment> getInvestments();

	public void addInvestment(IInvestment investment);

	public void removeInvestment(IInvestment investment);
}
