package Presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import CapaAplicacio.controladorFerPrestec;
import CapaAplicacio.controladorLogin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class ferPrestec extends JFrame{

	private JFrame frame;
	private JTextField fieldExemplarDemanat;
	private JTextField fieldSoci;
	private controladorFerPrestec cont;
	private controladorLogin controladorLogin;

	/**
	 * Create the application.
	 */
	public ferPrestec() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFerPrstec = new JLabel("Fer PrÃ©stec");
		lblFerPrstec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFerPrstec.setBounds(174, 26, 85, 14);
		frame.getContentPane().add(lblFerPrstec);
		
		JLabel lblExemplarDemanat = new JLabel("Exemplar demanat: ");
		lblExemplarDemanat.setBounds(71, 100, 128, 14);
		frame.getContentPane().add(lblExemplarDemanat);
		
		fieldExemplarDemanat = new JTextField();
		fieldExemplarDemanat.setBounds(209, 97, 114, 20);
		frame.getContentPane().add(fieldExemplarDemanat);
		fieldExemplarDemanat.setColumns(10);
		
		JLabel lblSoci = new JLabel("Soci: ");
		lblSoci.setBounds(71, 135, 114, 14);
		frame.getContentPane().add(lblSoci);
		
		fieldSoci = new JTextField();
		fieldSoci.setBounds(209, 132, 114, 20);
		frame.getContentPane().add(fieldSoci);
		fieldSoci.setColumns(10);
		
		cont=new controladorFerPrestec();
		
		JButton btnFerPrstec = new JButton("FER PRÉSTEC");
		btnFerPrstec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Date data = cont.enregistrarPrestec(fieldSoci.getText(), fieldExemplarDemanat.getText());
					JOptionPane.showMessageDialog(new JFrame(), "Préstec inserit correctament a la Base de dades. \n Ultim dia de retorn: "+data.toString(), "Inserció Correcte",
					        JOptionPane.PLAIN_MESSAGE);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFerPrstec.setBounds(54, 194, 114, 23);
		frame.getContentPane().add(btnFerPrstec);
		
		JButton btnEnrere = new JButton("ENRERE");
		btnEnrere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new seleccio();
				dispose();
			}
		});
		btnEnrere.setBounds(229, 194, 114, 23);
		frame.getContentPane().add(btnEnrere);
	}
}
