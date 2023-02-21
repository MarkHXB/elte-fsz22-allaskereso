package hu.elte.joooble.domain.user;

import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.file.EmployerLogo;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employer extends User {
	@Column(name = "CompanyName", unique = true, nullable = false)
	private String companyName;

	@NonNull
	@Column(name = "Location", nullable = false)
	private String location;

	@OneToMany
	private List<Job> jobs;

	@OneToOne
	private EmployerLogo logo;

	public EmployerLogo getLogo() {
		return logo;
	}

	public void setLogo(EmployerLogo logo) {
		this.logo = logo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@NonNull
	public String getLocation() {
		return location;
	}

	public void setLocation(@NonNull String location) {
		this.location = location;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}
