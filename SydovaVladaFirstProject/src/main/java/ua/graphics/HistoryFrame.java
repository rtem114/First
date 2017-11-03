package ua.graphics;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import ua.entity.SelectedCases;
import ua.entity.SelectedCasesHistory;
import ua.mainLogic.VisualLogic;
import ua.tableModel.HistoryTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HistoryFrame extends JFrame {

	private static final long serialVersionUID = -7318100446799066067L;
	private JPanel contentPane;
	@Autowired
	private ConfigurableApplicationContext run;
	private JTable table;

	public HistoryFrame(ConfigurableApplicationContext run, SelectedCases selectedCases) {
		ArrayList<String> arListOfHistory = new ArrayList<String>();
		List<SelectedCasesHistory> listSCH = new VisualLogic().findAllSelectedCasesHistory(run);
		for (SelectedCasesHistory selectedCasesHistory : listSCH) {
			
//		/	if (((Integer) selectedCasesHistory.getSelectedCases().getId()).equals(selectedCases.getId())) {
			if(selectedCasesHistory.getSides().equals(selectedCases.getSides())){
				String one = selectedCasesHistory.getHistory().toString().substring(8, 10) + "."
						+ selectedCasesHistory.getHistory().toString().substring(5, 7) + "."
						+ selectedCasesHistory.getHistory().toString().substring(0, 4) + "  "
						+ selectedCasesHistory.getHistory().toString().substring(11, 16);
				arListOfHistory.add(one);
			}
		}
		for (String df : arListOfHistory) {
			System.out.println(df);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 302, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE).addGap(8)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE).addGap(5)));

		try {
			HistoryTableModel model = new HistoryTableModel(arListOfHistory);
			table = new JTable();
			table.setModel(model);
			table.setAutoCreateRowSorter(true);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
//		JOptionPane.showMessageDialog(null, "This is the message...");
//		JOptionPane.show
	}
}
