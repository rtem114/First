package ua.graphics;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.entity.Cases;
import ua.entity.Courts;
import ua.mainLogic.CourtsTableModel;
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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

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
	private JButton button;

	public Appearance() {

		setFont(new Font("Times New Roman", Font.PLAIN, 12));
		setTitle("Cases List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 409);
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

					TableModel model = new TableModel(cases);

					table.setModel(model);

				} catch (Exception exc) {
					exc.printStackTrace();
				}

			}
		});

		panel.add(btnNewButton);

		button = new JButton("Обрати суд");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				try {
					
					List<Courts> courts =  new VisualLogic().findAllCourts(run);
//					for (Courts courts2 : courts) {
//						System.out.println(courts2.getName());
//					}

//					CourtsTableModel model = new CourtsTableModel(courts);
//					table.setModel(model);
					CourtsListFrame lof = new CourtsListFrame(run);
					lof.setVisible(true);

				} catch (Exception exc) {
					exc.printStackTrace();
				}


			}
		});
		panel.add(button);

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
