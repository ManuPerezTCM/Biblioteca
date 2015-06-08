package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.INITIALIZE;

import CapaAplicacio.controladorRetornPrestec;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ferRetorn extends JFrame {

	private controladorRetornPrestec ctlRP;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField JTextExemplar;
	private JLabel JlblExemplar;
	private JLabel JlblTitol;
	private JButton btnNotificarDevolucio;
	/**
	 * Create the frame.
	 */
	public ferRetorn() {		
		initialize();
		this.setVisible(true);
	}
	
	private void initialize(){
		
		ctlRP = new controladorRetornPrestec();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JTextExemplar = new JTextField();
		JTextExemplar.setText("");
		JTextExemplar.setBounds(163, 82, 124, 20);
		contentPane.add(JTextExemplar);
		JTextExemplar.setColumns(10);
		
		JlblExemplar = new JLabel("Exemplar a Retornar");
		JlblExemplar.setBounds(53, 85, 100, 14);
		contentPane.add(JlblExemplar);
		
		JlblTitol = new JLabel("Retorn Prestec");
		JlblTitol.setFont(new Font("Traditional Arabic", Font.PLAIN, 17));
		JlblTitol.setBounds(137, 11, 124, 40);
		contentPane.add(JlblTitol);
		
		btnNotificarDevolucio = new JButton("Notificar Devoluci\u00F3");
		btnNotificarDevolucio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonResponse();
				
			}
		});
		btnNotificarDevolucio.setBounds(163, 150, 124, 40);
		contentPane.add(btnNotificarDevolucio);
		
	}
	
	private void buttonResponse(){
		try {
			this.ctlRP.retornDinsTermini(this.JTextExemplar.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
		new confirmarRetorn(this.ctlRP, this);
	}
}
