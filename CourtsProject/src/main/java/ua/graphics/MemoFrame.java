package ua.graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import ua.entity.SelectedCases;
import ua.mainLogic.VisualLogic;
import ua.repository.SelectedCasesRepository;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MemoFrame extends JFrame {

	private static final long serialVersionUID = 3891873203114525908L;

	private JPanel contentPane;
	private String text;
	private JTextPane textPane;

	
	public MemoFrame(ConfigurableApplicationContext run, SelectedCases tempCases) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MemoFrame.class.getResource("/ua/projectResources/edit.png")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 577, 668);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		///////////////////////////////////////////////////////////////////////////////
		JButton button = new JButton("Відмінити ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				

			}
		});

		JButton button_1 = new JButton("Зберегти зміни");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = textPane.getText();
				List<SelectedCases> listSelecCas = new VisualLogic().findAllSelectedCases(run);
				for (SelectedCases selectedCases : listSelecCas) {
					if (selectedCases.getNumber().equals(tempCases.getNumber())) {
						SelectedCasesRepository selCasRep = run.getBean(SelectedCasesRepository.class);
						selectedCases.setMemo(text);
						selCasRep.save(selectedCases);

					}
				}
				JOptionPane.showMessageDialog(null, "Нотатку збережено.");
				setVisible(false);
//				new SelectedCasesListFrame(run).new Refresh().refresh(run);
			
			}
		});

		textPane = new JTextPane();
		textPane.setEditable(true);
		textPane.setEnabled(true);
		textPane.setFont(new Font("Times new roman", Font.PLAIN, 18));
		textPane.setText(tempCases.getMemo());

		scrollPane.setViewportView(textPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(11))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);

	}
}
