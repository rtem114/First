package ua.graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import ch.qos.logback.core.util.CloseUtil;
import ua.entity.SelectedCases;
import ua.mainLogic.VisualLogic;
import ua.repository.CaseRepository;
import ua.repository.SelectedCasesRepository;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class MemoFrame extends JFrame {

	@Autowired
	private ConfigurableApplicationContext run;
	private JPanel contentPane;
	private String text;
	private JTextPane textPane;

	
	public MemoFrame(ConfigurableApplicationContext run, SelectedCases tempCases) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 589, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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

		///////////////////////////////////////////////////////////////////////////////
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 541,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(15, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING,
										gl_contentPane.createSequentialGroup().addContainerGap(361, Short.MAX_VALUE)
												.addComponent(button_1).addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(button).addGap(20)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)
								.addGap(30).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(button).addComponent(button_1))
								.addContainerGap(29, Short.MAX_VALUE)));

		textPane = new JTextPane();
		textPane.setEditable(true);
		textPane.setEnabled(true);
		textPane.setFont(new Font("Times new roman", Font.PLAIN, 20));
		textPane.setText(tempCases.getMemo());

		scrollPane.setViewportView(textPane);
		contentPane.setLayout(gl_contentPane);
	

	}
}
