package de.simon.moneymanager.model;

import java.util.Date;

public class Investment implements IInvestment {

	public static ICategory defaultCategory = new Category("Not Categorized");
	private int investmentId = -1;
	private String name;
	private double amount;
	private ICategory category;
	private String description;
	private Date creationDate;
	private Date investmentDate;

	public int getInvestmentId() {
		return this.investmentId;
	}

	public void setInvestmentId(int id) {
		// TODO throw Exception
		this.investmentId = id;
	}

	@Override
	public ICategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(ICategory category) {
		this.category = category;
	}

	@Override
	public double getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Date getCreationDate() {
		return this.creationDate;
	}

	@Override
	public Date getInvestmentDate() {
		return this.investmentDate;
	}

	@Override
	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}
}
