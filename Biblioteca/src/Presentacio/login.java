package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import CapaAplicacio.controladorLogin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldContrasenya;
	private JTextField textFieldNom;
	private controladorLogin controladorLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		try {
			controladorLogin = new controladorLogin();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblIniciar = new JLabel("Iniciar Sessió");

		JLabel lblNom = new JLabel("Nom: ");

		JLabel lblContrasenya = new JLabel("Contrasenya: ");

		JButton btnAcceptar = new JButton("Acceptar");

		textFieldContrasenya = new JTextField();
		textFieldContrasenya.setColumns(10);

		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(162)
																		.addComponent(
																				lblIniciar))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(48)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblContrasenya)
																						.addComponent(
																								lblNom))
																		.addGap(18)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								textFieldNom,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								textFieldContrasenya,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(154, Short.MAX_VALUE))
						.addGroup(
								Alignment.TRAILING,
								gl_contentPane.createSequentialGroup()
										.addContainerGap(273, Short.MAX_VALUE)
										.addComponent(btnAcceptar).addGap(50)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(16)
										.addComponent(lblIniciar)
										.addGap(69)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNom)
																		.addGap(31)
																		.addComponent(
																				lblContrasenya))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				textFieldNom,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				textFieldContrasenya,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED, 45,
												Short.MAX_VALUE)
										.addComponent(btnAcceptar).addGap(25)));
		contentPane.setLayout(gl_contentPane);

		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controladorLogin.comprovar(textFieldNom.getText(),
							textFieldContrasenya.getText());
					JOptionPane
							.showMessageDialog(
									new JFrame(),
									"Es troba a la BBDD aquest bibliotecari, aquesta pantalla es per assegurar-ho, ja canviarem l'action performed per que tiri una pantalla inicial",
									"Radical", JOptionPane.OK_OPTION);
				} catch (Exception e1) {
					tirarError(e1.getMessage());
				}
			}
		});
	}

	/**
	 * @Author Manu
	 * @param missatge
	 */
	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
