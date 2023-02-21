package hu.elte.joooble.domain.file;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import hu.elte.joooble.domain.user.Employee;

@Entity
public class Resume extends File{
	@OneToOne
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}