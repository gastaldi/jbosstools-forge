package org.jboss.tools.aesh.core.internal.ansi;



public class ShowCursor extends AbstractAnsiControlSequence {

	public ShowCursor(String arguments) {}

	@Override
	public AnsiControlSequenceType getType() {
		return AnsiControlSequenceType.SHOW_CURSOR;
	}

}