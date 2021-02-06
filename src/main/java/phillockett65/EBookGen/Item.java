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
 * Item is a simple class that captures a manifest item and 
 * provides access via the toString() method only.
 */
package phillockett65.EBookGen;

public class Item {
	private String id;
	private String file;
	private String type;

	public Item(String id, String file, String type) {
		super();
		this.id = id;
		this.file = file;
		this.type = type;
	}

	@Override
	public String toString() {
		String node = "		<item href=\"" + file + "\"";
		node += " id=\"" + id + "\"";
		node += " media-type=\"" + type + "\"";
		node += "/>\n";

		return node;
	}

}
