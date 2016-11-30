package ezstock;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LOginUser {

	private JFrame frame;
	public JTextField user;
	public JPasswordField passwordField;
	private JLabel textField;
	public int count;
	public JButton btnSignIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOginUser window = new LOginUser();
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

	public LOginUser() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		JLabel picture = new JLabel();
		picture.setBounds(151, 171, 295, 273);
		picture.setIcon(new ImageIcon(LOginUser.class.getResource("/img/user-login-icon.png")));
		frame.getContentPane().add(picture);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(250, 250, 210));
		JLabel userLabel = new JLabel("Username");
		userLabel.setBackground(new Color(250, 250, 210));
		userLabel.setOpaque(true);
		userLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		userLabel.setBounds(571, 205, 187, 33);
		frame.getContentPane().add(userLabel);

		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblNewLabel.setBounds(571, 310, 161, 44);
		frame.getContentPane().add(lblNewLabel);

		user = new JTextField();
		user.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		user.setBounds(806, 205, 197, 33);
		frame.getContentPane().add(user);
		user.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		passwordField.setBounds(806, 310, 197, 33);
		frame.getContentPane().add(passwordField);

		JButton signUpbtn = new JButton("Sign up");
		signUpbtn.setBackground(new Color(255, 222, 173));
		signUpbtn.setForeground(new Color(255, 99, 71));
		signUpbtn.setFont(new Font("Comic Sans MS", Font.BOLD, 21));
		signUpbtn.setBounds(633, 494, 117, 44);
		frame.getContentPane().add(signUpbtn);
		signUpbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new SignIn();
			}
		});

		btnSignIn = new JButton("Sign in");
		btnSignIn.setBackground(new Color(255, 222, 173));
		btnSignIn.setForeground(new Color(255, 99, 71));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						String query = "Select * from username where id=? and password=?";
						PreparedStatement pst = conn.prepareStatement(query);

						pst.setString(1, user.getText());
						pst.setString(2, passwordField.getText());
						ResultSet rs = pst.executeQuery();

						count = 0;
						while (rs.next()) {
							count++;
						}
						if (count == 1) {
							frame.setVisible(false);
							Function f = new Function();

						} else if (count > 1) {
							JOptionPane.showMessageDialog(null, "Duplicate Userrname and Password");
						} else {
							JOptionPane.showMessageDialog(null, "Username and Password are incorrect");
						}
						 rs.close();
						 pst.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				
			}
		});
		btnSignIn.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnSignIn.setBounds(871, 494, 117, 44);
		frame.getContentPane().add(btnSignIn);

		JLabel lblEzstock = new JLabel("EZStock");
		lblEzstock.setForeground(new Color(105, 105, 105));
		lblEzstock.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
		lblEzstock.setBounds(123, 71, 368, 97);
		frame.getContentPane().add(lblEzstock);
		frame.setVisible(true);
	}
}
