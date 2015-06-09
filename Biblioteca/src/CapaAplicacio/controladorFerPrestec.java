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

/*
 el numero de dies es canvia quan es dona OK a fer prestec desde la pantalla
 el prestec que dona el soci es queda guardat com a atribut
 */
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

	// String soci ï¿½s el seu DNI i String exemplar ï¿½s el REGISTRE
	public Date enregistrarPrestec(String soci, String exemplar)
			throws Exception {
		if (soci.equals("") || exemplar.equals("")) {
			throw new Exception(
					"Introdueixi un soci i un exemplar per realitzar el prï¿½stec");
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
		// if (bbddSoci.potDemanarPrestec(soci, exemplar)
		if (sociObj.potDemanarPrestec(exemplarObj)) {

			PrestecPK prestecPK = new PrestecPK();
			prestecPK.setDataPrestec(data_prestec);
			prestecPK.setSoci(soci);

			prestec = new Prestec();
			prestec.setId(prestecPK);
			prestec.setExemplar(exemplarObj);
			prestec.setSoci(sociObj);

			this.bbddPrestec.afegirPrestec(this.prestec);
			sociObj.demanarPrestec(exemplarObj);//QUE CONY? Això és el que ha de comprovar si el prèstec és pot fer o no
			bbddSoci.prestecAfegit(prestec);
		}
		return prestec.getDataMaxRetorn();
	}

	/*private boolean prestecPermes() throws Exception { //aquesta funcio d'aqui la té que fer el patro estats
		return sociObj.potDemanarPrestec(this.exemplarObj)
				&& !bbddSoci.sociTeObra(exemplarObj, sociObj)
				&& exemplarObj.disponible()
				&& bbddExemplar.disponible(exemplarObj);
	}*/
	
	private void comprobarDNI(String soci) throws Exception{
		String lletra = "TRWAGMYFPDXBNJZSQVHLCKE";
		if (soci.length() != 9) {
			for (int i = 0; i < 8; i++) {
				if (Character.isDigit(soci.charAt(i)))
					throw new Exception(
							"DNI No vàlid. Introdueix 8 nombres i una lletra");
			}
			Integer valor = new Integer(soci.substring(0, 8));
			int aux = valor % 23;
			if(soci.charAt(8)!=lletra.charAt(aux))
				throw new Exception("DNI No Vàlid. No concorda la lletra amb el DNI");
		}
	}
}
