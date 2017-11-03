package ua.mainLogic;

 
import org.springframework.context.ConfigurableApplicationContext;
 

import ua.entity.Courts;
import ua.repository.CourtsRepository;

 
public class SetCourtsToTheDatabase {

 

	public void settt(ConfigurableApplicationContext run) {

		CourtsRepository cr = run.getBean(CourtsRepository.class);
		
		Courts courts1 = new Courts();
		courts1.setName("Першотравневий районний суд м.Чернівці");
		courts1.setRegion("Чернівецької області");
		courts1.setAdress("http://old.court.gov.ua/sud2408/csz/");
		cr.save(courts1);
		
		Courts courts2 = new Courts();
		courts2.setName("Садгірський районний суд м.Чернівці");
		courts2.setRegion("Чернівецької області");
		courts2.setAdress("http://old.court.gov.ua/sud2410/csz/");
		cr.save(courts2);
		
		Courts courts3 = new Courts();
		courts3.setName("Шевченківський районний суд м.Чернівці");
		courts3.setRegion("Чернівецької області");
		courts3.setAdress("http://old.court.gov.ua/sud2414/csz/");
		cr.save(courts3);
		
		Courts courts4 = new Courts();
		courts4.setName("Вижницький районний суд");
		courts4.setRegion("Чернівецької області");
		courts4.setAdress("http://old.court.gov.ua/sud2401/csz/");
		cr.save(courts4);

		Courts courts5 = new Courts();
		courts5.setName("Герцаївський районний суд");
		courts5.setRegion("Чернівецької області");
		courts5.setAdress("http://old.court.gov.ua/sud2402/csz/");
		cr.save(courts5);
		
		Courts courts6 = new Courts();
		courts6.setName("Глибоцький районний суд");
		courts6.setRegion("Чернівецької області");
		courts6.setAdress("http://old.court.gov.ua/sud2403/csz/");
		cr.save(courts6);
		
		Courts courts7 = new Courts();
		courts7.setName("Заставнівський районний суд");
		courts7.setRegion("Чернівецької області");
		courts7.setAdress("http://old.court.gov.ua/sud2404/csz/");
		cr.save(courts7);
		
		Courts courts8 = new Courts();
		courts8.setName("Кельменецький районний суд");
		courts8.setRegion("Чернівецької області");
		courts8.setAdress("http://old.court.gov.ua/sud2405/csz/");
		cr.save(courts8);
		
		Courts courts9 = new Courts();
		courts9.setName("Кіцманський районний суд");
		courts9.setRegion("Чернівецької області");
		courts9.setAdress("http://old.court.gov.ua/sud2406/csz/");
		cr.save(courts9);
		
		Courts courts10 = new Courts();
		courts10.setName("Новоселицький районний суд");
		courts10.setRegion("Чернівецької області");
		courts10.setAdress("http://old.court.gov.ua/sud2407/spisok/csz/");  
		cr.save(courts10);
		
		Courts courts11 = new Courts();
		courts11.setName("Путильський районний суд");
		courts11.setRegion("Чернівецької області");
		courts11.setAdress("http://old.court.gov.ua/sud2409/csz/");
		cr.save(courts11);
		
		Courts courts12 = new Courts();
		courts12.setName("Сокирянський районний суд");
		courts12.setRegion("Чернівецької області");
		courts12.setAdress("http://old.court.gov.ua/sud2411/csz/");
		cr.save(courts12);
		
		Courts courts13 = new Courts();
		courts13.setName("Сторожинецький районний суд");
		courts13.setRegion("Чернівецької області");
		courts13.setAdress("http://old.court.gov.ua/sud2412/csz/");
		cr.save(courts13);
		
		Courts courts14 = new Courts();
		courts14.setName("Хотинський районний суд");
		courts14.setRegion("Чернівецької області");
		courts14.setAdress("http://old.court.gov.ua/sud2413/csz/");
		cr.save(courts14);
		
		Courts courts15 = new Courts();
		courts15.setName("Чернівецький окружний адміністративний суд");
		courts15.setRegion(" ");
		courts15.setAdress("http://old.court.gov.ua/sud2470/4/csz/");
		cr.save(courts15);
		
		Courts courts16 = new Courts();
		courts16.setName("Апеляційний суд");
		courts16.setRegion("Чернівецької області");
		courts16.setAdress("http://old.court.gov.ua/sud2490/35875428739652379465923695/csz/");
		cr.save(courts16);
		
		Courts courts17 = new Courts();
		courts17.setName("Новодністровський міський суд");
		courts17.setRegion("Чернівецької області");
		courts17.setAdress("http://old.court.gov.ua/sud2415/csz/");
		cr.save(courts17);
		
		Courts courts18 = new Courts();
		courts18.setName("Господарський суд");
		courts18.setRegion("Чернівецької області");
		courts18.setAdress("http://old.court.gov.ua/sud5027/spysky/csz/");
		cr.save(courts18);
		
	}

}
