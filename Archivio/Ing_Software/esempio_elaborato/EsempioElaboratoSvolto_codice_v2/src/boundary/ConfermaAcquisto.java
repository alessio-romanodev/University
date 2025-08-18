package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import control.GestioneCinema;
import exception.OperationException;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConfermaAcquisto {

	JFrame frame;
	JTextField textField;
	JTextField textField_1;
	ArrayList<Integer> biglietti;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConfermaAcquisto window = new ConfermaAcquisto();
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
	public ConfermaAcquisto(ArrayList<Integer> _biglietti) {
		initialize();
		biglietti = _biglietti;
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
		textField.setBounds(41, 153, 347, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(41, 57, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTotale = new JLabel("Totale (EURO)");
		lblTotale.setBounds(42, 41, 116, 16);
		frame.getContentPane().add(lblTotale);
		
		JLabel lblCartaDiCredito = new JLabel("Carta di Credito");
		lblCartaDiCredito.setBounds(42, 135, 129, 16);
		frame.getContentPane().add(lblCartaDiCredito);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					gestioneCinema.emettiBiglietti(biglietti);
					biglietti = new ArrayList<Integer>();
					Gui.biglietti = new ArrayList<Integer>();
				} catch (OperationException e1) {
					// TODO Auto-generated catch block
					System.out.println("Riprovare..\n");
				}
				Gui.textField_3.setText(" Acquisto Effettuato!");
			}
		});
		btnConferma.setBounds(41, 218, 117, 29);
		frame.getContentPane().add(btnConferma);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Gui.textField_3.setText(" Acquisto Annullato!");
			}
		});
		btnAnnulla.setBounds(271, 218, 117, 29);
		frame.getContentPane().add(btnAnnulla);
	}

}
