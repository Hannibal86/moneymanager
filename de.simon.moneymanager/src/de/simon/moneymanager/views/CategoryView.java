package de.simon.moneymanager.views;

import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
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

import de.simon.moneymanager.labelprovider.CategoryLabelProvider;
import de.simon.moneymanager.model.Category;
import de.simon.moneymanager.model.Database;

public class CategoryView extends ViewPart {

	private FormToolkit toolkit;
	private ScrolledForm scrolledForm;
	private Text textName;
	private Text textDescription;
	private ComboViewer comboViewer;

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());

		scrolledForm = toolkit.createScrolledForm(parent);
		scrolledForm.setText("Kategorien erstellen");
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		scrolledForm.getBody().setLayout(layout);

		toolkit.createLabel(scrolledForm.getBody(), "Eltern Kategorie");
		comboViewer = new ComboViewer(scrolledForm.getBody());
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setLabelProvider(new CategoryLabelProvider());

		List<Category> categories = Database.getInstance().getRootCategories();
		comboViewer.setInput(categories);

		toolkit.adapt(comboViewer.getControl(), true, true);

		toolkit.createLabel(scrolledForm.getBody(), "Kategorie Name");
		textName = toolkit.createText(scrolledForm.getBody(), "");
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		textName.setLayoutData(td);

		toolkit.createLabel(scrolledForm.getBody(), "Beschreibung");
		textDescription = toolkit.createText(scrolledForm.getBody(), "");
		TableWrapData td2 = new TableWrapData(TableWrapData.FILL_GRAB);
		textDescription.setLayoutData(td2);

		Button buttonSave = toolkit.createButton(scrolledForm.getBody(),
				"Speichern", SWT.PUSH);
		buttonSave.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// TODO add Category

				} catch (Exception ex) {
					scrolledForm.setMessage("Geb dein Kram ordentlich ein!!!",
							IMessageProvider.ERROR);
				}
			}
		});

		toolkit.decorateFormHeading(scrolledForm.getForm());
	}

	@Override
	public void setFocus() {
		this.comboViewer.getControl().setFocus();
	}

}
