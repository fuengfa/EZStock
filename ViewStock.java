package ezstock;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ViewStock {
	public JFrame frame;
	private JTable table;
	private JTextField textField;
	private JButton btnHome;
	private JLabel lblViewStock;
	private JLabel lblNewLabel;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStock window = new ViewStock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection conn = null;
	private JScrollPane scrollPane;
	private JButton btnShowAll;
	private JButton btnNewButton;

	public ViewStock() {
		initialize();
		DBConnection t2 = new DBConnection();
		conn = t2.openConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("EZStock");
		frame.setIconImage(new ImageIcon(LOginUser.class.getResource("/img/ez.png")).getImage());
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(255, 255, 224));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		JLabel lblSearch = new JLabel("Search Data");
		lblSearch.setForeground(new Color(0, 139, 139));
		lblSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblSearch.setBounds(560, 144, 114, 24);
		frame.getContentPane().add(lblSearch);

		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(224, 255, 255));
		textField.setBounds(696, 142, 167, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		btnHome = new JButton("HOME");
		btnHome.setBackground(new Color(105, 105, 105));
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Function();

			}
		});
		btnHome.setBounds(39, 626, 154, 42);
		frame.getContentPane().add(btnHome);

		lblViewStock = new JLabel("View Stock");
		lblViewStock.setForeground(new Color(105, 105, 105));
		lblViewStock.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
		lblViewStock.setBounds(416, 43, 424, 57);
		frame.getContentPane().add(lblViewStock);

		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(ViewStock.class.getResource("/img/Search-icon1.png")));
		lblNewLabel.setBounds(758, 11, 167, 131);
		frame.getContentPane().add(lblNewLabel);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnSearch.setBackground(new Color(144, 238, 144));
		btnSearch.setForeground(new Color(245, 255, 250));
		btnSearch.setBounds(873, 142, 104, 34);
		frame.getContentPane().add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 187, 707, 414);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(105, 105, 105), new Color(105, 105, 105)));

		btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllData();
			}
		});
		btnShowAll.setBackground(new Color(144, 238, 144));
		btnShowAll.setForeground(new Color(255, 255, 255));
		btnShowAll.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnShowAll.setBounds(270, 144, 120, 32);
		frame.getContentPane().add(btnShowAll);
		
		btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnNewButton.setBounds(1103, 626, 154, 42);
		frame.getContentPane().add(btnNewButton);
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchData();
				textField.setText("");

			}
		});
	}

	public void searchData() {
		// Clear table
		table.setModel(new DefaultTableModel());

		// Model for Table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Product Code");
		model.addColumn("Product Name");
		model.addColumn("Amount");
		model.addColumn("Cost");
		model.addColumn("Sale price");

		Statement s = null;

		try {

			s = conn.createStatement();

			String sql = "SELECT * FROM  newstock " + "WHERE name like '%" + textField.getText() + "%' "
					+ "ORDER BY code ASC";

			ResultSet rec = s.executeQuery(sql);
			
			
			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(rec.getString("code"), row, 0);
				model.setValueAt(rec.getString("name"), row, 1);
				model.setValueAt(rec.getString("amount"), row, 2);
				model.setValueAt(rec.getString("cost"), row, 3);
				model.setValueAt(rec.getFloat("sale"), row, 4);
				row++;
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

			
	}
	public void showAllData() {
		// Clear table
		table.setModel(new DefaultTableModel());

		// Model for Table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Product Code");
		model.addColumn("Product Name");
		model.addColumn("Amount");
		model.addColumn("Cost");
		model.addColumn("Sale price");

		Statement s = null;

		try {

			s = conn.createStatement();

			String sql = "SELECT * FROM  newstock " + "WHERE name like '%" + textField.getText() + "%' "
					+ "ORDER BY code ASC";

			ResultSet rec = s.executeQuery(sql);
			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(rec.getString("code"), row, 0);
				model.setValueAt(rec.getString("name"), row, 1);
				model.setValueAt(rec.getString("amount"), row, 2);
				model.setValueAt(rec.getString("cost"), row, 3);
				model.setValueAt(rec.getFloat("sale"), row, 4);
				row++;
			}

		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}