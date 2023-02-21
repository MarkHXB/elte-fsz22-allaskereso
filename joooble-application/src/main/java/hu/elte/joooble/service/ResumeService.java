package hu.elte.joooble.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.elte.joooble.domain.file.Resume;
import hu.elte.joooble.repository.EmployeeRepository;
import hu.elte.joooble.repository.ResumeRepository;

@Service
public class ResumeService  {
	@Autowired
	private ResumeRepository resumeRepository;

	public Iterable<Resume> findAllResume(){
		return resumeRepository.findAll();
	}
	
	public Resume editResume(Long employeeId,String resumeTitle) {
		Resume resume = resumeRepository.findByEmployeeId(employeeId);
		
		resume.setTitle(resumeTitle);

		return resumeRepository.save(resume);
	}
}
