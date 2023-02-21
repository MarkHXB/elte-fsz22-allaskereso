package hu.elte.joooble.controller;

import hu.elte.joooble.domain.file.EmployerLogo;
import hu.elte.joooble.domain.file.Resume;
import hu.elte.joooble.domain.job.Job;
import hu.elte.joooble.domain.job.JobStatusEnum;
import hu.elte.joooble.domain.user.Employee;
import hu.elte.joooble.domain.user.Employer;
import hu.elte.joooble.domain.user.Admin;
import hu.elte.joooble.repository.EmployeeRepository;
import hu.elte.joooble.repository.EmployerRepository;
import hu.elte.joooble.security.SecurityHelper;
import hu.elte.joooble.service.*;
import hu.elte.joooble.storage.StorageService;
import hu.elte.joooble.webdomain.EmployeeEditForm;
import hu.elte.joooble.webdomain.EmployeeView;
import hu.elte.joooble.webdomain.EmployerEditForm;
import hu.elte.joooble.webdomain.EmployerView;
import hu.elte.joooble.webdomain.AdminView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FileService fileService;
    @Autowired
    private JobService jobService;
    @Autowired
    private EmployeeJobService employeeJobService;

    private final StorageService storageService;

    @Autowired
    public UserController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/profile")
    public String profile(EmployeeEditForm employeeEditForm, EmployerEditForm employerEditForm, Model model) {
        String userType = SecurityHelper.isEmployer() ? "employer" : "employee";

        model.addAttribute("userType", userType);

        if (userType == "employer") {
            model.addAttribute("employerView", getEmployerView());
        } else if (userType == "employee") {
            model.addAttribute("employeeView", getEmployeeView());
        }
        //else if(userType == "admin"){
           // model.addAttribute("adminView", getAdminView());
        //}

        return "profile";
    }

    public EmployeeView getEmployeeView() {
        Employee emp = employeeService.findEmployeeByCredentialEmail(SecurityHelper.getUsername());

        String resumeFileName = emp.getResume().getFileName()+"."+emp.getResume().getExtension();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        Iterable<Job> appliedJobList = employeeJobService.findAllJobsByEmployeeIdAndJobStatus(emp.getId(), JobStatusEnum.APPLIED);
        Iterable<Job> hiredJobList = employeeJobService.findAllJobsByEmployeeIdAndJobStatus(emp.getId(), JobStatusEnum.HIRED);
        Iterable<Job> rejectedJobList = employeeJobService.findAllJobsByEmployeeIdAndJobStatus(emp.getId(), JobStatusEnum.REJECTED);

        EmployeeView view = new EmployeeView(emp.getId().toString(), emp.getCredential().getEmail(), formatter.format(emp.getBirthdate()), emp.getResume().getTitle(),resumeFileName,appliedJobList,hiredJobList,rejectedJobList);

        return view;
    }

    public EmployerView getEmployerView() {
        Employer emp = employerService.findEmployerByCredentialEmail(SecurityHelper.getUsername());

        Path logoPath = storageService.load(emp.getLogo().getFileName());
        String absoluteLogoPath = fileService.getFileAbsolutePath(logoPath);
        List<Job> activeJobs = jobService.findAllActiveJobsByEmployerId(emp.getId());
        List<Job> inActiveJobs = jobService.findAllInActiveJobsByEmployerId(emp.getId());

        EmployerView view = new EmployerView(emp.getId().toString(), emp.getCompanyName(), emp.getLocation(), emp.getCredential().getEmail(), activeJobs, inActiveJobs, emp.getLogo().getTitle(),absoluteLogoPath);

        return view;
    }

    //public AdminView getAdminView(){
        //Admin administrator =
        //AdminView view = new AdminView();
        //return view;
    //}

    @GetMapping("/profile/employee-update")
    public String employeeChangeGet(EmployeeEditForm employeeEditForm, @ModelAttribute("employeeEditForm") final EmployeeEditForm employeeEditFormChanged, Model model) {
        if (model.asMap().containsKey("employeeEditForm")) {
            model.addAttribute("org.springframework.validation.BindingResult.employeeEditForm", model.asMap().get("bindingResult"));
        }
        model.addAttribute("employeeView", getEmployeeView());

        return "employeeEdit";
    }

    @PostMapping("/profile/employee-update")
    public String employeeChangePost(@Valid EmployeeEditForm employeeEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            redirectAttributes.addFlashAttribute("employeeEditForm", employeeEditForm);
            return "redirect:/profile/employee-update";
        }

        Employee emp = employeeService.findEmployeeByCredentialEmail(SecurityHelper.getUsername());
        Resume resume = new Resume();
        resume.setTitle(employeeEditForm.getFilename());
        resume.setExtension(fileService.extractExtension(employeeEditForm.getFile().getOriginalFilename()));
        resume.setFileName(employeeEditForm.getFile().getOriginalFilename());
        employeeService.saveEmployee(emp.getId(), employeeEditForm.getEmail(), employeeEditForm.getPassword(), resume);
        storageService.store(employeeEditForm.getFile());

        redirectAttributes.addFlashAttribute("success", "You have changed your personal information successfully!");

        return "redirect:/profile";
    }

    @GetMapping("/profile/employer-update")
    public String employerChangeGet(EmployerEditForm employerEditForm, @ModelAttribute("employerEditForm") final EmployerEditForm employerEditFormChanged, Model model) {
        if (model.asMap().containsKey("employerEditForm")) {
            model.addAttribute("org.springframework.validation.BindingResult.employerEditForm", model.asMap().get("bindingResult"));
        }
        model.addAttribute("employerView", getEmployerView());

        return "employerEdit";
    }

    @PostMapping("/profile/employer-update")
    public String employerChangePost(@Valid EmployerEditForm employerEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            redirectAttributes.addFlashAttribute("employerEditForm", employerEditForm);
            return "redirect:/profile/employer-update";
        }

        Employer emp = employerService.findEmployerByCredentialEmail(SecurityHelper.getUsername());
        EmployerLogo logo = new EmployerLogo();
        logo.setTitle(employerEditForm.getFilename());
        logo.setExtension(fileService.extractExtension(employerEditForm.getFile().getOriginalFilename()));
        logo.setFileName(employerEditForm.getFile().getOriginalFilename());
        employerService.saveEmployer(emp.getId(), employerEditForm.getEmail(), employerEditForm.getPassword(),employerEditForm.getCompanyName(),employerEditForm.getCompanyAddress(),logo,emp.getJobs());
        storageService.store(employerEditForm.getFile());

        redirectAttributes.addFlashAttribute("success", "You have changed your personal information successfully!");

        return "redirect:/profile";
    }
}
