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
	private String file;
	private String id;
	private String title;
	private String type;
	private boolean linear = true;

	public Mother(String file, String id, String title, String type) {
		super();
		this.file = file;
		this.id = id;
		this.title = title;
		this.type = type;
	}

	public void setLinear(boolean linear) {
		this.linear = linear;
	}

	public String getFile() {
		return file;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * Get an object that represents a Contents page entry.
	 * 
	 * @return an object that represents a Contents page entry.
	 */
	public Entry getEntry() {
		return new Entry(title, file);
	}

	/**
	 * Get an object that represents a manifest item.
	 * 
	 * @return an object that represents a manifest item.
	 */
	public Item getManifestItem() {
		return new Item(id, file, type);
	}

	/**
	 * Get an object that represents a spine item reference.
	 * 
	 * @return an object that represents a spine item reference.
	 */
	public ItemRef getItemRef() {
		return new ItemRef(id, linear);
	}

	/**
	 * Get an object that represents a navMap navPoint.
	 * 
	 * @return an object that represents a navMap navPoint.
	 */
	public NavPoint getNavPoint() {
		return new NavPoint(id, title, file);
	}

}
