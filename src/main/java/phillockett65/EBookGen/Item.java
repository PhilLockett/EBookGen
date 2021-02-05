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
