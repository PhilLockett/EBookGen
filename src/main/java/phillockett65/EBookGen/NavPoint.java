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
 * NavPoint is a simple class that captures a navMap entry and 
 * provides access via the toString() method only.
 */
package phillockett65.EBookGen;

public class NavPoint {

	private static int count = 0;
	
	private String id;
	private String title;
	private int playOrder;
	private String file;

	public NavPoint(String id, String title, String file) {
		super();
		this.id = id;
		this.title = title;
		this.playOrder = ++count;
		this.file = file;
	}

	@Override
	public String toString() {
		String node = "\n";
		node += "		<navPoint class=\"chapter\" id=\"" + id + "\" playOrder=\"" + playOrder + "\">\n";
		node += "			<navLabel>\n";
		node += "				<text>" + title + "</text>\n";
		node += "			</navLabel>\n";
		node += "			<content src=\"" + file + "\"/>\n";
		node += "		</navPoint>\n";

		return node;
	}

	public static void resetPlayOrder() {
		count = 0;
	}

	
}
