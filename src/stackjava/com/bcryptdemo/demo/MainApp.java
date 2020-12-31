package stackjava.com.bcryptdemo.demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.mindrot.jbcrypt.BCrypt;

public class MainApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCalculator;
	private JPanel panelTester;
	private JLabel lblEnterPasswordTo;
	private JTextField textFieldPassToHash;
	private JLabel lblRound;
	private JComboBox comboBoxRound;
	private JButton btnCalculateHash;
	private JTextField textFieldHashResult;
	private JLabel lblPasswordToEvaluate;
	private JTextField textFieldPassToEvaluate;
	private JTextField textFieldBcryptHashToEvaluate;
	private JLabel lblBcryptHashTo;
	private JButton btnTest;
	private JTextField textFieldEvaluteResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			        SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		setTitle("BCrypt Demo - stackjava.com");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 399);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.panelCalculator = new JPanel();
		this.panelCalculator.setBorder(new TitledBorder(null, "BCrypt Calculator", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelCalculator.setBounds(12, 13, 543, 142);
		this.contentPane.add(this.panelCalculator);
		this.panelCalculator.setLayout(null);
		
		this.lblEnterPasswordTo = new JLabel("Enter password to Hash: ");
		this.lblEnterPasswordTo.setBounds(12, 28, 152, 16);
		this.panelCalculator.add(this.lblEnterPasswordTo);
		
		this.textFieldPassToHash = new JTextField();
		this.textFieldPassToHash.setBounds(153, 25, 372, 22);
		this.panelCalculator.add(this.textFieldPassToHash);
		this.textFieldPassToHash.setColumns(10);
		
		this.lblRound = new JLabel("Maximum number of rounds which is tolerable:");
		this.lblRound.setBounds(12, 61, 278, 16);
		this.panelCalculator.add(this.lblRound);
		
		this.comboBoxRound = new JComboBox();
		this.comboBoxRound.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		this.comboBoxRound.setBounds(353, 57, 172, 22);
		this.panelCalculator.add(this.comboBoxRound);
		
		this.btnCalculateHash = new JButton("Calculate");
		this.btnCalculateHash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = textFieldPassToHash.getText();
				if (password != null && !password.isEmpty()) {
					int round = Integer.parseInt(comboBoxRound.getSelectedItem().toString());
					String hash = BCrypt.hashpw(password, BCrypt.gensalt(round));
					textFieldHashResult.setText(hash);
				}
			}
		});
		this.btnCalculateHash.setBounds(12, 90, 95, 25);
		this.panelCalculator.add(this.btnCalculateHash);
		
		this.textFieldHashResult = new JTextField();
		this.textFieldHashResult.setEditable(false);
		this.textFieldHashResult.setBounds(153, 90, 372, 22);
		this.panelCalculator.add(this.textFieldHashResult);
		this.textFieldHashResult.setColumns(10);
		
		this.panelTester = new JPanel();
		this.panelTester.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "BCrypt Tester", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panelTester.setBounds(12, 179, 543, 175);
		this.contentPane.add(this.panelTester);
		this.panelTester.setLayout(null);
		
		this.lblPasswordToEvaluate = new JLabel("Password to evaluate:");
		this.lblPasswordToEvaluate.setBounds(12, 26, 134, 16);
		this.panelTester.add(this.lblPasswordToEvaluate);
		
		this.textFieldPassToEvaluate = new JTextField();
		this.textFieldPassToEvaluate.setColumns(10);
		this.textFieldPassToEvaluate.setBounds(154, 23, 380, 22);
		this.panelTester.add(this.textFieldPassToEvaluate);
		
		this.textFieldBcryptHashToEvaluate = new JTextField();
		this.textFieldBcryptHashToEvaluate.setColumns(10);
		this.textFieldBcryptHashToEvaluate.setBounds(154, 74, 380, 22);
		this.panelTester.add(this.textFieldBcryptHashToEvaluate);
		
		this.lblBcryptHashTo = new JLabel("BCrypt hash to evaluate:");
		this.lblBcryptHashTo.setBounds(12, 77, 134, 16);
		this.panelTester.add(this.lblBcryptHashTo);
		
		this.btnTest = new JButton("Calculate");
		this.btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = textFieldPassToEvaluate.getText();
				String hash = textFieldBcryptHashToEvaluate.getText();
				boolean match = BCrypt.checkpw(password, hash);
				if (match) {
					textFieldEvaluteResult.setText("Password and hash match");
				} else {
					textFieldEvaluteResult.setText("Password and hash do NOT match");
				}
			}
		});
		this.btnTest.setBounds(12, 123, 95, 25);
		this.panelTester.add(this.btnTest);
		
		this.textFieldEvaluteResult = new JTextField();
		this.textFieldEvaluteResult.setEditable(false);
		this.textFieldEvaluteResult.setColumns(10);
		this.textFieldEvaluteResult.setBounds(153, 123, 372, 22);
		this.panelTester.add(this.textFieldEvaluteResult);
	}
}
