package ua.graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.entity.Cases;
import ua.entity.CasesToFind;
import ua.entity.Courts;
import ua.entity.SelectedCases;

import ua.entity.TemporaryCases;

import ua.mainLogic.ParsingTempCases;

import ua.mainLogic.VisualLogic;
import ua.repository.CaseRepository;
import ua.repository.CasesToFindRepository;

import ua.repository.SelectedCasesRepository;
import ua.repository.TemporaryCasesRepository;
import ua.tableModel.TableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@Service
public class FirstPage extends JFrame {

	private static final long serialVersionUID = 7988002197084504690L;
	@Autowired
	private ConfigurableApplicationContext run;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	public TableModel model;
	private JButton button;
	public JButton btnNewButton_1;
	private JButton button_3;

	/////////////////////////////////////////////////////////////////////
	public class DateCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

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

	public FirstPage() {

		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setTitle("Cases List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel label = new JLabel("Введіть дані сторони справи");

		textField = new JTextField();
		textField.setToolTipText("Введіть відомості для пошуку.");
		textField.setColumns(18);

		/////////////////////////////////////////////////////////////////////
		btnNewButton = new JButton("Пошук");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					String text = textField.getText();

					List<Cases> cases = null;

					if (text != null && text.trim().length() > 0) {

						cases = new VisualLogic().findElement(run, text);

					} else {

						cases = new VisualLogic().findAll(run);

					}

					model = new TableModel(cases);
					table.setModel(model);
					table.setFont(new Font("Times new roman", Font.PLAIN, 15));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

					TableColumnModel colModel = table.getColumnModel();
					colModel.getColumn(0).setPreferredWidth(100);
					colModel.getColumn(0).setCellRenderer(new DateCellRenderer());
					colModel.getColumn(1).setPreferredWidth(70);
					colModel.getColumn(2).setPreferredWidth(70);
					colModel.getColumn(3).setPreferredWidth(300);
					colModel.getColumn(4).setPreferredWidth(300);
					colModel.getColumn(5).setPreferredWidth(100);

					table.setAutoCreateRowSorter(true);

				} catch (Exception exc) {
					System.out.println("problem 1");
					exc.printStackTrace();

				}

			}
		});

		///////////////////////////////////////////////////////////////
		button = new JButton("Обрати суд");
		button.setToolTipText("Обрати суд чиї судові засідання будуть відображені.");

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					new CourtsListFrame(run);
					CourtsListFrame.getObj(run).setVisible(true);

					// CourtsListFrame.getObj(run).setVisible(true);
					// CourtsListFrame lof = new CourtsListFrame(run);
					// lof.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}

			}

		});

		////////////////////////////////////////////////////////////////////
		btnNewButton_1 = new JButton("Додати в обране\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Cases> listtt = new VisualLogic().findAll(run);
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Потрібно обрати справу!");
					return;
				}
				String cas = (String) table.getValueAt(row, 2);
				String date = table.getValueAt(row, 0).toString();
				System.out.println(cas);
				Cases casesTemp = null;
				for (Cases i : listtt) {
					if (i.getNumber().toString().equals(cas) && i.getDate().toString().equals(date)) {
						casesTemp = i;
					}
				}

				SelectedCasesRepository selCas = run.getBean(SelectedCasesRepository.class);
				List<SelectedCases> listSelecCas = new VisualLogic().findAllSelectedCases(run);
				for (SelectedCases selectedCases : listSelecCas) {
					if (selectedCases.getNumber().equals(casesTemp.getNumber())) {
						JOptionPane.showMessageDialog(null, "Дана справа вже є в переліку обраних");
						return;
					}
				}
				SelectedCases sc = new SelectedCases();
				sc.setDate(casesTemp.getDate());
				sc.setJudge(casesTemp.getJudge());
				sc.setNumber(casesTemp.getNumber());
				sc.setSides(casesTemp.getSides());
				sc.setType(casesTemp.getType());
				sc.setCourt(casesTemp.getCourt());

				selCas.save(sc);

				try {
					SelectedCasesListFrame sclf = new SelectedCasesListFrame(run);
					sclf.setVisible(true);

				} catch (Exception exc) {
					System.out.println("problem 2");
					exc.printStackTrace();
				}
			}

		});

		/////////////////////////////////////////////////////////////////////////
		JButton button_1 = new JButton("Обране");
		button_1.setToolTipText("Переглянути список обраних справ.");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// new SelectedCasesListFrame.getObj(run).setVisible(true);

					SelectedCasesListFrame sclf = new SelectedCasesListFrame(run);
					sclf.setVisible(true);

				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		/////////////////////////////////////////////////////////////////////////
		JButton button_2 = new JButton("Очистити ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaseRepository caseRep = run.getBean(CaseRepository.class);
				List<Cases> listCases = new VisualLogic().findAll(run);
				caseRep.delete(listCases);
				refresh();
			}
		});
		/////////////////////////////////////////////////////////////////////////
		button_3 = new JButton("Відслідковувати");
		button_3.setToolTipText(
				"Вказати відомості про справу, для повідомлення після того, як вона буде призначена до розгляду.");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CasesToFindFrame ctf = new CasesToFindFrame(run);
				ctf.setVisible(true);
			}
		});

		/////////////////////////////////////////////////////////////////////////
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(5).addComponent(label).addGap(5)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_2)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_3)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(button)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_1)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_1)
						.addContainerGap(112, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(9).addComponent(label))
						.addGroup(gl_panel.createSequentialGroup().addGap(5)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
										.addComponent(button_2).addComponent(button_3).addComponent(button)
										.addComponent(btnNewButton_1).addComponent(button_1)))
						.addGroup(gl_panel.createSequentialGroup().addGap(6).addComponent(textField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		//////////////////////////////////////////////////////////////////////////////

		scrollPane = new JScrollPane();
		scrollPane.setFont(scrollPane.getFont().deriveFont(scrollPane.getFont().getSize() + 5f));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.getRootPane().setDefaultButton(btnNewButton);

		// table = new JTable();
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
					// catch null pointer exception if mouse is over an empty
					// line
				}

				return tip;
			}
		};
		scrollPane.setViewportView(table);
		setLocationRelativeTo(null);

		//////////////////////////////////////////////////////////////////////////////

	}

	public void refresh() {
		try {

			List<Cases> cases = new VisualLogic().findAll(run);

			model = new TableModel(cases);
			table.setModel(model);
			table.setFont(new Font("Times new roman", Font.PLAIN, 15));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.setVisible(true);

			TableColumnModel colModel = table.getColumnModel();
			colModel.getColumn(0).setCellRenderer(new DateCellRenderer());
			colModel.getColumn(1).setPreferredWidth(70);
			colModel.getColumn(2).setPreferredWidth(50);
			colModel.getColumn(3).setPreferredWidth(200);
			colModel.getColumn(4).setPreferredWidth(400);
			colModel.getColumn(5).setPreferredWidth(100);

			table.setAutoCreateRowSorter(true);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	@Scheduled(fixedDelay = 60000)
	public void refreshHourly() {

		// ___________перевірка на перепризначення справ_____________
		System.out.println("відбувається перевірка...");

		TemporaryCasesRepository temporaryCasesRepository = run.getBean(TemporaryCasesRepository.class);
		SelectedCasesRepository selCasRepository = run.getBean(SelectedCasesRepository.class);

		List<SelectedCases> listSelectedCases = new VisualLogic().findAllSelectedCases(run);
		List<Courts> courts = new VisualLogic().findAllCourts(run);
		List<TemporaryCases> listTempCases = new VisualLogic().findAllTemporaryCases(run);

		List<CasesToFind> listCasesToFind = new VisualLogic().findAllCasesToFind(run);

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
							System.out.println("date was changed  " + selCases.getSides());
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("exception in parsing 1: First Page");
			e.printStackTrace();
		}

		// _________перевірка для списку відстежуваних справ_____________
		Courts tempCourt1 = null;
		String selectedCase = null;
		SelectedCasesRepository selCas = run.getBean(SelectedCasesRepository.class);
		CasesToFindRepository ctfr = run.getBean(CasesToFindRepository.class);

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
				System.out.println(arL);
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

						selCas.save(sc);
						selectedCase = listctf.getCasesToFind();
						JOptionPane.showMessageDialog(null, selectedCase + " Додана в обрані.");
						ctfr.delete(listctf);
					}
				}
			}

			temporaryCasesRepository.delete(listTempCases);
		} catch (Exception e) {
			System.out.println("2 First Page");
		}

	}

	/////////////////////////////////////////////////////////////////////////////

	@PostConstruct
	public void visible() {
		setVisible(true);

	}
}
