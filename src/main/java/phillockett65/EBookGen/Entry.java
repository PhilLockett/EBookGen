/*  EBookGen - a simple application to generate an epub template.
 *
 *  Copyright 2021 Philip Lockett.
 *
 *  This file is part of EBookGen.
 *
 *  EBookGen is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  EBookGen is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with EBookGen.  If not, see <https://www.gnu.org/licenses/>.
 */

/*
 * Entry is a simple class that captures a Contents page entry and 
 * provides access via the toString() method only.
 */
package phillockett65.EBookGen;

public class Entry {
	private String title;
	private String file;

	public Entry(String title, String file) {
		super();
		this.title = title;
		this.file = file;
	}

	@Override
	public String toString() {
		String node = "	<br/>\n";
		node += "	<a href=\"" + file + "\">" + title + "</a>\n";

		return node;
	}

}
