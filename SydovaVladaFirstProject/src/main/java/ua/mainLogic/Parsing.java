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
import ua.repository.CaseRepository;

public class Parsing {

	public void parse(ConfigurableApplicationContext run) {

		try {

			String START_URL = "http://old.court.gov.ua/sud2415/csz/";
			WebClient webClient = new WebClient(BrowserVersion.CHROME);
			HtmlPage page = webClient.getPage(START_URL);

			webClient.getOptions().setJavaScriptEnabled(true);
			// webClient.getOptions().setCssEnabled(true);
			// webClient.getOptions().setThrowExceptionOnScriptError(false);
			// webClient.getOptions().setPrintContentOnFailingStatusCode(false);
			webClient.waitForBackgroundJavaScript(3000);

			HtmlButton button = page.getHtmlElementById("cleardate");

			button.click();
			webClient.waitForBackgroundJavaScript(3000);
			final HtmlTable table = page.getHtmlElementById("assignments");
			CaseRepository caseRepository = run.getBean(CaseRepository.class);
			DomElement buttonNext = page.getFirstByXPath("//span[@class='ui-icon ui-icon-circle-arrow-e']");
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
			String strdate = null;
			boolean flag = true;

			while (flag) {
				for (int i = 1; i < 11; i++) {
					if (table.getCellAt(i, 1) == null) {

						flag = false;
						break;
					}
					Cases cas = new Cases();
					strdate = table.getCellAt(i, 0).asText();
					Date newDate = sdf.parse(strdate);
					cas.setDate(newDate);
					cas.setJudge(table.getCellAt(i, 1).asText());
					cas.setNumber(table.getCellAt(i, 2).asText());
					cas.setSides(table.getCellAt(i, 3).asText());
					cas.setType(table.getCellAt(i, 4).asText());
					caseRepository.save(cas);
				}

				buttonNext.click();

			}
			webClient.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

}
