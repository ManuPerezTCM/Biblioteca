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
		this.biblioteca = controladorLogin.getBiblioteca();
	}

	// String soci �s el seu DNI i String exemplar �s el REGISTRE
	public void enregistrarPrestec(String soci, String exemplar)
			throws Exception {
		if (soci.equals("") || exemplar.equals("")) {
			throw new Exception(
					"Introdueixi un soci i un exemplar per realitzar el pr�stec");
		}
		bbddPrestec = new BBDDPrestec();
		bbddSoci = new BBDDSoci();
		bbddExemplar = new BBDDExemplar();
		sociObj = bbddSoci.find(soci);
		if (sociObj == null) {
			throw new Exception("El soci seleccionat no existeix.");
		}
		exemplarObj = bbddExemplar.find(Long.parseLong(exemplar));
		if (exemplarObj == null) {
			throw new Exception("L'exemplar seleccionat no existeix.");
		}
		if (bbddSoci.potDemanarPrestec(soci, exemplar)
				&& exemplarObj.disponible()
				&& !bbddExemplar.estaEnPrestec(exemplar)) {
			data_prestec = new Date();
			data_max_retorn = new Date();
			Calendar calendar = GregorianCalendar.getInstance();

			calendar.setTime(data_max_retorn);
			calendar.add(calendar.DAY_OF_MONTH, biblioteca.getDiesDePrestec().intValue());
			data_max_retorn = calendar.getTime();

			PrestecPK prestecPK = new PrestecPK();
			prestecPK.setDataPrestec(data_prestec);
			prestecPK.setSoci(soci);

			prestec = new Prestec();
			prestec.setId(prestecPK);
			prestec.setExemplar(exemplarObj);
			prestec.setSoci(sociObj);
			prestec.setDataMaxRetorn(data_max_retorn);
			this.bbddPrestec.afegirPrestec(this.prestec);
		}
	}
}
