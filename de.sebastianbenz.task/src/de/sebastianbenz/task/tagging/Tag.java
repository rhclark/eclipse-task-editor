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
package de.sebastianbenz.task.tagging;

public class Tag extends Region {

	public static final Tag EMPTY_TAG = Tag.create(null, null, 0, 0);
	private final String name;
	private final String value;
	private Tag(String name, String value, int offset, int length) {
		super(offset, length);
		this.name = name;
		this.value = value;
	}

	public static Tag from(String name) {
		return create(name, null, 0, 0);
	}

	@Override
	public String toString() {
		String valueString = value != null ? "(" + value + ")" : "";
		return "@" + name + valueString;
	}

	public static Tag create(String name, String value, int offset, int length) {
		return new Tag(name, value, offset, length);
	}

	public String getName() {
		return name;
	}
	
	
}
