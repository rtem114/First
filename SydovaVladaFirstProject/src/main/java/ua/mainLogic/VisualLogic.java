package ua.mainLogic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.entity.Cases;
import ua.repository.CaseRepository;

@Service
public class VisualLogic {
	
	
	@Autowired
	public CaseRepository caseRepository;

	public List<Cases> findAll(ConfigurableApplicationContext run) {

		try {
			CaseRepository caseRepository = run.getBean(CaseRepository.class);
			List<Cases> listCases = caseRepository.findAll();
			for (Cases cases : listCases) {
				System.out.println(cases);
			}
			return listCases;

		} catch (Exception e) {
			e.printStackTrace();
		}
	

		return new ArrayList<>();
		
}
	
	public List<Cases> findElement(ConfigurableApplicationContext run, String text) {

		try {
			CaseRepository caseRepository = run.getBean(CaseRepository.class);
			List<Cases> listCases = caseRepository.findAll();
			for (Cases cases : listCases) {
				System.out.println(cases);
			}
			return listCases;

		} catch (Exception e) {
			e.printStackTrace();
		}
	

		return new ArrayList<>();
}
	
	
}