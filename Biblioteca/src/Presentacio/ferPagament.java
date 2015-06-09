package Presentacio;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import CapaAplicacio.controladorFerPagament;
import Domini.Prestec;


public class ferPagament extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSoci;
	private JList list;
	private controladorFerPagament controladorFerPagament;	
	private DefaultListModel model;
	private ArrayList<Prestec> perPagar;
	private JLabel lblImport;
	
	public ferPagament() {
		try {
			controladorFerPagament = new controladorFerPagament();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		setTitle("Fer Pagament de Pr\u00E9stec");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setVisible(true);

		JLabel lblSoci = new JLabel("Introdueix un Soci: ");
		lblSoci.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textFieldSoci = new JTextField();
		textFieldSoci.setColumns(10);
	
		
		JLabel lblSeleccionaElPrstec = new JLabel("Selecciona el pr\u00E9stec a pagar:");
		
		JLabel lblImportACobrar = new JLabel("Import a cobrar:");
		
		lblImport = new JLabel("");
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		JButton btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					perPagar = controladorFerPagament.getPrestecsPerPagar(textFieldSoci.getText());
					model = new DefaultListModel<Prestec>();
					for(Prestec prestec: perPagar){
						model.addElement(prestec.getExemplar().getObra().getTitol());
					}
					list.setModel(model);
				} catch (Exception e1) {
					tirarError(e1.getMessage());
				}
			}
		});
		
		JButton btnMostrarImport = new JButton("Mostrar Import");
		btnMostrarImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.isSelectionEmpty())
					tirarError("No has escollit cap préstec per mostrar.");
				else
				lblImport.setText(perPagar.get(list.getSelectedIndex()).getImportRetard().toString());
			}
		});
		
		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.isSelectionEmpty())
					tirarError("No has escollit cap préstec per cobrar.");
				else
					try {
						controladorFerPagament.pagarPrestec(perPagar.get(list.getSelectedIndex()));
					} catch (Exception e) {
						tirarError(e.getMessage());
					}			}
		});
		
		JButton btnEnrere = new JButton("Enrere");
		btnEnrere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(71, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEnrere)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblSoci)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textFieldSoci, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblSeleccionaElPrstec, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblImportACobrar)
											.addGap(8))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnMostrarImport, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblImport, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnValidar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btnCobrar))))
					.addGap(27))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnValidar)
						.addComponent(textFieldSoci, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSoci))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSeleccionaElPrstec)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblImportACobrar)
								.addComponent(lblImport, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnMostrarImport)
								.addComponent(btnCobrar))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEnrere)
					.addGap(10))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void tirarError(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	private void tornarEnrere(){
		this.dispose();
		new seleccio();
	}
}
