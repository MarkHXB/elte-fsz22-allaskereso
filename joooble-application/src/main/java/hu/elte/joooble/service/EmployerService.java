package hu.elte.joooble.service;

import java.util.ArrayList;
import java.util.List;

import hu.elte.joooble.domain.user.Credential;
import hu.elte.joooble.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.elte.joooble.domain.file.EmployerLogo;
import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.user.Employer;
import hu.elte.joooble.repository.EmployerLogoRepository;
import hu.elte.joooble.repository.EmployerRepository;
import hu.elte.joooble.repository.JobRepository;

@Service
public class EmployerService {
	@Autowired
	private EmployerRepository employerRepository;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private EmployerLogoRepository employerLogoRepository;
	@Autowired
	private CredentialRepository credentialRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public Iterable<Job> findAllJobByEmployerId(Long empId) {
		return jobRepository.findAllJobsByEmployerId(empId);
	}


	public Employer findEmployerByCredentialEmail(String email){
		return employerRepository.findEmployerByCredential_Email(email);
	}

	public void saveEmployer(Long id, String email, String password,String companyName, String companyAddress, EmployerLogo logo, List<Job> jobs){
		Employer emp = findEmployerById(id);
		Credential credential = emp.getCredential();
		credential.setEmail(email);
		credential.setPassword(passwordEncoder.encode(password));
		credentialRepository.save(credential);
		emp.setCredential(credential);
		emp.setCompanyName(companyName);
		emp.setLocation(companyAddress);
		employerLogoRepository.save(logo);
		emp.setLogo(logo);
		employerRepository.save(emp);
		logo.setEmployer(emp);
		emp.setJobs(jobs);
		employerLogoRepository.save(logo);
	}

	public Employer findEmployerById(Long id) {
		return employerRepository.findById(id).get();
	}
	
	public Job findJobByEmployerId(Long empId) {
		return jobRepository.findJobByEmployerId(empId);
	}
}
