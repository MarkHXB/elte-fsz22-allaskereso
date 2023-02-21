package hu.elte.joooble.domain.file;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;

@Entity
public class EmployerLogo extends File{
	@OneToOne
	private Employer employer;

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
}