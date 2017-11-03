package ua.graphics;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import ua.entity.Cases;
import ua.entity.SelectedCases;

import ua.mainLogic.VisualLogic;
import ua.repository.CaseRepository;
import ua.repository.SelectedCasesRepository;
import ua.tableModel.TableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class CasesSearchDialog extends JDialog {

	private static final long serialVersionUID = 3244030407378017053L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table;
	public TableModel model;
	public JButton btnNewButton_1;


	private JButton test = new JButton("Обрати суд");
	private CourtsListDialog td ;
	private JButton button_2;

	public CasesSearchDialog(ConfigurableApplicationContext run,JFrame frame) {
		super (frame,  true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CasesSearchDialog.class.getResource("/ua/projectResources/searching-magnifying-glass.png")));
		setBounds(100, 100, 970, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		td = new CourtsListDialog(run, this);
		
		JLabel label = new JLabel("Введіть дані сторони справи");
		
		textField = new JTextField();
		textField.setToolTipText("Введіть відомості для пошуку.");
		textField.setColumns(18);
		
		List<Cases> listCas = new VisualLogic().findAll(run);

		try {
			model = new TableModel(listCas);
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
			colModel.getColumn(0).setCellRenderer(new FirstPage(run).new DateCellRenderer());
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
		scrollPane.setFont(scrollPane.getFont().deriveFont(scrollPane.getFont().getSize() + 5f));
		
		 button_2 = new JButton("Пошук");
		 button_2.setToolTipText("Пошук серед завантажених справ. ");
		button_2.addActionListener(new ActionListener() {
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
					colModel.getColumn(0).setPreferredWidth(90);
					colModel.getColumn(0).setCellRenderer(new FirstPage(run).new DateCellRenderer());
					colModel.getColumn(1).setPreferredWidth(70);
					colModel.getColumn(2).setPreferredWidth(70);
					colModel.getColumn(3).setPreferredWidth(250);
					colModel.getColumn(4).setPreferredWidth(250);
					colModel.getColumn(5).setPreferredWidth(100);

					table.setAutoCreateRowSorter(true);

				} catch (Exception exc) {
					System.out.println("problem 1");
					exc.printStackTrace();

				}
			}
		});
		
		JButton button_4 = new JButton("Очистити список");
		button_4.setToolTipText("Видалити всі справи з даного списку. ");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CaseRepository caseRep = run.getBean(CaseRepository.class);
				List<Cases> listCases = new VisualLogic().findAll(run);
				caseRep.delete(listCases);
				refresh(run);
			}
		});
		
		JButton button_6 = new JButton("Додати в обране");
		button_6.setToolTipText("Додати справу в список Обраних.");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cases> listtt = new VisualLogic().findAll(run);
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Потрібно обрати справу!");
					return;
				}
				String cas = (String) table.getValueAt(row, 2);
				String date = table.getValueAt(row, 0).toString();
			
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
				JOptionPane.showMessageDialog(null, "Додано!");
			}
		});
		test.setToolTipText("Оберіть суд, чиї справи будуть завантажені.");
		
		 
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!td.isVisible()) {
					td.setVisible(true);

					model = new TableModel(td.getAllCasesFromDialog(run));
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
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_4)
							.addGap(18)
							.addComponent(button_6)
							.addGap(18)
							.addComponent(test))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(test)
							.addComponent(button_6)
							.addComponent(button_4)
							.addComponent(button_2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(4))
		);
		contentPanel.setLayout(gl_contentPanel);
		contentPanel.add(scrollPane, BorderLayout.SOUTH);
		scrollPane.setViewportView(table);
		contentPanel.getRootPane().setDefaultButton(button_2);
		
		setLocationRelativeTo(null);
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}
	
	 public List<SelectedCases> getAllCasesFromDialog(ConfigurableApplicationContext run) {
		  List<SelectedCases> list = new VisualLogic().findAllSelectedCases(run);
	      return list;
	   }
	
	public void refresh(ConfigurableApplicationContext run) {
		try {

			List<Cases> cases = new VisualLogic().findAll(run);

			model = new TableModel(cases);
			table.setModel(model);
			table.setFont(new Font("Times new roman", Font.PLAIN, 15));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table.setVisible(true);

			TableColumnModel colModel = table.getColumnModel();
			colModel.getColumn(1).setPreferredWidth(90);
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

	}
}
