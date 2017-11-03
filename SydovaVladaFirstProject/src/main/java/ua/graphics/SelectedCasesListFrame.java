package ua.graphics;

import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.calendar.CalendarMainWindow;
import ua.entity.SelectedCases;
import ua.mainLogic.VisualLogic;
import ua.repository.SelectedCasesRepository;
import ua.tableModel.SelectedCasesTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
@Service
public class SelectedCasesListFrame extends JFrame {

	private static final long serialVersionUID = -5401420540713032276L;
	private JPanel contentPane;
	@Autowired
	private ConfigurableApplicationContext run;
	private JTable table;
	private JScrollPane scrollPane;
	private GroupLayout gl_contentPane;
	private List<SelectedCases> listSelecCas;
	private SelectedCasesTableModel model;
	private static SelectedCasesListFrame obj = null;

	public SelectedCasesListFrame(ConfigurableApplicationContext run) {
		
			
		
		setLocation(new Point(200, 200));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1048, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		listSelecCas = new VisualLogic().findAllSelectedCases(run);

		try {
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
			colModel.getColumn(0).setPreferredWidth(100);
			colModel.getColumn(0).setCellRenderer(new FirstPage().new DateCellRenderer());
			colModel.getColumn(1).setPreferredWidth(70);
			colModel.getColumn(2).setPreferredWidth(70);
			colModel.getColumn(3).setPreferredWidth(300);
			colModel.getColumn(4).setPreferredWidth(300);
			colModel.getColumn(5).setPreferredWidth(100);

			table.setAutoCreateRowSorter(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		scrollPane = new JScrollPane();

		JButton btnNewButton = new JButton("Вихід");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		JButton btnNewButton_1 = new JButton("Видалити ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Потрібно обрати справу!");
					return;
				}

				SelectedCasesRepository selCasRep = run.getBean(SelectedCasesRepository.class);
				listSelecCas = new VisualLogic().findAllSelectedCases(run);
				for (SelectedCases cases : listSelecCas) {
					String cor = (String) table.getValueAt(row, 2);
					String date = table.getValueAt(row, 0).toString();
					if (cases.getNumber().equals(cor)
							&& cases.getDate().toString().equals(date)) {
						selCasRep.delete(cases);
					}

				}
				new Refresh().refresh(run);
			}
		});

		JButton button = new JButton("Історія");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

				HistoryFrame hf = new HistoryFrame(run, tempCases);
				hf.setVisible(true);
			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////////////

		JButton button_1 = new JButton("Примітки");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

		JButton button_2 = new JButton("Календар");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarMainWindow cmw = new CalendarMainWindow(run);
				cmw.setVisible(true);
			}
		});

		JButton button_3 = new JButton("Очистити");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SelectedCasesRepository selCasRep = run.getBean(SelectedCasesRepository.class);
				listSelecCas = new VisualLogic().findAllSelectedCases(run);

				selCasRep.delete(listSelecCas);

				new Refresh().refresh(run);

			}
		});

		///////////////////////////////////////////////////////////////////////////////////////////////////////
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(button_3)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_2)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(button)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE))
						.addGap(13)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(26)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(button).addComponent(button_1).addComponent(btnNewButton_1)
								.addComponent(button_2).addComponent(button_3))
						.addContainerGap()));

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}

//	public static SelectedCasesListFrame getObj(ConfigurableApplicationContext run) {
//		// Сінглтон для одиничного відкривання вікна
//		if (obj == null) {
//			obj = new SelectedCasesListFrame(run);
//		}
//		return obj;
//	}

	public class Refresh {

		public void refresh(ConfigurableApplicationContext run) {

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
				colModel.getColumn(0).setPreferredWidth(100);
				colModel.getColumn(0).setCellRenderer(new FirstPage().new DateCellRenderer());
				colModel.getColumn(1).setPreferredWidth(70);
				colModel.getColumn(2).setPreferredWidth(70);
				colModel.getColumn(3).setPreferredWidth(300);
				colModel.getColumn(4).setPreferredWidth(300);
				colModel.getColumn(5).setPreferredWidth(100);

				table.setAutoCreateRowSorter(true);

			} catch (Exception exc) {
				exc.printStackTrace();
			}
			scrollPane.setViewportView(table);
			contentPane.setLayout(gl_contentPane);
			setLocationRelativeTo(null);
		}

	}
}
