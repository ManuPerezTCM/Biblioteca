package Presentacio;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import CapaAplicacio.controladorEndarrerirDataPrestec;

import java.awt.Color;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.List;

public class endarrerirDataPrestec extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIntrodueixSoci;
	private JTextField textFieldIntrodueixDies;
	private JLabel lblIntrodueixUnSoci;
	private controladorEndarrerirDataPrestec cont;
	private JLabel lblTitol;
	private JButton btnOkSoci;
	private JLabel lblSeleccionaUnPrestec;
	private List listPrestecsSoci;
	private JLabel lblQuantsDies;
	private JButton btnEndarrerir;
	/**
	 * Create the frame.
	 */
	
	public endarrerirDataPrestec() {
		initialize();
		this.setVisible(true);
	}
	
	private void initialize() {
		this.cont=new controladorEndarrerirDataPrestec();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIntrodueixUnSoci = new JLabel("Introdueix un Soci:");
		lblIntrodueixUnSoci.setBounds(10, 55, 127, 14);
		contentPane.add(lblIntrodueixUnSoci);
		
		lblTitol = new JLabel("Endarrerir data d'un pr\u00E8stec");
		lblTitol.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitol.setBounds(119, 11, 267, 14);
		contentPane.add(lblTitol);
		
		textFieldIntrodueixSoci = new JTextField();
		textFieldIntrodueixSoci.setBounds(147, 52, 126, 20);
		contentPane.add(textFieldIntrodueixSoci);
		textFieldIntrodueixSoci.setColumns(10);
		
		btnOkSoci = new JButton("OK");
		btnOkSoci.setBounds(297, 51, 89, 23);
		contentPane.add(btnOkSoci);
		
		lblSeleccionaUnPrestec = new JLabel("Selecciona un pr\u00E8stec per endarrerir");
		lblSeleccionaUnPrestec.setEnabled(false);
		lblSeleccionaUnPrestec.setBounds(10, 91, 252, 14);
		contentPane.add(lblSeleccionaUnPrestec);
		
		
		lblQuantsDies = new JLabel("Quants dies?");
		lblQuantsDies.setEnabled(false);
		lblQuantsDies.setBounds(177, 160, 89, 14);
		contentPane.add(lblQuantsDies);
		
		textFieldIntrodueixDies = new JTextField();
		textFieldIntrodueixDies.setEnabled(false);
		textFieldIntrodueixDies.setBounds(276, 157, 32, 20);
		contentPane.add(textFieldIntrodueixDies);
		textFieldIntrodueixDies.setColumns(10);
		
		listPrestecsSoci = new List();
		listPrestecsSoci.setEnabled(false);
		listPrestecsSoci.setBounds(10, 111, 145, 111);
		contentPane.add(listPrestecsSoci);
		
		btnEndarrerir = new JButton("Endarrerir");
		btnEndarrerir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String obra=listPrestecsSoci.getSelectedItem();
				if(textFieldIntrodueixDies.getText().equals("")||obra==null){
					JOptionPane.showMessageDialog(new JFrame(), "Selecciona un nombre de dies i una obra, siusplau", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				else{
					int dies=Integer.parseInt(textFieldIntrodueixDies.getText());
					try {
						cont.endarrerirPrestec(obra, dies);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEndarrerir.setEnabled(false);
		btnEndarrerir.setBounds(177, 188, 107, 23);
		contentPane.add(btnEndarrerir);
		
		JButton btnEnrere = new JButton("ENRERE");
		btnEnrere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new seleccio();
				dispose();
			}
		});
		btnEnrere.setBounds(319, 228, 89, 23);
		contentPane.add(btnEnrere);
		
		
		
		
		
		
		btnOkSoci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[]obresSoci=cont.getPrestecsActiusSoci(textFieldIntrodueixSoci.getText());
					for(int i=0;i<obresSoci.length;i++){
						listPrestecsSoci.add(obresSoci[i]);
					}
					lblIntrodueixUnSoci.setEnabled(false);
					textFieldIntrodueixSoci.setEnabled(false);
					btnOkSoci.setEnabled(false);
					lblSeleccionaUnPrestec.setEnabled(true);
					listPrestecsSoci.setEnabled(true);
					lblQuantsDies.setEnabled(true);
					textFieldIntrodueixDies.setEnabled(true);
					btnEndarrerir.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		
		
	}
}
