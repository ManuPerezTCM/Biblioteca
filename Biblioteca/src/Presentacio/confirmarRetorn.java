package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import CapaAplicacio.controladorRetornPrestec;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class confirmarRetorn extends JFrame {
	
	private JPanel contentPane;
	private JLabel lblExemplar;
	private JLabel valueExemplar;
	private JLabel lblImportAPagar;
	private JLabel valueImportAPagar;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	
	private controladorRetornPrestec cltRP;
	private ferRetorn pantallaAnterior;
	
	/**
	 * Create the frame.
	 */
	public confirmarRetorn(controladorRetornPrestec cltRP, ferRetorn pantalla) {
		this.cltRP = cltRP;
		pantallaAnterior = pantalla;		
		initialize();
		this.setVisible(true);
	}
	
	private void initialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblExemplar = new JLabel("Exemplar:");
		lblExemplar.setBounds(61, 34, 57, 14);
		contentPane.add(lblExemplar);
		
		valueExemplar = new JLabel(this.cltRP.getNomExemplar());
		valueExemplar.setBounds(128, 34, 90, 14);
		contentPane.add(valueExemplar);
		
		lblImportAPagar = new JLabel("Import a pagar:");
		lblImportAPagar.setBounds(61, 70, 90, 14);
		contentPane.add(lblImportAPagar);
		
		
		valueImportAPagar = new JLabel(String.valueOf(cltRP.getDeutePrestec()));
		valueImportAPagar.setBounds(161, 70, 46, 14);
		contentPane.add(valueImportAPagar);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmar();
			}
		});
		btnConfirmar.setBounds(61, 127, 89, 23);
		contentPane.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setBounds(241, 127, 89, 23);
		contentPane.add(btnCancelar);
	}
	private void confirmar(){
		try {
			this.cltRP.confirmarRetorn();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage()+ " ", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
			new seleccio();
			this.cltRP.negarRetorn();
			this.dispose();
		}
		new seleccio();
		this.pantallaAnterior.dispose();
		this.dispose();
	}
	private void cancelar(){
		this.cltRP.negarRetorn();
		this.dispose();
	}	
}
