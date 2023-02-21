package hu.elte.joooble.webdomain;

public class JobListView {
	private final Long id;
	private final String title;
	private final String employerLogoPath;
	private final String employerName;
	private final String position;
	private final String description;

	public JobListView(Long id, String title, String employerLogoPath, String employerName, String position, String description) {
		this.id = id;
		this.title = title;
		this.employerLogoPath = employerLogoPath;
		this.employerName = employerName;
		this.position = position;
		this.description = description;
	}
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getEmployerLogoPath() {
		return employerLogoPath;
	}

	public String getEmployerName() {
		return employerName;
	}

	public String getDescription() {
		return description;
	}
	public String getPosition() {
		return position;
	}
}
