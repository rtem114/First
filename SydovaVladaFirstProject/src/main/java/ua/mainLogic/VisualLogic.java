package ua.mainLogic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.entity.Cases;
import ua.entity.Courts;
import ua.repository.CaseRepository;
import ua.repository.CourtsRepository;

@Service
public class VisualLogic {
	@Autowired
	public CourtsRepository courtRepository;

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
			List<Cases> listCases2 = new ArrayList<Cases>();
			for (Cases cases : listCases) {
				if (cases.getSides().toString().toLowerCase().contains(text.toLowerCase())
						|| cases.getJudge().toString().toLowerCase().contains(text.toLowerCase())
						|| cases.getType().toString().toLowerCase().contains(text.toLowerCase())
						|| cases.getNumber().toString().toLowerCase().contains(text.toLowerCase())) {
					listCases2.add(cases);
					System.out.println(cases);
				}
			}

			return listCases2;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	public List<Courts> findAllCourts(ConfigurableApplicationContext run) {

		try {
			CourtsRepository courtRepository = run.getBean(CourtsRepository.class);

			List<Courts> listCourts = courtRepository.findAll();
		

			return listCourts;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

}
