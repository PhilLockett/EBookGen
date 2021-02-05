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
	public static void reset() {
		count = 0;
	}

	
}
