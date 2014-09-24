/**
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.tools.forge.ui.util;

import org.apache.maven.archetype.catalog.ArchetypeCatalog;
import org.eclipse.core.runtime.CoreException;
import org.jboss.forge.addon.maven.archetype.ArchetypeCatalogFactory;
import org.jboss.tools.forge.ui.internal.ForgeUIPlugin;

/**
 * Wrapper for
 * {@link org.eclipse.m2e.core.internal.archetype.ArchetypeCatalogFactory}
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
@SuppressWarnings("restriction")
public class ArchetypeCatalogFactoryWrapper implements ArchetypeCatalogFactory {

	private final org.eclipse.m2e.core.internal.archetype.ArchetypeCatalogFactory delegate;

	public ArchetypeCatalogFactoryWrapper(
			org.eclipse.m2e.core.internal.archetype.ArchetypeCatalogFactory delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public ArchetypeCatalog getArchetypeCatalog() {
		try {
			return delegate.getArchetypeCatalog();
		} catch (CoreException e) {
			ForgeUIPlugin.log(e);
			return null;
		}
	}

	@Override
	public String getName() {
		return delegate.getDescription();
	}

}
