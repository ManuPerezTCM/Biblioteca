package Presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import CapaAplicacio.controladorFerPrestec;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ferPrestec {

	private JFrame frame;
	private JTextField fieldExemplarDemanat;
	private JTextField fieldSoci;
	private controladorFerPrestec cont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ferPrestec window = new ferPrestec();
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
	public ferPrestec() {
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
		
		JLabel lblFerPrstec = new JLabel("Fer Pr\u00E8stec");
		lblFerPrstec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFerPrstec.setBounds(174, 26, 85, 14);
		frame.getContentPane().add(lblFerPrstec);
		
		JLabel lblExemplarDemanat = new JLabel("Exemplar demanat: ");
		lblExemplarDemanat.setBounds(71, 100, 105, 14);
		frame.getContentPane().add(lblExemplarDemanat);
		
		fieldExemplarDemanat = new JTextField();
		fieldExemplarDemanat.setBounds(209, 97, 114, 20);
		frame.getContentPane().add(fieldExemplarDemanat);
		fieldExemplarDemanat.setColumns(10);
		
		JLabel lblSoci = new JLabel("Soci: ");
		lblSoci.setBounds(71, 135, 46, 14);
		frame.getContentPane().add(lblSoci);
		
		fieldSoci = new JTextField();
		fieldSoci.setBounds(209, 132, 114, 20);
		frame.getContentPane().add(fieldSoci);
		fieldSoci.setColumns(10);
		
		cont=new controladorFerPrestec();
		
		JButton btnFerPrstec = new JButton("FER PR\u00C8STEC");
		btnFerPrstec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont.enregistrarPrestec(fieldSoci.getText(), fieldExemplarDemanat.getText());
			}
		});
		btnFerPrstec.setBounds(54, 194, 114, 23);
		frame.getContentPane().add(btnFerPrstec);
		
		JButton btnEnrere = new JButton("ENRERE");
		btnEnrere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEnrere.setBounds(229, 194, 114, 23);
		frame.getContentPane().add(btnEnrere);
	}
}
