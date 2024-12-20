package com.jdc.collection;

import java.util.Comparator;

public class NameAscComparator implements Comparator<NoteBook> {
	
	@Override
	public int compare(NoteBook nb1, NoteBook nb2) {
		return nb1.getName().compareTo(nb2.getName());
	}

}
