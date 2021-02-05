package phillockett65.EBookGen;

public class ItemRef {
	private String id;
	private boolean linear;
	public ItemRef(String id, boolean linear) {
		super();
		this.id = id;
		this.linear = linear;
	}
	@Override
	public String toString() {
		String node = "		<itemref idref=\"" + id + "\"";
		if (!linear)
			node += " linear=\"no\"";
		node += "/>\n";

		return node;
	}

}
