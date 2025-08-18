package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu {

	static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMultiscreen = new JLabel("MultiScreen");
		lblMultiscreen.setForeground(Color.RED);
		lblMultiscreen.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblMultiscreen.setBounds(138, 6, 183, 64);
		frame.getContentPane().add(lblMultiscreen);
		
		JButton btnAcquistaBiglietto = new JButton("Acquista Biglietto");
		btnAcquistaBiglietto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
					frame.setVisible(false);
				} catch (Exception exc) {
					System.out.println("Errore nella creazione della finestra");
					//exc.printStackTrace();
				}
			}
		});
		btnAcquistaBiglietto.setBounds(96, 187, 270, 29);
		frame.getContentPane().add(btnAcquistaBiglietto);
		
		JButton btnRicercaFilm = new JButton("Ricerca film");
		btnRicercaFilm.setBounds(96, 83, 270, 29);
		frame.getContentPane().add(btnRicercaFilm);
		
		JButton btnVisualizzaProgrammazioneMensile = new JButton("Visualizza programmazione mensile");
		btnVisualizzaProgrammazioneMensile.setBounds(96, 134, 270, 29);
		frame.getContentPane().add(btnVisualizzaProgrammazioneMensile);
	}

}
