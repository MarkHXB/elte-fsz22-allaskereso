package hu.elte.joooble.webdomain;

public class JobDetailsView {

	private final String userType;
	private final Long jobId;
	private final String employerLogoPath;
	private final String employerCompanyName;
	private final String jobAddedDate;
	private final String jobPositionName;
	private final String jobLocation;
	private final String jobDescription;
	private final String employerEmail;
	private final String employerLocation;
	private final String jobStatus;

	private final boolean jobIsAvailable;

	public JobDetailsView(String userType, Long jobId, String employerLogoPath, String employerCompanyName, String jobAddedDate,
			String jobPositionName, String jobLocation, String jobDescription, String employerEmail,
			String employerLocation, String jobStatus,boolean jobIsAvailable) {
		this.userType = userType;
		this.jobId = jobId;
		this.employerLogoPath = employerLogoPath;
		this.employerCompanyName = employerCompanyName;
		this.jobAddedDate = jobAddedDate;
		this.jobPositionName = jobPositionName;
		this.jobLocation = jobLocation;
		this.jobDescription = jobDescription;
		this.employerEmail = employerEmail;
		this.employerLocation = employerLocation;
		this.jobStatus = jobStatus;
		this.jobIsAvailable = jobIsAvailable;
	}

	public String getUserType() {
		return userType;
	}

	public Long getJobId() {
		return jobId;
	}

	public String getEmployerLogoPath() {
		return employerLogoPath;
	}

	public String getEmployerCompanyName() {
		return employerCompanyName;
	}

	public String getJobAddedDate() {
		return jobAddedDate;
	}

	public String getJobPositionName() {
		return jobPositionName;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getEmployerEmail() {
		return employerEmail;
	}

	public String getEmployerLocation() {
		return employerLocation;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public boolean isJobIsAvailable() {
		return jobIsAvailable;
	}
}
