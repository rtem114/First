package ua;

import java.util.List;

import javax.swing.JFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ua.entity.Courts;
import ua.mainLogic.SetCourtsToTheDatabase;
import ua.mainLogic.VisualLogic;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@EnableTransactionManagement
public class CourtsProjectApplication extends JFrame {

	private static final long serialVersionUID = -3715515255862936478L;

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(CourtsProjectApplication.class, args);

		List<Courts> listCourts = new VisualLogic().findAllCourts(run);
		if (listCourts.isEmpty()) {// список судів наповняється лише за умови,
									// що
			new SetCourtsToTheDatabase().settt(run);// він ще пустий
		}

	}
}
