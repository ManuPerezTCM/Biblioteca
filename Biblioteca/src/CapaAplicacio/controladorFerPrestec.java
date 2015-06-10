package CapaAplicacio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Domini.Biblioteca;
import Domini.Exemplar;
import Domini.Prestec;
import Domini.PrestecPK;
import Domini.Soci;
import Persistencia.BBDDExemplar;
import Persistencia.BBDDPrestec;
import Persistencia.BBDDSoci;

public class controladorFerPrestec {

	private Soci sociObj;
	private Date data_prestec;
	private Date data_max_retorn;
	private Exemplar exemplarObj;

	private BBDDPrestec bbddPrestec;
	private BBDDSoci bbddSoci;
	private BBDDExemplar bbddExemplar;

	private Prestec prestec;
	private Biblioteca biblioteca;

	public controladorFerPrestec() {
		this.biblioteca = biblioteca.getInstancia();
		bbddPrestec = new BBDDPrestec();
		bbddSoci = new BBDDSoci();
		bbddExemplar = new BBDDExemplar();
	}

	// String soci �s el seu DNI i String exemplar �s el REGISTRE
	public Date enregistrarPrestec(String soci, String exemplar)
			throws Exception {
		if (soci.equals("") || exemplar.equals("")) {
			throw new Exception(
					"Introdueixi un soci i un exemplar per realitzar el pr�stec");
		}
			
		this.comprobarDNI(soci);
		
		sociObj = bbddSoci.find(soci);
		if (sociObj == null) {
			throw new Exception(
					"El soci seleccionat no existeix.");
		}
		exemplarObj = bbddExemplar.find(Long.parseLong(exemplar));
		if (exemplarObj == null) {
			throw new Exception("L'exemplar seleccionat no existeix.");
		}
		this.bbddExemplar.disponible(exemplarObj);
		sociObj.afegirPrestec(exemplarObj);
		this.bbddSoci.actualitzarSoci(sociObj);
		for(int i=0;i<sociObj.getPrestecs().size();i++){
			if(sociObj.getPrestecs().get(i).getExemplar().equals(exemplarObj)){
				this.prestec=sociObj.getPrestecs().get(i);
			}
		}
		this.bbddPrestec.afegirPrestec(this.prestec);
		System.out.println(5);
		return prestec.getDataMaxRetorn();
	}
	
	private void comprobarDNI(String soci) throws Exception{
		String lletra = "TRWAGMYFPDXBNJZSQVHLCKE";
		if (soci.length() != 9) {
			for (int i = 0; i < 8; i++) {
				if (Character.isDigit(soci.charAt(i)))
					throw new Exception(
							"DNI No v�lid. Introdueix 8 nombres i una lletra");
			}
			Integer valor = new Integer(soci.substring(0, 8));
			int aux = valor % 23;
			if(soci.charAt(8)!=lletra.charAt(aux))
				throw new Exception("DNI No V�lid. No concorda la lletra amb el DNI");
		}
	}
}
