package de.simon.moneymanager.views;

import java.util.Calendar;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;

import de.simon.moneymanager.model.Investment;
import de.simon.moneymanager.model.dao.factories.AbstractDaoFactory;

public class InvestmentAddView extends ViewPart {
	public static final String ID = "MoneyManager.view";
	private ScrolledForm scrolledForm;
	private Text textName;
	private Text textDescription;
	private Text textEuro;
	private CalendarCombo textDate;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {

		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		scrolledForm = toolkit.createScrolledForm(parent);
		scrolledForm.setText("Money Manager");
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		scrolledForm.getBody().setLayout(layout);

		toolkit.createLabel(scrolledForm.getBody(), "Name");
		textName = toolkit.createText(scrolledForm.getBody(), "");
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		textName.setLayoutData(td);

		toolkit.createLabel(scrolledForm.getBody(), "Beschreibung");
		textDescription = toolkit.createText(scrolledForm.getBody(), "",
				SWT.MULTI);
		TableWrapData td2 = new TableWrapData(TableWrapData.FILL_GRAB);
		textDescription.setLayoutData(td2);

		toolkit.createLabel(scrolledForm.getBody(), "Datum");

		textDate = new CalendarCombo(scrolledForm.getBody(), SWT.NONE);
		TableWrapData td3 = new TableWrapData(TableWrapData.FILL_GRAB);
		textDate.setLayoutData(td3);
		// textDate.setText("2012-02-05");

		toolkit.createLabel(scrolledForm.getBody(), "Euro");
		textEuro = toolkit.createText(scrolledForm.getBody(), "");
		TableWrapData td4 = new TableWrapData(TableWrapData.FILL_GRAB);
		textEuro.setLayoutData(td4);

		Button buttonSave = toolkit.createButton(scrolledForm.getBody(),
				"Speichern", SWT.PUSH);
		buttonSave.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {

					Calendar date = textDate.getDate();

					String euroString = textEuro.getText().replaceAll(",", ".");
					double amount = Double.parseDouble(euroString);

					Investment investment = new Investment();
					investment.setAmount(amount);
					investment.setDescription(textDescription.getText());
					investment.setName(textName.getText());
					investment.setInvestmentDate(date.getTime());

					AbstractDaoFactory daoFactory = AbstractDaoFactory
							.getDaoFactory(AbstractDaoFactory.SQLite);
					daoFactory.getInvestmentsDao().insertInvestment(investment);

					textName.setText("");
					textName.setFocus();
					textDescription.setText("");
					textEuro.setText("");

				} catch (Exception ex) {
					scrolledForm.setMessage("Geb dein Kram ordentlich ein!!!",
							IMessageProvider.ERROR);
				}
			}
		});

		toolkit.decorateFormHeading(scrolledForm.getForm());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		this.scrolledForm.setFocus();
	}
}
