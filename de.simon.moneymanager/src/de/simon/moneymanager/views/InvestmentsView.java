package de.simon.moneymanager.views;

import java.sql.SQLException;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import de.simon.moneymanager.model.IInvestment;
import de.simon.moneymanager.model.dao.IInvestmentsDao;
import de.simon.moneymanager.model.dao.factories.AbstractDaoFactory;
import de.simon.moneymanager.model.dao.factories.SQLiteDaoFactory;

public class InvestmentsView extends ViewPart {

	private TableViewer viewer;

	@Override
	public void createPartControl(Composite parent) {

		createViewer(parent);
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		AbstractDaoFactory daoFactory = AbstractDaoFactory
				.getDaoFactory(SQLiteDaoFactory.SQLite);

		IInvestmentsDao investmentsDao = daoFactory.getInvestmentsDao();
		try {
			viewer.setInput(investmentsDao.getInvestments());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Make the selection available to other views
		getSite().setSelectionProvider(viewer);
		// Set the sorter for the table

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	private void createColumns(Composite parent, TableViewer viewer) {
		TableViewerColumn col = createTableViewerColumn("Date", 70, 0);
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof IInvestment) {
					return ((IInvestment) element).getInvestmentDate()
							.toString();
				}

				return super.getText(element);
			}

		});

		col = createTableViewerColumn("Name", 150, 0);
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof IInvestment) {
					return ((IInvestment) element).getName();
				}

				return super.getText(element);
			}

		});
		col = createTableViewerColumn("Amount", 100, 0);
		col.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof IInvestment) {
					return ((IInvestment) element).getAmount() + "";
				}

				return super.getText(element);
			}

		});
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	@Override
	public void setFocus() {
		this.viewer.getControl().setFocus();
	}

}
