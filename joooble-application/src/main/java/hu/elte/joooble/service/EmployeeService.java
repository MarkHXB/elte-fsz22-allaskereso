package hu.elte.joooble.service;

import hu.elte.joooble.domain.file.Resume;
import hu.elte.joooble.domain.job.*;
import hu.elte.joooble.domain.user.Credential;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;
import hu.elte.joooble.repository.*;
import hu.elte.joooble.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StorageService storageService;


    public Employee findEmployeeByCredentialEmail(String email){
        return employeeRepository.findEmployeeByCredential_Email(email);
    }

    public void saveEmployee(Long id, String email, String password, Resume resume){
        Employee emp = findEmployeeById(id);
        Credential credential = emp.getCredential();
        credential.setEmail(email);
        credential.setPassword(passwordEncoder.encode(password));
        credentialRepository.save(credential);
        emp.setCredential(credential);
        resumeRepository.save(resume);
        emp.setResume(resume);
        employeeRepository.save(emp);
        resume.setEmployee(emp);
        resumeRepository.save(resume);
    }

    public Iterable<Resume> findAllResumesByEmployeeId(Long empId){
    	return resumeRepository.findAllByEmployeeId(empId);
    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    
    public Iterable<Employee> findAllEmployees(){return employeeRepository.findAll();}
    
    public Employee findEmployeeById(Long id) {
    	return employeeRepository.findById(id).get();
    }


}
