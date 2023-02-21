package hu.elte.joooble.webdomain;

public class JobCategoryListView {
	private final String name;
	private final String value;
	
	public JobCategoryListView(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
