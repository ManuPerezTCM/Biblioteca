package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import CapaAplicacio.controladorEndarrerirDataPrestec;

public class endarrerirDataPrestec extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIntrodueixSoci;
	private JTextField textFieldIntrodueixDies;
	private controladorEndarrerirDataPrestec cont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					endarrerirDataPrestec frame = new endarrerirDataPrestec();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public endarrerirDataPrestec() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntrodueixUnSoci = new JLabel("Introdueix un Soci:");
		lblIntrodueixUnSoci.setBounds(33, 55, 104, 14);
		contentPane.add(lblIntrodueixUnSoci);
		
		JLabel lblTitol = new JLabel("Endarrerir data d'un pr\u00E8stec");
		lblTitol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitol.setBounds(119, 11, 168, 14);
		contentPane.add(lblTitol);
		
		textFieldIntrodueixSoci = new JTextField();
		textFieldIntrodueixSoci.setBounds(147, 52, 126, 20);
		contentPane.add(textFieldIntrodueixSoci);
		textFieldIntrodueixSoci.setColumns(10);
		
		JButton btnOkSoci = new JButton("OK");
		btnOkSoci.setBounds(297, 51, 89, 23);
		contentPane.add(btnOkSoci);
		
		JLabel lblSeleccionaUnPrstec = new JLabel("Selecciona un pr\u00E8stec per endarrerir");
		lblSeleccionaUnPrstec.setBounds(35, 91, 182, 14);
		contentPane.add(lblSeleccionaUnPrstec);
		
		JList listPrestecsSoci = new JList();
		listPrestecsSoci.setBounds(45, 194, 104, -58);
		contentPane.add(listPrestecsSoci);
		
		JLabel lblQuantsDies = new JLabel("Quants dies?");
		lblQuantsDies.setBounds(195, 160, 71, 14);
		contentPane.add(lblQuantsDies);
		
		textFieldIntrodueixDies = new JTextField();
		textFieldIntrodueixDies.setBounds(276, 157, 32, 20);
		contentPane.add(textFieldIntrodueixDies);
		textFieldIntrodueixDies.setColumns(10);
		
		JButton btnEndarrerir = new JButton("Endarrerir");
		btnEndarrerir.setBounds(195, 188, 89, 23);
		contentPane.add(btnEndarrerir);
	}
}
