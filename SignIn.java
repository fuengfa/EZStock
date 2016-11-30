package ezstock;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SignIn {

	private JFrame frame;
	private JTextField textFieldUer;
	private JTextField PasswordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
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

	public SignIn() {
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
		frame.getContentPane().setBackground(new Color(255, 250, 205));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblUsername.setBounds(727, 296, 105, 25);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblPassword.setBounds(727, 380, 105, 25);
		frame.getContentPane().add(lblPassword);

		textFieldUer = new JTextField();
		textFieldUer.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		textFieldUer.setBounds(876, 300, 148, 25);
		frame.getContentPane().add(textFieldUer);
		textFieldUer.setColumns(10);

		PasswordTextField = new JTextField();
		PasswordTextField.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		PasswordTextField.setBounds(876, 384, 150, 25);
		frame.getContentPane().add(PasswordTextField);
		PasswordTextField.setColumns(10);

		JLabel PicLabel = new JLabel("");
		PicLabel.setBackground(new Color(224, 255, 255));
		PicLabel.setBounds(77, 96, 530, 551);
		PicLabel.setIcon(new ImageIcon(LOginUser.class.getResource("/img/Key-icon.png")));
		frame.getContentPane().add(PicLabel);

		JButton btnSingUp = new JButton("Sign Up");
		btnSingUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usernamePattern = "[A-Za-z0-9]{4}";
				String passwordPattern = "[A-Za-z0-9]{4}";
				if (textFieldUer.getText().matches(usernamePattern) == true
						&& PasswordTextField.getText().matches(passwordPattern) == true) {
					String username123 = "INSERT INTO username(id,password) values (?,?)";
					try {
						PreparedStatement prepair = conn.prepareStatement(username123);
						prepair.setString(1, textFieldUer.getText());
						prepair.setString(2, PasswordTextField.getText());
						prepair.execute();
						prepair.close();
						JOptionPane.showMessageDialog(null, "Sign up Successfull");
						frame.setVisible(false);
						new LOginUser();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Duplicate username");
					}
				}else{
					JOptionPane.showMessageDialog(null, "User or Password can contain only A-Z , a-z , 0-9");
				}
			}
		});
		btnSingUp.setBackground(new Color(255, 192, 203));
		btnSingUp.setOpaque(true);
		btnSingUp.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnSingUp.setBounds(883, 475, 115, 42);
		frame.getContentPane().add(btnSingUp);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 99, 71), new Color(255, 69, 0)));
		panel.setBounds(634, 210, 427, 342);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("*Notice : Use charactor (a-z,A-Z,0-9) ");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(172, 223, 229, 22);
		panel.add(lblNewLabel);

		JButton Home = new JButton("Back");
		Home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new LOginUser();
			}
		});
		Home.setForeground(Color.GRAY);
		Home.setBackground(new Color(211, 211, 211));
		Home.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		Home.setBounds(1070, 622, 115, 42);
		frame.getContentPane().add(Home);

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnNewButton.setForeground(new Color(128, 128, 128));
		btnNewButton.setBounds(1195, 622, 115, 42);
		frame.getContentPane().add(btnNewButton);

		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setForeground(new Color(128, 128, 128));
		lblSignUp.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
		lblSignUp.setBounds(156, 0, 390, 110);
		frame.getContentPane().add(lblSignUp);
		frame.setVisible(true);

	}
}
