package hu.elte.joooble.controller;

import hu.elte.joooble.domain.file.EmployerLogo;
import hu.elte.joooble.domain.file.Resume;
import hu.elte.joooble.domain.user.Credential;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;
import hu.elte.joooble.repository.*;
import hu.elte.joooble.service.EmployerService;
import hu.elte.joooble.webdomain.EmployeeEditForm;
import hu.elte.joooble.webdomain.EmployeeRegisterForm;
import hu.elte.joooble.webdomain.EmployerRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class RegisterController {

    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CredentialRepository credentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployerLogoRepository employerLogoRepository;
    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/register")
    public String RegisterDecide()
    {
        return "register";
    }

    @GetMapping("/register-employer")
    public String EmployerRegisterPage()
    {
        return "register_employer";
    }

    @GetMapping("/register-employee")
    public String EmployeeRegisterPage()
    {
        return "register_employee";
    }

    @PostMapping("registrationEmployer")
    public String EmployerRegistration(@Valid EmployerRegisterForm employerRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            redirectAttributes.addFlashAttribute("employerRegisterForm", employerRegisterForm);
            return "register_employer";
        }

        Employer employer = new Employer();

        Credential credential = new Credential();
        credential.setEmail(employerRegisterForm.getEmail());
        credential.setPassword(passwordEncoder.encode(employerRegisterForm.getPassword()));
        employer.setCredential(credential);

        credentialRepository.save(credential);

        EmployerLogo logo = new EmployerLogo();

        employer.setCompanyName(employerRegisterForm.getCompanyName());
        employer.setLocation(employerRegisterForm.getCompanyAddress());
        employer.setActive(true);
        employerRepository.save(employer);

        logo.setEmployer(employer);
        logo.setFileName("");
        logo.setTitle("");
        logo.setExtension("");
        logo.setEmployer(employerRepository.findById(employer.getId()).get());
        employerLogoRepository.save(logo);
        employer.setLogo(logo);

        employerRepository.save(employer);

        return "redirect:/";
    }

    @PostMapping("registrationEmployee")
    public String EmployeeRegistration(@Valid EmployeeRegisterForm employeeRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            redirectAttributes.addFlashAttribute("employerRegisterForm", employeeRegisterForm);
            return "register_employee";
        }

        Employee employee = new Employee();

        Credential credential = new Credential();
        credential.setEmail(employeeRegisterForm.getEmail());
        credential.setPassword(passwordEncoder.encode(employeeRegisterForm.getPassword()));
        credentialRepository.save(credential);
        employee.setCredential(credential);

        employee.setBirthdate(LocalDate.parse(employeeRegisterForm.getBirthday()));
        employee.setJobs(new ArrayList<>());
        employeeRepository.save(employee);
        Resume resume = new Resume();
        resume.setEmployee(employee);
        resume.setExtension("pdf");
        resume.setTitle("Default");
        resume.setFileName("Default");
        resumeRepository.save(resume);
        employee.setResume(resume);

        employeeRepository.save(employee);

        return "redirect:/";
    }
}
