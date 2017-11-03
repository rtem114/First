package ua.graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ua.entity.Cases;
import ua.entity.Courts;
import ua.mainLogic.Parsing;
import ua.mainLogic.VisualLogic;
import ua.tableModel.CourtsTableModel;
import ua.tableModel.TableModel;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

@Service
public class CourtsListFrame extends JFrame {

	private static final long serialVersionUID = 5267767903090808890L;
	// private ProgressInfinite progress;
	private JPanel contentPane;
	// @Autowired
	// private ConfigurableApplicationContext run;
	private JTable table_1;
	private static CourtsListFrame obj=null;
	private JButton CancelButton_1;
	private JButton btnNewButton_2;
	private JProgressBar progressBar;
	public Courts corTemp;

	public CourtsListFrame(ConfigurableApplicationContext run) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 492, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane_1 = new JScrollPane();

		List<Courts> courts = new VisualLogic().findAllCourts(run);

		btnNewButton_2 = new JButton("OK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer t = 0;
				int row = table_1.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Потрібно обрати суд!");
					return;
				}
				String cor = (String) table_1.getValueAt(row, 0);
				System.out.println(cor);
				corTemp = null;
				for (Courts courts2 : courts) {
					if (courts2.getName().equals(cor)) {
						corTemp = courts2;
					}
				}				
				(new SecondThread(run)).execute();
					}
		});
		CancelButton_1 = new JButton("Вихід");
		CancelButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		try {

			CourtsTableModel model = new CourtsTableModel(courts);
			table_1 = new JTable();
			table_1.setModel(model);

		} catch (Exception exc) {
			exc.printStackTrace();
		}

		scrollPane_1.setViewportView(table_1);
		
		progressBar = new JProgressBar();
		// ImageIcon ii = new ImageIcon(getClass().getResource("/images/ajax-loader2.gif"));
		// зображення процесу завантаження через гіфку
		//
		JLabel lblNewLabel = new JLabel();
		// lblNewLabel.setIcon(ii);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(CancelButton_1))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(CancelButton_1)
								.addComponent(btnNewButton_2))
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.add(lblNewLabel, java.awt.BorderLayout.CENTER);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
		progressBar.setVisible(false);
		progressBar.setIndeterminate(true);
		
	}
	
	public  static CourtsListFrame getObj(ConfigurableApplicationContext run){
		// Сінглтон для одиничного відкривання вікна
		if(obj==null){
			obj=new CourtsListFrame(run);
		}return obj;
	}
	

	class SecondThread extends SwingWorker<Integer, ConfigurableApplicationContext> {

		private ConfigurableApplicationContext run;

		SecondThread(ConfigurableApplicationContext run) {
			this.run = run;
		};

		@Override
		public Integer doInBackground() {
			progressBar.setVisible(true);
			int t = new Parsing().parse(run, corTemp);
			JOptionPane.showMessageDialog(null, "Завантажено " + t + " призначених справ даного суду.");
			return t;
		}

		@Override
		protected void done() {
			try {
				progressBar.setVisible(false);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	


}
