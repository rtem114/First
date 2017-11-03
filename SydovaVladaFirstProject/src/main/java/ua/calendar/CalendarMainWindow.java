package ua.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import com.mindfusion.common.DateTime;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.scheduling.CalendarAdapter;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.CustomDrawElements;
import com.mindfusion.scheduling.DateList;
import com.mindfusion.scheduling.ResourceDateEvent;
import com.mindfusion.scheduling.awt.AwtCalendar;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.scheduling.model.Item;
import com.mindfusion.scheduling.model.ItemList;

import ua.entity.SelectedCases;
import ua.mainLogic.VisualLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class CalendarMainWindow extends JFrame {

	private static final long serialVersionUID = 1740886134965368992L;

	AwtCalendar calendar;

	@Autowired
	private ConfigurableApplicationContext run;

	public CalendarMainWindow(ConfigurableApplicationContext run) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		setTitle("Календар засідань");
		BorderLayout layout = new BorderLayout();
		getContentPane().setLayout(layout);
		setLocationRelativeTo(null);
		calendar = new AwtCalendar();
		calendar.beginInit();
//		calendar.setCurrentView(CalendarView.SingleMonth);
	

		calendar.getMonthSettings().getDaySettings().setShowToday(true);
		calendar.setShowToolTips(true);

		DateList dates = calendar.getTimetableSettings().getDates();
		for (int k = 2; k < 80; k++) {
			calendar.getTimetableSettings().getDates().add(DateTime.today().addDays(k - 1));
		}
		List<SelectedCases> selCas = new VisualLogic().findAllSelectedCases(run);
		for (SelectedCases selectedCases : selCas) {
			for (DateTime dateTime : dates) {
				String one = selectedCases.getDate().toString().substring(8, 10) + "."
						+ selectedCases.getDate().toString().substring(5, 7);
				if (dateTime.toString().substring(0, 5).equals(one)) {
					Appointment item2 = new Appointment();
					item2.setStartTime(dateTime.getDate());
					item2.setEndTime(dateTime.getDate());
					if (selectedCases.getSides().startsWith("Позивач")) {
						item2.setHeaderText(selectedCases.getSides().substring(8));
					} else if (selectedCases.getSides().startsWith("Заявник")) {
						item2.setHeaderText(selectedCases.getSides().substring(8));
					} else if (selectedCases.getSides().startsWith("Обвинувачений")) {
						item2.setHeaderText(selectedCases.getSides().substring(14));
					} else if (selectedCases.getSides().startsWith("Скаржник")) {
						item2.setHeaderText(selectedCases.getSides().substring(9));
					} else if (selectedCases.getSides().startsWith("Потерпілий")) {
						item2.setHeaderText(selectedCases.getSides().substring(11));
					} else if (selectedCases.getSides().startsWith("Заінтересована особа")) {
						item2.setHeaderText(selectedCases.getSides().substring(21));
					} else if (selectedCases.getSides()
							.startsWith("Особа, яка притягається до адмін. відповідальності")) {
						item2.setHeaderText(selectedCases.getSides().substring(51));
					} else if (selectedCases.getSides().startsWith("Представник потерпілого")) {
						item2.setHeaderText(selectedCases.getSides().substring(24));
					} else if (selectedCases.getSides().startsWith("Інша особа")) {
						item2.setHeaderText(selectedCases.getSides().substring(11));
					} else if (selectedCases.getSides()
							.startsWith("Особа, стосовно якої розглядається подання, клопотання, заява")) {
						item2.setHeaderText(selectedCases.getSides().substring(62));
					}

					else {
						item2.setHeaderText(selectedCases.getSides());
						item2.setDescriptionText(selectedCases.getSides());

					}
					item2.getStyle().setBrush(Brushes.LightBlue);

					calendar.getSchedule().getItems().add(item2);

				}
			}
		}
		calendar.setLocale(getLocale());
		calendar.endInit();
		getContentPane().add(calendar, BorderLayout.CENTER);

	}
}
