package ua.graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.entity.Cases;
import ua.mainLogic.TableModel;
import ua.mainLogic.VisualLogic;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

@Service
public class Appearance extends JFrame {

	private static final long serialVersionUID = 8593672284935730017L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	@Autowired
	private ConfigurableApplicationContext run;

	public Appearance() {
		setFont(new Font("Times New Roman", Font.PLAIN, 12));
		setTitle("Cases List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel label = new JLabel("Введіть дані сторони справи");
		panel.add(label);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(18);

		btnNewButton = new JButton("Пошук");
//		String text = textField.getText();
//		if (text == null) {
//			btnNewButton.addActionListener((e) -> new VisualLogic().findAll(run));
//		}else{
//			btnNewButton.addActionListener((e) -> new VisualLogic().findElement(run, text));
//		}

			
			
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
								
					try {
						String text = textField.getText();

						List<Cases> cases = null;

						if (text == null) {
						cases = new VisualLogic().findAll(run);
						} else {
							cases = new VisualLogic().findElement(run, text);
						}
						
						// create the model and update the "table"
						TableModel model = new TableModel(cases);
						
						table.setModel(model);
						
						/*
						for (Employee temp : employees) {
							System.out.println(temp);
						}
						*/
					} catch (Exception exc) {
						exc.printStackTrace();
					}
					
				}
			});
			
			
			
			
			
			
//		TableModel model = new TableModel(new VisualLogic().findAll(run));
//		table.setModel(model);
		
		panel.add(btnNewButton);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	@PostConstruct
	public void visible() {
		setVisible(true);
	}
}
