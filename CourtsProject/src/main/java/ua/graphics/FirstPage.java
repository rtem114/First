package ua.graphics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.calendar.CalendarMainWindow;
import ua.entity.CasesToFind;
import ua.entity.Courts;
import ua.entity.SelectedCases;

import ua.entity.TemporaryCases;

import ua.mainLogic.ParsingTempCases;
import ua.mainLogic.VisualLogic;
import ua.repository.CasesToFindRepository;

import ua.repository.SelectedCasesRepository;
import ua.repository.TemporaryCasesRepository;
import ua.tableModel.SelectedCasesTableModel;
import java.awt.Toolkit;

@Service
public class FirstPage extends JFrame {

	private static final long serialVersionUID = 1766207890278232267L;

	@Autowired
	private ConfigurableApplicationContext run;

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private GroupLayout gl_contentPane;
	private SelectedCasesTableModel model;
	JButton btnNewButton;
	private CasesSearchDialog tcsd;
	private JButton notatku;
	private JButton button_1;
	private JButton button_2;
	private JButton btnNewButton_1;
	private JButton button;

	public class DateCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1121024788522231885L;
		private SimpleDateFormat sdfNewValue = new SimpleDateFormat("dd.MM.YYYY HH:mm");

		@Override
		public void setValue(Object value) {
			try {
				if (value != null)
					value = sdfNewValue.format(value);
			} catch (IllegalArgumentException e) {
			}

			super.setValue(value);
		}
	}

	public FirstPage(ConfigurableApplicationContext run) {
		setTitle("Судовий помічник ");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FirstPage.class.getResource("/ua/projectResources/icon.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 573);
		contentPane = new JPanel();

		contentPane.setBorder(null);
		setContentPane(contentPane);

		try {

			List<SelectedCases> listSelecCas = new VisualLogic().findAllSelectedCases(run);
			model = new SelectedCasesTableModel(listSelecCas);
			// наступний блок дає можливість переглядати при наведенні курсора
			table = new JTable() {

				private static final long serialVersionUID = -5080089725504838593L;

				// Implement table cell tool tips.
				public String getToolTipText(MouseEvent e) {
					String tip = null;
					java.awt.Point p = e.getPoint();
					int rowIndex = rowAtPoint(p);
					int colIndex = columnAtPoint(p);
					try {
						tip = getValueAt(rowIndex, colIndex).toString();
					} catch (RuntimeException e1) {
					}
					return tip;
				}
			};

			table.setModel(model);
			table.setFont(new Font("Times new roman", Font.PLAIN, 15));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.setAutoCreateRowSorter(true);

			TableColumnModel colModel = table.getColumnModel();
			colModel.getColumn(0).setPreferredWidth(90);
			colModel.getColumn(0).setCellRenderer(new DateCellRenderer());
			colModel.getColumn(1).setPreferredWidth(70);
			colModel.getColumn(2).setPreferredWidth(70);
			colModel.getColumn(3).setPreferredWidth(250);
			colModel.getColumn(4).setPreferredWidth(250);
			colModel.getColumn(5).setPreferredWidth(100);

			table.setAutoCreateRowSorter(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		scrollPane = new JScrollPane();

		btnNewButton = new JButton("Пошук");
		btnNewButton.setToolTipText("Пошук справи серед вже призначених. ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tcsd = new CasesSearchDialog(run, new FirstPage(run));
				if (!tcsd.isVisible()) {
					tcsd.setVisible(true);

					model = new SelectedCasesTableModel(tcsd.getAllCasesFromDialog(run));
					table.setModel(model);
					table.setFont(new Font("Times new roman", Font.PLAIN, 15));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
					table.setVisible(true);

					TableColumnModel colModel = table.getColumnModel();
					colModel.getColumn(0).setPreferredWidth(90);
					colModel.getColumn(0).setCellRenderer(new FirstPage(run).new DateCellRenderer());
					colModel.getColumn(1).setPreferredWidth(70);
					colModel.getColumn(2).setPreferredWidth(70);
					colModel.getColumn(3).setPreferredWidth(250);
					colModel.getColumn(4).setPreferredWidth(250);
					colModel.getColumn(5).setPreferredWidth(100);

					table.setAutoCreateRowSorter(true);
				}

			}
		});

		btnNewButton_1 = new JButton("Видалити ");
		btnNewButton_1.setToolTipText("Видалити обрану справу.");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Потрібно обрати справу!");
					return;
				}

				SelectedCasesRepository selCasRep = run.getBean(SelectedCasesRepository.class);
				List<SelectedCases> listSelecCas = new VisualLogic().findAllSelectedCases(run);
				for (SelectedCases cases : listSelecCas) {
					String cor = (String) table.getValueAt(row, 2);
					String date = table.getValueAt(row, 0).toString();
					if (cases.getNumber().equals(cor) && cases.getDate().toString().equals(date)) {
						selCasRep.delete(cases);
					}

				}
				new Refresh().refresh(run);
			}
		});

		button = new JButton("Відслідкувати");
		button.setToolTipText(
				"Почати відслідковувати справу, яка ще не призначена. ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CasesToFindFrame ctf = new CasesToFindFrame(run);
				ctf.setVisible(true);

			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////////////

		notatku = new JButton("Нотатки");
		notatku.setToolTipText("Створити нотатку для обраної справи. ");
		notatku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<SelectedCases> listSelecCas = new VisualLogic().findAllSelectedCases(run);
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Потрібно обрати справу!");
					return;
				}
				SelectedCases tempCases = null;
				for (SelectedCases cases : listSelecCas) {
					String cor = (String) table.getValueAt(row, 2);
					if (cases.getNumber().equals(cor)) {
						tempCases = cases;
					}
				}

				MemoFrame mf = new MemoFrame(run, tempCases);

				mf.setVisible(true);

			}
		});

		button_2 = new JButton("Календар");
		button_2.setToolTipText("Відкрити календар.");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarMainWindow cmw = new CalendarMainWindow(run);
				cmw.setVisible(true);
			}
		});

		button_1 = new JButton("Очистити");
		button_1.setToolTipText("Видалити всі обрані справи.");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<SelectedCases> listSelecCas = new VisualLogic().findAllSelectedCases(run);
				SelectedCasesRepository selCasRep = run.getBean(SelectedCasesRepository.class);
				listSelecCas = new VisualLogic().findAllSelectedCases(run);

				selCasRep.delete(listSelecCas);

				new Refresh().refresh(run);

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(10, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 931, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnNewButton_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_2).addGap(97)
								.addComponent(notatku).addGap(6).addComponent(button))
						.addComponent(btnNewButton))
				.addGap(13)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(btnNewButton).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE).addGap(11)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(button_2)
								.addComponent(button_1).addComponent(btnNewButton_1))
						.addComponent(notatku).addComponent(button))));
		contentPane.setLayout(gl_contentPane);

		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(button_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_2)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(notatku)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE))
						.addGap(13)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(26)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(button).addComponent(notatku).addComponent(btnNewButton_1)
								.addComponent(button_2).addComponent(button_1))
						.addContainerGap()));

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public class Refresh {

		public void refresh(ConfigurableApplicationContext run) {
			List<SelectedCases> listSelecCas = new VisualLogic().findAllSelectedCases(run);
			try {
				listSelecCas = new VisualLogic().findAllSelectedCases(run);
				model = new SelectedCasesTableModel(listSelecCas);

				table = new JTable() {

					private static final long serialVersionUID = -5080089725504838593L;

					// Implement table cell tool tips.
					public String getToolTipText(MouseEvent e) {
						String tip = null;
						java.awt.Point p = e.getPoint();
						int rowIndex = rowAtPoint(p);
						int colIndex = columnAtPoint(p);

						try {
							tip = getValueAt(rowIndex, colIndex).toString();
						} catch (RuntimeException e1) {
							// catch null pointer exception if mouse is over an
							// empty
							// line
						}

						return tip;
					}
				};
				table.setModel(model);
				table.setFont(new Font("Times new roman", Font.PLAIN, 15));
				table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				table.setAutoCreateRowSorter(true);

				TableColumnModel colModel = table.getColumnModel();
				colModel.getColumn(0).setPreferredWidth(90);
				colModel.getColumn(0).setCellRenderer(new FirstPage(run).new DateCellRenderer());
				colModel.getColumn(1).setPreferredWidth(70);
				colModel.getColumn(2).setPreferredWidth(70);
				colModel.getColumn(3).setPreferredWidth(250);
				colModel.getColumn(4).setPreferredWidth(250);
				colModel.getColumn(5).setPreferredWidth(100);

				table.setAutoCreateRowSorter(true);

			} catch (Exception exc) {
				exc.printStackTrace();
			}
			scrollPane.setViewportView(table);
			contentPane.setLayout(gl_contentPane);
			// setLocationRelativeTo(null);
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)

					.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10).addGroup(gl_contentPane
									.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
											.createSequentialGroup().addGap(264).addGroup(gl_contentPane
													.createParallelGroup(Alignment.TRAILING)
													.addGroup(gl_contentPane.createSequentialGroup().addGap(607))
													.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(button_1).addGap(124)))
											.addComponent(button_2).addGap(6).addComponent(btnNewButton_1).addGap(6)
											.addComponent(notatku).addGap(6).addComponent(button).addGap(6)
											.addComponent(btnNewButton))
									.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
											.addGap(274)))
							.addGap(13)));
			gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup().addGap(26)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup().addGap(11)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)

													.addComponent(button_2).addComponent(btnNewButton_1)
													.addComponent(notatku).addComponent(button)
													.addComponent(btnNewButton)))
									.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_1)))
							.addGap(11)));
			contentPane.setLayout(gl_contentPane);
			gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup().addComponent(button_1)
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_2)
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_1)
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(notatku)
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(button)
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton))
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE))
							.addGap(13)));
			gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup().addGap(26)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
									.addComponent(button).addComponent(notatku).addComponent(btnNewButton_1)
									.addComponent(button_2).addComponent(button_1))
							.addContainerGap()));

			scrollPane.setViewportView(table);
			contentPane.setLayout(gl_contentPane);
		}

	}

	@Scheduled(fixedDelay = 60000)
	public void refreshHourly() {

		// ___________перевірка на перепризначення справ_____________
		System.out.println("відбувається перевірка...");

		TemporaryCasesRepository temporaryCasesRepository = run.getBean(TemporaryCasesRepository.class);
		SelectedCasesRepository selCasRepository = run.getBean(SelectedCasesRepository.class);
		List<SelectedCases> listSelectedCases = new VisualLogic().findAllSelectedCases(run);
		List<Courts> courts = new VisualLogic().findAllCourts(run);
		List<TemporaryCases> listTempCases = new VisualLogic().findAllTemporaryCases(run);

		ArrayList<Courts> arList = new ArrayList<Courts>();
		Courts tempCourt = null;

		for (Courts court : courts) {
			for (SelectedCases selCases : listSelectedCases) {
				if (selCases != null) {
					if (!court.equals(tempCourt)) {
						if (selCases.getCourt().equals(court.getName())) {
							arList.add(court);
							tempCourt = court;
						}
					}
				} else {
					System.out.println("list selected cases is empty");
				}
			}
		}
		try {
			for (Courts courts2 : arList) {
				new ParsingTempCases().parse(run, courts2);
				System.out.println("Парсінг для перевірки перепризначення справ " + courts2.getName());
			}
			for (TemporaryCases tempCases : listTempCases) {
				for (SelectedCases selCases : listSelectedCases) {

					if (tempCases.getNumber().equals(selCases.getNumber())
							&& !tempCases.getDate().equals(selCases.getDate())) {
						System.out.println("знайдено розбіжності в датах справи : " + selCases.getSides());

						if (tempCases.getDate().after(selCases.getDate())) {
							selCases.setDate(tempCases.getDate());
							selCasRepository.save(selCases);
							System.out.println("date was changed " + selCases.getSides());
							JOptionPane.showMessageDialog(null, selCases.getSides()+ " перепризначена на " + tempCases.getDate().toString().substring(8, 10)
									+"."+ tempCases.getDate().toString().substring(5, 7)
									+"."+ tempCases.getDate().toString().substring(0, 4)
									+ " року");
							new Refresh().refresh(run);
						}
					}
				}
			}
			temporaryCasesRepository.delete(listTempCases);
		} catch (Exception e) {
			System.out.println("exception in parsing 1: First Page");
			e.printStackTrace();
		}

		// _________перевірка для списку відстежуваних справ_____________
		Courts tempCourt1 = null;
		String selectedCase = null;
		SelectedCasesRepository selCasRep = run.getBean(SelectedCasesRepository.class);
		CasesToFindRepository ctfr = run.getBean(CasesToFindRepository.class);
		TemporaryCasesRepository temporaryCasesRepository2 = run.getBean(TemporaryCasesRepository.class);
		
		List<CasesToFind> listCasesToFind = new VisualLogic().findAllCasesToFind(run);

		ArrayList<Courts> arList1 = new ArrayList<Courts>();

		for (CasesToFind casToFind : listCasesToFind) {
			for (Courts court : courts) {
				if (casToFind != null) {
					if (casToFind.getCourt().equals(court.getName())) {

						if (court.equals(tempCourt1)) {

						} else {

							arList1.add(court);
							System.out.println(court);
						}
						tempCourt1 = court;
					}
				}
			}
		}
		try {
			for (Courts arL : arList1) {
				new ParsingTempCases().parse(run, arL);
				System.out.println(arL.getName());
				System.out.println("Парсінг для перевірки призначення справ із списку відстежуваних");
			}

			for (TemporaryCases tempCases : listTempCases) {
				for (CasesToFind listctf : listCasesToFind) {
					if (tempCases.getSides().toLowerCase().contains(listctf.getCasesToFind().toLowerCase())
							&& tempCases.getCourt().toLowerCase().equals(listctf.getCourt().toLowerCase())) {
						SelectedCases sc = new SelectedCases();
						sc.setDate(tempCases.getDate());
						sc.setJudge(tempCases.getJudge());
						sc.setNumber(tempCases.getNumber());
						sc.setSides(tempCases.getSides());
						sc.setType(tempCases.getType());
						sc.setCourt(tempCases.getCourt());

						selCasRep.save(sc);
						selectedCase = listctf.getCasesToFind();
						JOptionPane.showMessageDialog(null, selectedCase + " Додана в обрані.");
						ctfr.delete(listctf);
						new Refresh().refresh(run);
					}
				}
			}

			temporaryCasesRepository2.delete(listTempCases);
		} catch (Exception e) {
			System.out.println("2 First Page");
		}

	}

	@PostConstruct
	public void visible() {
		setVisible(true);

	}
}
