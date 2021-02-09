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
 * Mother is a simple class that captures basic content information and 
 * generates collection objects on request.
 */
package phillockett65.EBookGen;

public class Mother {
	private static int count = 0;

	private int playOrder;
	private String file;
	private String id;
	private String title;
	private String type;
	private boolean linear = true;

	/**
	 * Constructor.
	 * 
	 * @param file associated with a collection object.
	 * @param id used to identify the collection object.
	 * @param title of the collection object.
	 * @param type of the collection object.
	 */
	public Mother(String file, String id, String title, String type) {
		super();
		this.file = file;
		this.id = id;
		this.title = title;
		this.type = type;
	}

	/**
	 * Set the linear value.
	 * 
	 * @param linear.
	 */
	public void setLinear(boolean linear) {
		this.linear = linear;
	}

	/**
	 * Get the File.
	 * 
	 * @return the File.
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Get the Title.
	 * @return the Title.
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * Get a string that represents a Contents page entry.
	 * 
	 * @return a string that represents a Contents page entry.
	 */
	public String getEntry() {
		String node = "	<br/>\n";
		node += "	<a href=\"" + file + "\">" + title + "</a>\n";

		return node;
	}

	/**
	 * Get a string that represents a manifest item.
	 * 
	 * @return a string that represents a manifest item.
	 */
	public String getItem() {
		String node = "		<item href=\"" + file + "\"";
		node += " id=\"" + id + "\"";
		node += " media-type=\"" + type + "\"";
		node += "/>\n";

		return node;
	}

	/**
	 * Get a string that represents a spine item reference.
	 * 
	 * @return a string that represents a spine item reference.
	 */
	public String getItemRef() {
		String node = "		<itemref idref=\"" + id + "\"";
		if (!linear)
			node += " linear=\"no\"";
		node += "/>\n";

		return node;
	}

	/**
	 * Get a string that represents a navMap navPoint.
	 * 
	 * @return a string that represents a navMap navPoint.
	 */
	public String getNavPoint() {
		this.playOrder = ++count;

		String node = "\n";
		node += "		<navPoint class=\"chapter\" id=\"" + id + "\" playOrder=\"" + playOrder + "\">\n";
		node += "			<navLabel>\n";
		node += "				<text>" + title + "</text>\n";
		node += "			</navLabel>\n";
		node += "			<content src=\"" + file + "\"/>\n";
		node += "		</navPoint>\n";

		return node;
	}

	/**
	 * Resets the static play order count, used for each generate request.
	 */
	public static void resetPlayOrder() {
		count = 0;
	}

}
