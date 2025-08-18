package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import control.GestioneCinema;
import exception.OperationException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Gui {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	static JTextField textField_3;
	ArrayList<String> results = null;
	static ArrayList<Integer> biglietti;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Gui window = new Gui();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
		textField_3.setText(" ");
		results = null;
		biglietti = new ArrayList<Integer>();
		textField.setText("2020-01-01");
		textField_1.setText("16:20");
		textField_2.setText("mariorossi@gmail.com");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GestioneCinema gestioneCinema = GestioneCinema.getInstance();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(25, 102, 168, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDataaaaammgg = new JLabel("Data (aaaa-mm-gg)");
		lblDataaaaammgg.setBounds(25, 82, 141, 16);
		frame.getContentPane().add(lblDataaaaammgg);
		
		textField_1 = new JTextField();
		textField_1.setBounds(24, 160, 112, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblOraHhmm = new JLabel("Ora (hh:mm)");
		lblOraHhmm.setBounds(24, 140, 84, 16);
		frame.getContentPane().add(lblOraHhmm);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(10);
		comboBox.setBounds(144, 161, 72, 27);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		comboBox.addItem("6");
		comboBox.addItem("7");
		comboBox.addItem("8");
		comboBox.addItem("9");
		comboBox.addItem("10");
		
		JLabel lblNumeroPosti = new JLabel("Numero posti");
		lblNumeroPosti.setBounds(144, 140, 93, 16);
		frame.getContentPane().add(lblNumeroPosti);
		
		textField_2 = new JTextField();
		textField_2.setBounds(24, 223, 168, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(24, 207, 61, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblFilm = new JLabel("Film");
		lblFilm.setBounds(24, 20, 61, 16);
		frame.getContentPane().add(lblFilm);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(25, 43, 191, 27);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("Il Gladiatore");
		comboBox_1.addItem("I Pirati dei Caraibi");
		
		JButton btnNewButton = new JButton("Acquista Biglietto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfermaAcquisto ca = new ConfermaAcquisto(biglietti);
				try {
					results = gestioneCinema.acquistaBiglietti(comboBox_1.getSelectedItem().toString(), Date.valueOf(textField.getText()), new Time(new SimpleDateFormat("HH:mm").parse(textField_1.getText()).getTime()), Integer.parseInt(comboBox.getSelectedItem().toString()), textField_2.getText(), biglietti);
					ca.textField_1.setText(results.get(0));
					if(!results.get(1).equals("null")) {
						ca.textField.setText(results.get(1));
					}
					ca.frame.setVisible(true);
				} catch (NumberFormatException | OperationException | ParseException e1) {
					
					textField_3.setText(e1.getMessage());
				}
				//ca.textField_1.setText("10.0 €");
				//ca.textField.setText("1234 1243 1234 1243");
			}
		});
		btnNewButton.setBounds(268, 160, 155, 49);
		frame.getContentPane().add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(268, 52, 155, 90);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblMultiscreen = new JLabel("MultiScreen");
		lblMultiscreen.setForeground(Color.RED);
		lblMultiscreen.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lblMultiscreen.setBounds(268, 7, 149, 32);
		frame.getContentPane().add(lblMultiscreen);
		
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainMenu.frame.setVisible(true);
			}
		});
		btnReturn.setBounds(306, 223, 117, 29);
		frame.getContentPane().add(btnReturn);
	}
}
