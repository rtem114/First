package ua.graphics;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import ua.entity.CasesToFind;
import ua.mainLogic.VisualLogic;
import ua.repository.CasesToFindRepository;
import ua.tableModel.CasesToFindModel;
import java.awt.Toolkit;

@Service
public class CasesToFindFrame extends JFrame {

	
	private static final long serialVersionUID = 339341486061950707L;
	private JPanel contentPane;

	private JTextField textField;
	private JTable table_1;
	public CasesToFindModel model;
	private List<CasesToFind> listCasesToFind;
	private GroupLayout gl_contentPane;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBox;

	
	public CasesToFindFrame(ConfigurableApplicationContext run) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CasesToFindFrame.class.getResource("/ua/projectResources/binoculars (1).png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Вихід");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		listCasesToFind = new VisualLogic().findAllCasesToFind(run);

		JLabel label = new JLabel("Введіть дані про позивача чи відповідача (прізвище або назву)");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JButton button = new JButton("Видалити");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Потрібно обрати пункт");
					return;
				}

				CasesToFindRepository ctfr = run.getBean(CasesToFindRepository.class);
				List<CasesToFind> listCasesToFind = new VisualLogic().findAllCasesToFind(run);
				for (CasesToFind cases : listCasesToFind) {
					String cor = (String) table_1.getValueAt(row, 0);
					if (cases.getCasesToFind().equals(cor)) {
						ctfr.delete(cases);
					}
				}
				new Refresh().refresh(run);
			}
		});

		scrollPane = new JScrollPane();

		textField = new JTextField();
		textField.setColumns(10);
		setLocationRelativeTo(null);
		////////////////////////////////////////////////////////////////////////////////////////////////
		JButton button_1 = new JButton("Додати");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String choise = null;
				choise = comboBox.getSelectedItem().toString();
				String text = textField.getText();
				if (text != null && text.trim().length() > 0) {
					for (CasesToFind casesToFind : listCasesToFind) {
						if (casesToFind.getCasesToFind().toLowerCase().equals(text.toLowerCase())
								&& casesToFind.getCourt().equals(choise)) {
							JOptionPane.showMessageDialog(null, "Така справа вже присутня в списку");
							new Refresh().refresh(run);
							textField.setText("");
							return;

						}
					}
									
					CasesToFindRepository ctfRepository = run.getBean(CasesToFindRepository.class);
					CasesToFind ctf = new CasesToFind();
					ctf.setCasesToFind(text);
					ctf.setCourt(choise);
					ctfRepository.save(ctf);

				} else {

					JOptionPane.showMessageDialog(null, "Введіть дані");
					return;
				}

				new Refresh().refresh(run);
				textField.setText("");
			}
		});
		
		 comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setName("");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Першотравневий районний суд м. Чернівці", "Садгірський районний суд м. Чернівці", "Шевченківський районний суд м.Чернівці", 
				"Вижницький районний суд", "Герцаївський районний суд", "Глибоцький районний суд", "Заставнівський районний суд", "Кельменецький районний суд", "Кіцманський районний суд", 
				"Новоселицький районний суд", "Путильський районний суд", "Сокирянський районний суд", "Сторожинецький районний суд", "Хотинський районний суд", 
				"Чернівецький окружний адміністративний суд", "Апеляційний суд", "Новодністровський міський суд", "Господарський суд Чернівецької області"}));
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("Оберіть суд");
		////////////////////////////////////////////////////////////////////////////////////////////////
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(35, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBox, Alignment.LEADING, 0, 321, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(button_1)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1))
					.addGap(7)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(button))
					.addContainerGap())
		);

		try {

			model = new CasesToFindModel(listCasesToFind);

			table_1 = new JTable();
			table_1.setFont(new Font("Times new roman", Font.PLAIN, 17));
			table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
			table_1.setModel(model);

		} catch (Exception exc) {
			exc.printStackTrace();
		}

		scrollPane.setViewportView(table_1);
		contentPane.setLayout(gl_contentPane);

		contentPane.getRootPane().setDefaultButton(button_1);
	}

	private class Refresh {

		private void refresh(ConfigurableApplicationContext run) {

			try {
				listCasesToFind = new VisualLogic().findAllCasesToFind(run);
				model = new CasesToFindModel(listCasesToFind);

				table_1 = new JTable();
				table_1.setFont(new Font("Times new roman", Font.PLAIN, 17));
				table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				table_1.setModel(model);

			} catch (Exception exc) {
				exc.printStackTrace();
			}
			scrollPane.setViewportView(table_1);
			contentPane.setLayout(gl_contentPane);
			
		}

	}

}
