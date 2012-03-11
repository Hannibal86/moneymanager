package de.simon.moneymanager.labelprovider;


import org.eclipse.jface.viewers.LabelProvider;

import de.simon.moneymanager.model.Category;

public class CategoryLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {

		if (element instanceof Category) {
			return ((Category) element).getName();
		}
		return super.getText(element);
	}

}
