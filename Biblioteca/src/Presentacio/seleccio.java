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

import CapaAplicacio.controladorLogin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class seleccio extends JFrame {

	private JPanel contentPane;

	public seleccio() {
		this.setVisible(true);
		setTitle("Mini Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNouPrstec = new JButton("Nou Pr\u00E9stec");
		btnNouPrstec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ferPrestec();
			}
		});

		JButton btnTornarPrstec = new JButton("Tornar Pr\u00E9stec");
		btnTornarPrstec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnEndarrerirPrstec = new JButton("Endarrerir Pr\u00E9stec");
		btnEndarrerirPrstec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnPagarPrstec = new JButton("Pagar Pr\u00E9stec");
		btnPagarPrstec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ferPagament();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(25)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																btnPagarPrstec,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																152,
																Short.MAX_VALUE)
														.addComponent(
																btnEndarrerirPrstec,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																152,
																Short.MAX_VALUE)
														.addComponent(
																btnTornarPrstec,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																152,
																Short.MAX_VALUE)
														.addComponent(
																btnNouPrstec,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(27)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addGap(31)
						.addComponent(btnNouPrstec)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnTornarPrstec)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnEndarrerirPrstec)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnPagarPrstec)
						.addContainerGap(24, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	private void ferPrestec() {
		try {
			new ferPrestec();
			this.dispose();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
	}
	
	private void ferPagament(){
		try {
			new ferPagament();
			this.dispose();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
	}
	
	private void tirarError(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
