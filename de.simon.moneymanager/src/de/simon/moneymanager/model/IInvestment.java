package de.simon.moneymanager.model;

import java.util.Date;

public interface IInvestment {

	public int getInvestmentId();

	public void setInvestmentId(int investmentId);

	public ICategory getCategory();

	public void setCategory(ICategory category);

	public double getAmount();

	public void setAmount(double amount);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public Date getCreationDate();

	public Date getInvestmentDate();

	public void setInvestmentDate(Date date);
}
