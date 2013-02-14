/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.tools.forge.ui.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.jboss.forge.ui.UIInputComponent;
import org.jboss.forge.ui.UISelectMany;
import org.jboss.forge.ui.hints.InputType;
import org.jboss.forge.ui.hints.InputTypes;
import org.jboss.tools.forge.ui.wizards.ForgeWizardPage;

public class CheckboxTableControlBuilder extends ControlBuilder {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Control build(ForgeWizardPage page,
			final UIInputComponent<?, Object> input, final Composite container) {
		final Table table = new Table(container, SWT.CHECK | SWT.BORDER
				| SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		UISelectMany<Object> selectMany = (UISelectMany) input;
		final List<Object> data = new ArrayList<Object>();
		setValueFor(input, data);
		Iterator<Object> iterator = selectMany.getValueChoices().iterator();
		while (iterator.hasNext()) {
			Object next = iterator.next();
			TableItem item = new TableItem(table, SWT.NONE);
			item.setData(next);
			item.setText(next.toString());
		}
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.item instanceof TableItem) {
					TableItem source = (TableItem) e.item;
					if (source.getChecked()) {
						data.add(source.getData());
					} else {
						data.remove(source.getData());
					}
				}
			}
		});
		return table;
	}

	@Override
	protected Class<?> getProducedType() {
		return Object.class;
	}

	@Override
	protected InputType getSupportedInputType() {
		return InputTypes.SELECT_MANY_CHECKBOX;
	}

	@Override
	protected Iterable<Class<?>> getSupportedInputComponentTypes() {
		List<Class<?>> result = new ArrayList<Class<?>>();
		result.add(UISelectMany.class);
		return result;
	}
}
