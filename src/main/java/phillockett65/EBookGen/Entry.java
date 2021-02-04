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
