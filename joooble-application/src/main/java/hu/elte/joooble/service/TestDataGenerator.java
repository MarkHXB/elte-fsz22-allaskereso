package hu.elte.joooble.service;

import hu.elte.joooble.domain.job.*;
import hu.elte.joooble.domain.user.*;
import hu.elte.joooble.domain.file.*;
import hu.elte.joooble.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Component
public class TestDataGenerator {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CredentialRepository credentialRepository;

	@Autowired
	private EmployeeJobRepository employeeJobRepository;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private ResumeRepository resumeRepository;
	@Autowired
	private EmployerLogoRepository employerLogoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void createTestData() {
		//Employer
		Credential empr1 = createCredential("employer@valami.com", "123");
		credentialRepository.save(empr1);

		//Employee
		Credential empr2 = createCredential("employee@valami.com", "123");
		credentialRepository.save(empr2);

		Job job = createJob("valami", "valami", "valami", JobCategoryEnum.ADMINISTRATION, LocalDateTime.now(), BigDecimal.valueOf(33333d), 30, "valami");
		List<Job> jobs = new ArrayList<>();
		Employer employer = createEmployer(empr1, "Epam", "Budapest", null);
		employerRepository.save(employer);

		EmployerLogo logo = createEmployerLogo("Legjobb Logó", "default-logo.png", "png");
		employerLogoRepository.save(logo);
		logo.setEmployer(employerRepository.findById(employer.getId()).get());
		employer.setLogo(logo);

		job.setEmployer(employer);
		jobs.add(job);
		employer.setJobs(jobs);
		jobRepository.save(job);
		employerRepository.save(employer);


		//Employee
		//AppliedJob appliedJob = createAppliedJob(LocalDate.of(2022,12,10),job,employer.getId());
		//HiredJob hiredJob = createHiredJob(LocalDate.of(2022,12,10),job,employer.getId());
		//RejectedJob rejectedJob = createRejectedJob(LocalDate.of(2022,12,10),job,employer.getId(),"nem felelt meg.");
		//appliedJobRepository.save(appliedJob);
		//hiredJobRepository.save(hiredJob);
		//rejectedJobRepository.save(rejectedJob);
		List<EmployeeJob> employeeJobs = new ArrayList<>(); //appJobs.add(appliedJob);


		Employee employee = createEmployee(empr2, LocalDate.of(2000, 10, 10), null, employeeJobs);

		employeeRepository.save(employee);

		Resume resume = createResume("A legjobb CV", "default-cv", "docx");
		resumeRepository.save(resume);
		resume.setEmployee(employeeRepository.findById(employee.getId()).get());
		employee.setResume(resume);
		resumeRepository.save(resume);

		//EmployeeJob employeeJob = createEmployeeJob(LocalDate.of(2022, 12, 10), job, employee.getId(), JobStatusEnum.APPLIED);
		//employeeJobs.add(employeeJob);
		//employee.setJobs(employeeJobs);
		//employeeJobRepository.save(employeeJob);

		employeeRepository.save(employee);
	}

	// Helpers
	private Job createJob(String title, String location, String description, JobCategoryEnum jobCategory, LocalDateTime addedDate, BigDecimal salary, int workingHours, String positionName) {
		Job job = new Job();
		job.setTitle(title);
		job.setLocation(location);
		job.setDescription(description);
		job.setJobCategory(jobCategory);
		job.setAddedDate(addedDate);
		job.setSalary(salary);
		job.setWorkingHours(workingHours);
		job.setPositionName(positionName);
		job.setActive(true);
		return job;
	}

	private Employer createEmployer(Credential credentials, String companyName, String location, EmployerLogo employerLogo) {
		Employer employer = new Employer();
		employer.setCredential(credentials);
		employer.setCompanyName(companyName);
		employer.setLocation(location);
		employer.setLogo(employerLogo);
		employer.setActive(true);
		return employer;
	}

	private Credential createCredential(String email, String password) {
		Credential cred = new Credential();
		cred.setEmail(email);
		cred.setPassword(passwordEncoder.encode(password));
		return cred;
	}

	private Employee createEmployee(Credential credentials, LocalDate birthdate, Resume resume, List<EmployeeJob> employeeJobs) {
		Employee employee = new Employee();
		employee.setCredential(credentials);
		employee.setBirthdate(birthdate);
		employee.setResume(resume);
		employee.setActive(true);
		employee.setJobs(employeeJobs);
		return employee;
	}

	private Resume createResume(String title, String fileName, String extension) {
		Resume resume = new Resume();

		resume.setFileName(fileName);
		resume.setTitle(title);
		resume.setExtension(extension);

		return resume;
	}

	private EmployerLogo createEmployerLogo(String title, String fileName, String extension) {
		EmployerLogo logo = new EmployerLogo();

		logo.setFileName(fileName);
		logo.setTitle(title);
		logo.setExtension(extension);

		return logo;
	}

	private EmployeeJob createEmployeeJob(LocalDate date, Job job, Long employeeId, JobStatusEnum status) {
		EmployeeJob appJob = new EmployeeJob();
		appJob.setStatusChanged(date);
		appJob.setEmployee(employeeRepository.findById(employeeId).get());
		appJob.setJob(job);
		appJob.setStatus(status);
		return appJob;
	}
}