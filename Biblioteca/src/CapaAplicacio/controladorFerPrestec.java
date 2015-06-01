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

	public controladorFerPrestec(controladorLogin controladorLogin) {
//		this.biblioteca = controladorLogin.getBiblioteca();
	}

	// String soci ï¿½s el seu DNI i String exemplar ï¿½s el REGISTRE
	public void enregistrarPrestec(String soci, String exemplar)
			throws Exception {
		if (soci.equals("") || exemplar.equals("")) {
			throw new Exception(
					"Introdueixi un soci i un exemplar per realitzar el prï¿½stec");
		}
		bbddPrestec = new BBDDPrestec();
		bbddSoci = new BBDDSoci();
		bbddExemplar = new BBDDExemplar();
		sociObj = bbddSoci.find(soci);
		if (sociObj == null) {
			throw new Exception("El soci seleccionat no existeix o està de baixa.");
		}
		exemplarObj = bbddExemplar.find(Long.parseLong(exemplar));
		if (exemplarObj == null) {
			throw new Exception("L'exemplar seleccionat no existeix.");
		}
		//if (bbddSoci.potDemanarPrestec(soci, exemplar)
		if (prestecPermes()) {
			
			PrestecPK prestecPK = new PrestecPK();
			prestecPK.setDataPrestec(data_prestec);
			prestecPK.setSoci(soci);

			prestec = new Prestec();
			prestec.setId(prestecPK);
			prestec.setExemplar(exemplarObj);
			prestec.setSoci(sociObj);
			prestec.setDataMaxRetorn(data_max_retorn);
			
			this.bbddPrestec.afegirPrestec(this.prestec);
			sociObj.demanarPrestec(exemplarObj);
			bbddSoci.prestecAfegit(prestec);
		}
	}
	private boolean prestecPermes() throws Exception{
		return sociObj.potDemanarPrestec()
				&& !bbddSoci.sociTeObra(exemplarObj, sociObj)
				&& exemplarObj.disponible()
				&& bbddExemplar.disponible(exemplarObj);
	}
}
