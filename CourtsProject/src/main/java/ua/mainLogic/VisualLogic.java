package ua.mainLogic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import ua.entity.Cases;
import ua.entity.CasesToFind;
import ua.entity.Courts;
import ua.entity.SelectedCases;
import ua.entity.SelectedCasesHistory;
import ua.entity.TemporaryCases;
import ua.repository.CaseRepository;
import ua.repository.CasesToFindRepository;
import ua.repository.CourtsRepository;
import ua.repository.SelectedCasesHistoryRepository;
import ua.repository.SelectedCasesRepository;
import ua.repository.TemporaryCasesRepository;

@Service
public class VisualLogic {

	

	public List<Cases> findAll(ConfigurableApplicationContext run) {

		try {
			CaseRepository caseRepository = run.getBean(CaseRepository.class);
			List<Cases> listCases = caseRepository.findAll();
			
			return listCases;

		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return new ArrayList<>();

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	public List<Cases> findElement(ConfigurableApplicationContext run, String text) {

		try {
			CaseRepository caseRepository = run.getBean(CaseRepository.class);

			List<Cases> listCases = caseRepository.findAll();
			List<Cases> listCases2 = new ArrayList<Cases>();
			for (Cases cases : listCases) {
				if (cases.getSides().toString().toLowerCase().contains(text.toLowerCase())
						|| cases.getJudge().toString().toLowerCase().contains(text.toLowerCase())
						|| cases.getType().toString().toLowerCase().contains(text.toLowerCase())
						|| cases.getNumber().toString().toLowerCase().contains(text.toLowerCase())
						|| cases.getCourt().toString().toLowerCase().contains(text.toLowerCase())) {
					listCases2.add(cases);
					
				}
			}

			return listCases2;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	/////////////////////////////////////////////////////////////////////////////////////////
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

	/////////////////////////////////////////////////////////////////////////////////////////
	public List<SelectedCases> findAllSelectedCases(ConfigurableApplicationContext run) {

		try {
			SelectedCasesRepository selCasRepos = run.getBean(SelectedCasesRepository.class);
			List<SelectedCases> listSelecCas = selCasRepos.findAll();

			return listSelecCas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	public List<TemporaryCases> findAllTemporaryCases(ConfigurableApplicationContext run) {

		try {
			TemporaryCasesRepository tempCaseRepository = run.getBean(TemporaryCasesRepository.class);
			List<TemporaryCases> listCases = tempCaseRepository.findAll();

			return listCases;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	public List<CasesToFind> findAllCasesToFind(ConfigurableApplicationContext run) {

		try {
			CasesToFindRepository casesToFindRepository = run.getBean(CasesToFindRepository.class);
			List<CasesToFind> listCasesToFind = casesToFindRepository.findAll();

			return listCasesToFind;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	public List<SelectedCasesHistory> findAllSelectedCasesHistory(ConfigurableApplicationContext run) {

		try {
			SelectedCasesHistoryRepository selectedCasesHistoryRepository = run.getBean(SelectedCasesHistoryRepository.class);
			List<SelectedCasesHistory> listCasesHistory = selectedCasesHistoryRepository.findAll();

			return listCasesHistory;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

}
