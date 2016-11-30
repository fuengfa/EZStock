package ezstock;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class EditProduct {

	public JFrame frame;
	public JTextField textField;
	private JTextField product;
	private JTextField name;
	private JTextField amount;
	private JTextField cost;
	private JTextField price;
	public ResultSet recShowAll;
	public int rowShowall;
	public String Data[];
	public JButton btnNewButton;
	public JButton btnSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProduct window = new EditProduct();
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
	private JTable table;

	public EditProduct() {

		initialize();
		DBConnection t2 = new DBConnection();
		conn = t2.openConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Data = new String[5];
		frame.setTitle("EZStock");
		frame.getContentPane().setBackground(new Color(250, 250, 210));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(new ImageIcon(LOginUser.class.getResource("/img/ez.png")).getImage());

		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		textField.setBackground(new Color(224, 255, 255));
		textField.setForeground(new Color(0, 0, 0));
		textField.setBounds(930, 31, 171, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(139, 69, 19));
		lblSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblSearch.setBounds(842, 38, 78, 17);
		frame.getContentPane().add(lblSearch);

		product = new JTextField();
		product.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		product.setBackground(new Color(224, 255, 255));
		product.setBounds(228, 156, 115, 32);
		frame.getContentPane().add(product);
		product.setColumns(10);

		name = new JTextField();
		name.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		name.setBackground(new Color(224, 255, 255));
		name.setForeground(new Color(0, 0, 0));
		name.setBounds(228, 232, 108, 32);
		frame.getContentPane().add(name);
		name.setColumns(10);

		amount = new JTextField();
		amount.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		amount.setBackground(new Color(224, 255, 255));
		amount.setForeground(new Color(0, 0, 0));
		amount.setBounds(228, 301, 108, 32);
		frame.getContentPane().add(amount);
		amount.setColumns(10);

		cost = new JTextField();
		cost.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		cost.setBackground(new Color(224, 255, 255));
		cost.setForeground(new Color(0, 0, 0));
		cost.setBounds(228, 374, 108, 29);
		frame.getContentPane().add(cost);
		cost.setColumns(10);

		price = new JTextField();
		price.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		price.setBackground(new Color(224, 255, 255));
		price.setForeground(new Color(0, 0, 0));
		price.setBounds(228, 431, 108, 29);
		frame.getContentPane().add(price);
		price.setColumns(10);

		JLabel lblCode = new JLabel("Product Code:");
		lblCode.setForeground(new Color(128, 0, 0));
		lblCode.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblCode.setBounds(85, 155, 133, 29);
		frame.getContentPane().add(lblCode);

		JLabel lblName = new JLabel("Product Name:");
		lblName.setForeground(new Color(128, 0, 0));
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblName.setBounds(85, 237, 133, 17);
		frame.getContentPane().add(lblName);

		JLabel lblNewLabel = new JLabel("Amount:");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblNewLabel.setBounds(143, 306, 89, 17);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblAmount = new JLabel("Cost:");
		lblAmount.setForeground(new Color(128, 0, 0));
		lblAmount.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblAmount.setBounds(159, 374, 59, 22);
		frame.getContentPane().add(lblAmount);

		JLabel lblNewLabel_1 = new JLabel("Sale price:");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(113, 429, 119, 26);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username123 = "INSERT INTO newstock(code,name,amount,cost,sale) values (?,?,?,?,?)";
				try {
					PreparedStatement prepair = conn.prepareStatement(username123);
					prepair.setInt(1, Integer.parseInt(product.getText()));
					prepair.setString(2, name.getText());
					prepair.setInt(3, Integer.parseInt(amount.getText()));
					prepair.setDouble(4, Double.parseDouble(cost.getText()));
					prepair.setDouble(5, Double.parseDouble(price.getText()));
					prepair.execute();
					prepair.close();
					product.setText("");
					name.setText("");
					amount.setText("");
					cost.setText("");
					price.setText("");
					JOptionPane.showMessageDialog(null, "Saved");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Dupllicate Product code");
					e.printStackTrace();
				}
				showAllData();
			}
		});
		btnSave.setForeground(new Color(128, 0, 128));
		btnSave.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnSave.setBounds(208, 500, 128, 36);
		frame.getContentPane().add(btnSave);

		JButton btnUpdate = new JButton("Edit");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement s = null;

				try {
					s = conn.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					if (name.getText().isEmpty() || amount.getText().isEmpty() || cost.getText().isEmpty()
							|| price.getText().isEmpty()) {
						throw new MyownExcepton("Please fill all the fields");
					}
					String sql = "UPDATE newstock " + "SET name = '" + name.getText() + "' " +

					", amount = '" + Integer.parseInt(amount.getText()) + "' " +

					", cost = '" + Integer.parseInt(cost.getText()) + "' " +

					", sale = '" + Integer.parseInt(price.getText()) + "' " +

					" WHERE code = '" + Integer.parseInt(product.getText()) + "' ";

					try {
						s.execute(sql);
						product.setText("");
						name.setText("");
						amount.setText("");
						cost.setText("");
						price.setText("");
						JOptionPane.showMessageDialog(null, "Record Update Successfully");

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getStackTrace());

					}

				} catch (MyownExcepton e2) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields");
				}
				showAllData();

			}
		});
		btnUpdate.setForeground(new Color(128, 0, 128));
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnUpdate.setBounds(700, 555, 115, 36);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelect = new JButton("Delete");
		btnDelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();

				if (index < 0) {

					JOptionPane.showMessageDialog(null,

							"Please select record for Delete!");

				} else {

					String CustomerID = table.getValueAt(index, 0).toString();

					Object[] options = { "Yes", "No" };

					int n = JOptionPane.showOptionDialog(null,

							"Do you want to Delete data?",

							"Confirm to Delete?",

							JOptionPane.YES_NO_CANCEL_OPTION,

							JOptionPane.QUESTION_MESSAGE, null, options,

							options[1]);

					if (n == 0) // Confirm Delete = Yes

					{

						DeleteData(CustomerID); // Delete Data
						showAllData();

					}

				}
			}
		});
		btnDelect.setForeground(new Color(128, 0, 128));
		btnDelect.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnDelect.setBounds(825, 555, 108, 36);
		frame.getContentPane().add(btnDelect);

		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(105, 105, 105));
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnHome.setBounds(48, 655, 96, 31);
		frame.getContentPane().add(btnHome);
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new Function();
			}
		});

		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(105, 105, 105));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnExit.setBounds(1133, 655, 89, 32);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData();
				textField.setText("");

			}
		});
		btnSearch.setBackground(new Color(152, 251, 152));
		btnSearch.setForeground(new Color(154, 205, 50));
		btnSearch.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnSearch.setBounds(1122, 31, 115, 30);
		frame.getContentPane().add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(512, 164, 611, 359);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnNewButton = new JButton("Show All");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllData();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(221, 160, 221));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnNewButton.setBounds(1015, 114, 108, 36);
		frame.getContentPane().add(btnNewButton);

		JLabel lblEditStock = new JLabel("Edit Stock");
		lblEditStock.setForeground(new Color(128, 128, 128));
		lblEditStock.setFont(new Font("Comic Sans MS", Font.BOLD, 86));
		lblEditStock.setBounds(276, 35, 498, 89);
		frame.getContentPane().add(lblEditStock);
		frame.setVisible(true);
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

			String sqlTable = "SELECT * FROM  newstock " + "WHERE name like '%" + textField.getText() + "%' "
					+ "ORDER BY code ASC";

			ResultSet rec = s.executeQuery(sqlTable);

			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(rec.getInt("code"), row, 0);
				model.setValueAt(rec.getString("name"), row, 1);
				model.setValueAt(rec.getInt("amount"), row, 2);
				model.setValueAt(rec.getDouble("cost"), row, 3);
				model.setValueAt(rec.getDouble("sale"), row, 4);
				row++;
				if(row==1){
					Data[0] = Integer.toString(rec.getInt("code"));
					Data[1] = rec.getString("name");
					Data[2] = Integer.toString(rec.getInt("amount"));
					Data[3] = Double.toString(rec.getDouble("cost"));
					Data[4] = Double.toString(rec.getDouble("sale"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No matching");
			e.printStackTrace();
		}

	}

	private void DeleteData(String d) {

		Statement s = null;
		try {
			s = conn.createStatement();

			String sqlDelete = "DELETE FROM newstock  WHERE " +

			"code = '" + d + "' ";

			s.execute(sqlDelete);

			JOptionPane.showMessageDialog(null, "Delete Data Successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
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

			String sqlShowAll = "SELECT * FROM  newstock " + "WHERE name like '%" + textField.getText() + "%' "
					+ "ORDER BY code ASC";

			recShowAll = s.executeQuery(sqlShowAll);
			rowShowall = 0;
			while ((recShowAll != null) && (recShowAll.next())) {
				model.addRow(new Object[0]);
				model.setValueAt(recShowAll.getString("code"), rowShowall, 0);
				model.setValueAt(recShowAll.getString("name"), rowShowall, 1);
				model.setValueAt(recShowAll.getString("amount"), rowShowall, 2);
				model.setValueAt(recShowAll.getString("cost"), rowShowall, 3);
				model.setValueAt(recShowAll.getFloat("sale"), rowShowall, 4);
				rowShowall++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}

