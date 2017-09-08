package ua.graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;import org.yaml.snakeyaml.constructor.SafeConstructor.ConstructYamlTimestamp;

import ua.entity.Courts;
import ua.mainLogic.CourtsTableModel;
import ua.mainLogic.Parsing;
import ua.mainLogic.VisualLogic;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@Service
public class CourtsListFrame extends JFrame {

	
	private JPanel buttonPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane;

	private JPanel contentPane;
	@Autowired
	private ConfigurableApplicationContext run;
	private JTable table_1;
	private JButton CancelButton_1;
	private JButton btnNewButton_2;
	
	
	public CourtsListFrame(ConfigurableApplicationContext run) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane_1 =  new JScrollPane();
		
		CancelButton_1 = new JButton("Cancel");
		CancelButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		List<Courts> courts = new VisualLogic().findAllCourts(run);
		
		btnNewButton_2 = new JButton("OK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int row = table_1.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "Потрібно обрати суд!");
				return;
			}
			String cor= (String) table_1.getValueAt(row, 0);
			System.out.println(cor);
			Courts corTemp = null;
			for (Courts courts2 : courts) {
				if(courts2.getName().equals(cor)){
					corTemp = courts2;
				}
			}
			new Parsing().parse(run, corTemp);
			
			
			
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(CancelButton_1)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(CancelButton_1)
						.addComponent(btnNewButton_2))
					.addGap(4))
		);
		
		
		
		try {
		
		 	
		 CourtsTableModel model = new CourtsTableModel(courts);
		 table_1 = new JTable();
		 table_1.setModel(model);
		
		 } catch (Exception exc) {
		 exc.printStackTrace();
		 }
		
		scrollPane_1.setViewportView(table_1);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}


