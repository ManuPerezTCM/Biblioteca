package Presentacio;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import CapaAplicacio.controladorEndarrerirDataPrestec;

public class endarrerirDataPrestec extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIntrodueixSoci;
	private JTextField textFieldIntrodueixDies;
	private JLabel lblIntrodueixUnSoci;
	private controladorEndarrerirDataPrestec cont;
	private JLabel lblTitol;
	private JButton btnOkSoci;
	private JLabel lblSeleccionaUnPrestec;
	private DefaultListModel <String>listPrestecsSoci;
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
		lblSeleccionaUnPrestec.setBounds(35, 91, 252, 14);
		contentPane.add(lblSeleccionaUnPrestec);
		
		String[] data = {"one", "two", "three", "four"};
		listPrestecsSoci = new DefaultListModel();
		/*listPrestecsSoci.setEnabled(true);
		listPrestecsSoci.setBounds(45, 194, 104, -58);
		listPrestecsSoci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);*/
		
		//contentPane.add(listPrestecsSoci);
		
		lblQuantsDies = new JLabel("Quants dies?");
		lblQuantsDies.setEnabled(false);
		lblQuantsDies.setBounds(177, 160, 89, 14);
		contentPane.add(lblQuantsDies);
		
		textFieldIntrodueixDies = new JTextField();
		textFieldIntrodueixDies.setEnabled(false);
		textFieldIntrodueixDies.setBounds(276, 157, 32, 20);
		contentPane.add(textFieldIntrodueixDies);
		textFieldIntrodueixDies.setColumns(10);
		
		btnEndarrerir = new JButton("Endarrerir");
		btnEndarrerir.setEnabled(false);
		btnEndarrerir.setBounds(177, 188, 107, 23);
		contentPane.add(btnEndarrerir);
		
		btnOkSoci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cont.SociValid(textFieldIntrodueixSoci.getText())){
						lblIntrodueixUnSoci.setEnabled(false);
						textFieldIntrodueixSoci.setEnabled(false);
						btnOkSoci.setEnabled(false);
						lblSeleccionaUnPrestec.setEnabled(true);
						//listPrestecsSoci.setEnabled(true);
						lblQuantsDies.setEnabled(true);
						textFieldIntrodueixDies.setEnabled(true);
						btnEndarrerir.setEnabled(true);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
	}
}
