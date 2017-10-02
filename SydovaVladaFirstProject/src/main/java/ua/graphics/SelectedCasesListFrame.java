package ua.graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import ua.entity.Cases;
import ua.entity.Courts;
import ua.entity.SelectedCases;
import ua.graphics.FirstPage.DateCellRenderer;
import ua.mainLogic.Parsing;
import ua.mainLogic.VisualLogic;
import ua.repository.SelectedCasesRepository;
import ua.tableModel.CasesToFindModel;
import ua.tableModel.CourtsTableModel;
import ua.tableModel.SelectedCasesTableModel;
import ua.tableModel.TableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class SelectedCasesListFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5401420540713032276L;
	private JPanel contentPane;
	@Autowired
	private ConfigurableApplicationContext run;
	private JTable table;
	private JScrollPane scrollPane;
	private GroupLayout gl_contentPane;
	private List<SelectedCases> listSelecCas;
	private SelectedCasesTableModel model;

	public SelectedCasesListFrame(ConfigurableApplicationContext run) {
		setLocation(new Point(200, 200));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1048, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		 listSelecCas = new VisualLogic().findAllSelectedCases(run);

		for (SelectedCases cases : listSelecCas) {
			System.out.println(cases + " selected cases");
		}

		try {
			 model = new SelectedCasesTableModel(listSelecCas);
			table = new JTable();
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
					if (cases.getNumber().equals(cor)) {
						selCasRep.delete(cases);
					}
					
				}
				new Refresh().refresh(run);
			}
		});

		JButton button = new JButton("Історія");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HistoryFrame hf = new HistoryFrame();
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

		///////////////////////////////////////////////////////////////////////////////////////////////////////
		 gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnNewButton_1)
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
								.addComponent(button).addComponent(button_1).addComponent(btnNewButton_1))
						.addContainerGap()));

		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

	}
	
	public class Refresh {

		public void refresh(ConfigurableApplicationContext run) {

			try {
				listSelecCas = new VisualLogic().findAllSelectedCases(run);
				model = new SelectedCasesTableModel(listSelecCas);

				
					table = new JTable();
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
					System.out.println(111);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			scrollPane.setViewportView(table);
			contentPane.setLayout(gl_contentPane);
		}

	}
}
