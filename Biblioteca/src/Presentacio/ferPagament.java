package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class ferPagament extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSoci;

	public ferPagament() {
		setTitle("Fer Pagament de Pr\u00E9stec");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSoci = new JLabel("Soci: ");
		lblSoci.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textFieldSoci = new JTextField();
		textFieldSoci.setColumns(10);
		
		JButton btnValidar = new JButton("Validar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(89, Short.MAX_VALUE)
					.addComponent(lblSoci)
					.addGap(18)
					.addComponent(textFieldSoci, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnValidar)
					.addGap(105))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSoci)
						.addComponent(textFieldSoci, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnValidar))
					.addContainerGap(214, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
