package hu.elte.joooble.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.elte.joooble.domain.file.EmployerLogo;
import hu.elte.joooble.repository.EmployerLogoRepository;

@Service
public class EmployerLogoService {

	@Autowired
	private EmployerLogoRepository employerLogoRepository;

	
}
