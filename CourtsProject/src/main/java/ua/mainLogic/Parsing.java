package ua.mainLogic;
 
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import ua.entity.Cases;
import ua.entity.Courts;

import ua.repository.CaseRepository;

public class Parsing {

	public int parse(ConfigurableApplicationContext run, Courts court) {

		int t = 0;
		try {

			String START_URL = court.getAdress();
			WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
			HtmlPage page = webClient.getPage(START_URL);

			webClient.getOptions().setJavaScriptEnabled(true);
			 webClient.getOptions().setCssEnabled(true);
			 webClient.getOptions().setThrowExceptionOnScriptError(false);
			 webClient.getOptions().setPrintContentOnFailingStatusCode(false);
			webClient.waitForBackgroundJavaScript(1000);

			HtmlButton button = page.getHtmlElementById("cleardate");

			button.click();
			webClient.waitForBackgroundJavaScript(1000);
			final HtmlTable table = page.getHtmlElementById("assignments");
			CaseRepository caseRepository = run.getBean(CaseRepository.class);
			DomElement buttonNext = page.getFirstByXPath("//span[@class='ui-icon ui-icon-circle-arrow-e']");

			DomElement elem = page.getElementById("assignments_info");

			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
			String strdate = null;
			Date nDate = null;

			int second = 0;
			String full = elem.asText();
			int first = Integer.valueOf(full.substring(full.indexOf("із") + 3, full.length() - 9));
			second = first / 10;

			for (int k = 0; k <= second; k++) {

				for (int i = 1; i < 11; i++) {
					if (table.getCellAt(i, 1) == null) {

						break;
					}

					Cases cas = new Cases();
					strdate = table.getCellAt(i, 0).asText();
					nDate = sdf.parse(strdate);

					cas.setDate(nDate);
					cas.setJudge(table.getCellAt(i, 1).asText());
					cas.setNumber(table.getCellAt(i, 2).asText());
					cas.setSides(table.getCellAt(i, 3).asText());
					cas.setType(table.getCellAt(i, 4).asText());
					cas.setCourt(court.getName().toString());
					caseRepository.save(cas);
					t++;
				}

				buttonNext.click();

			}
			webClient.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("No internet for parsing");
		}
		System.out.println(t);
		return t;
	}

}
