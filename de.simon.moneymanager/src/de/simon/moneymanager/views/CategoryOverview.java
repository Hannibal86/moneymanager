package de.simon.moneymanager.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class CategoryOverview extends ViewPart {

	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		this.viewer = new TreeViewer(parent);
	}

	@Override
	public void setFocus() {
		this.viewer.getControl().setFocus();
	}

}
