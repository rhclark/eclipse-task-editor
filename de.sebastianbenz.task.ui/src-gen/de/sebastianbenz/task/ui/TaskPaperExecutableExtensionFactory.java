/*******************************************************************************
 * Copyright (c) 2011 Sebastian Benz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sebastian Benz - initial API and implementation
 *******************************************************************************/
/*
 * generated by Xtext
 */
package de.sebastianbenz.task.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class TaskPaperExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return de.sebastianbenz.task.ui.internal.TaskPaperActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return de.sebastianbenz.task.ui.internal.TaskPaperActivator.getInstance().getInjector("de.sebastianbenz.task.TaskPaper");
	}
	
}
