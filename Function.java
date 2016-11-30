package ezstock;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Function {
	public ViewStock v ;
	public JFrame frame;
	public JButton btnViewStock;
	public JButton btnEditStock;
	public EditProduct e2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Function window = new Function();
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
	public Function() {
		initialize();
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
		btnViewStock = new JButton("View Stock");
		btnViewStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewStock.setIcon(new ImageIcon(Function.class.getResource("/img/Search-icon.png")));
		btnViewStock.setForeground(new Color(255, 255, 255));
		btnViewStock.setBackground(new Color(188, 143, 143));
		btnViewStock.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		btnViewStock.setBounds(136, 495, 262, 105);
		frame.getContentPane().add(btnViewStock);
		btnViewStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				 v = new ViewStock();
			}
		});

		btnEditStock = new JButton("Edit Stock");
		btnEditStock.setIcon(new ImageIcon(Function.class.getResource("/img/edit-file-icon.png")));
		btnEditStock.setBackground(new Color(188, 143, 143));
		btnEditStock.setForeground(new Color(255, 255, 255));
		btnEditStock.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		btnEditStock.setBounds(983, 495, 306, 105);
		frame.getContentPane().add(btnEditStock);

		btnEditStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				e2 = new EditProduct();

			}
		});

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Function.class.getResource("/img/function.png")));
		lblNewLabel.setBounds(567, 113, 449, 304);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}


		
		


}
