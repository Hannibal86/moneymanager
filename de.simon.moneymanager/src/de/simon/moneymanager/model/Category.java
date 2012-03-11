package de.simon.moneymanager.model;

import java.util.List;

public class Category implements ICategory {

	private int categoryId;
	private String name;
	private String description;
	private ICategory parent;
	private List<ICategory> subCategories;
	private List<IInvestment> investments;

	public Category(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public ICategory getParent() {
		return this.parent;
	}

	public void setParent(ICategory category) {
		this.parent = category;
	}

	@Override
	public List<ICategory> getSubCategories() {
		return this.subCategories;
	}

	@Override
	public void addSubCategory(ICategory category) {
		this.subCategories.add(category);
	}

	@Override
	public void removeSubCategory(ICategory category) {
		this.subCategories.remove(category);
	}

	@Override
	public List<IInvestment> getInvestments() {
		return this.investments;
	}

	@Override
	public void addInvestment(IInvestment investment) {
		this.investments.add(investment);
	}

	@Override
	public void removeInvestment(IInvestment investment) {
		this.investments.remove(investment);
	}
}
