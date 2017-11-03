package ua.graphics;

import java.awt.BorderLayout;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import org.springframework.context.ConfigurableApplicationContext;


import ua.entity.Cases;
import ua.entity.Courts;
import ua.mainLogic.Parsing;
import ua.mainLogic.VisualLogic;
import ua.tableModel.CourtsTableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;

public class CourtsListDialog extends JDialog {

	private static final long serialVersionUID = -2865485942582169321L;
	private final JPanel contentPanel = new JPanel();
	private JTable table_1;
	public Courts corTemp;
	private JProgressBar progressBar;
	private JButton okButton;
	private JButton cancelButton;
	 

	public CourtsListDialog(ConfigurableApplicationContext run,JDialog dialog) {
		super (dialog, true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CourtsListDialog.class.getResource("/ua/projectResources/courthouse (1).png")));
		setBounds(100, 100, 411, 382);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		List<Courts> courts = new VisualLogic().findAllCourts(run);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
		);
		contentPanel.setLayout(gl_contentPanel);
		CourtsTableModel model = new CourtsTableModel(courts);
		table_1 = new JTable();
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);
		
		setLocationRelativeTo(null);
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
			 progressBar = new JProgressBar();
				progressBar.setVisible(false);
			}
			buttonPane.setLayout(new MigLayout("", "[476px][][]", "[29px]"));
			{
				cancelButton = new JButton("Вихід");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						
					}
				});
				cancelButton.setActionCommand("Вихід");
			}
			okButton = new JButton("Обрати");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					int row = table_1.getSelectedRow();
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Потрібно обрати суд!");
						return;
					}
					String cor = (String) table_1.getValueAt(row, 0);
					
					corTemp = null;
					if(cor.equals("Путильський районний суд")){
						JOptionPane.showMessageDialog(null, "Справи даного суду відсутні на сайті <Судова Влада>");
						return;						
					}
					
					for (Courts courts2 : courts) {
						if (courts2.getName().equals(cor)) {
							corTemp = courts2;
						}
					}
					(new SecondThread(run)).execute();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			buttonPane.add(okButton, "cell 1 0,growx,aligny top");
			buttonPane.add(cancelButton, "cell 2 0,alignx left,aligny top");
			buttonPane.add(progressBar, "flowx,cell 0 0,alignx left,aligny top");
		}
	}
	
	  public List<Cases> getAllCasesFromDialog(ConfigurableApplicationContext run) {
		  List<Cases> list = new VisualLogic().findAll(run);
	      return list;
	   }
	
	
	class SecondThread extends SwingWorker<Integer, ConfigurableApplicationContext> {

		private ConfigurableApplicationContext run;

		SecondThread(ConfigurableApplicationContext run) {
			this.run = run;
		};

		@Override
		public Integer doInBackground() {
			progressBar.setVisible(true);
			progressBar.setIndeterminate(true);
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
