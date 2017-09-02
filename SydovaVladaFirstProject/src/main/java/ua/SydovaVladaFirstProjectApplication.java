package ua;



import javax.swing.JFrame;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ua.mainLogic.Parsing;
import ua.mainLogic.VisualLogic;


@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class SydovaVladaFirstProjectApplication extends JFrame {

	
	private static final long serialVersionUID = -3090406674586814573L;

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SydovaVladaFirstProjectApplication.class, args);
		
		
//		new VisualLogic().findAll(run);

		// оформити парсінг окремою кнопкою 
//		new Parsing().parse(run);
//		 run.close();

	}

	
}
