package CapaAplicacio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Domini.Exemplar;
import Domini.Prestec;
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
	
	public controladorFerPrestec(){
		
	}
	
	//String soci �s el seu DNI i String exemplar �s el REGISTRE
	public void enregistrarPrestec(String soci, String exemplar) throws Exception{
		if (soci.equals("") || exemplar.equals("")){
			throw new Exception("Introdueixi un soci i un exemplar per realitzar el pr�stec");
		}
		bbddPrestec=new BBDDPrestec();
		bbddSoci=new BBDDSoci();
		bbddExemplar=new BBDDExemplar();
		sociObj = bbddSoci.find(soci);
		exemplarObj = bbddExemplar.find(exemplar);
				//fiable retorna un BOOLEAN indicant si al soci se li pot fer un pr�stec.
				//disponible retorna un BOOLEAN indicant si el exemplar est� disponible

		if(bbddSoci.potDemanarPrestec(soci, exemplar) && exemplarObj.disponible()&& !bbddExemplar.estaEnPrestec(exemplar)){
			
			data_max_retorn = new Date();
			Calendar calendar = null;
			//S'HA DE CAMBIAR PER FER QUE AGAFI EL VALOR DE DIES DE LA BBDD
			calendar.add(data_max_retorn.getDay(), 5);//afegim el temps especificat. 
			
			prestec = new Prestec();
			prestec.setSoci(sociObj);
			prestec.setDataMaxRetorn(data_max_retorn);
			this.prestec.setDataPrestec(data_prestec);
			
			this.bbddPrestec.afegirPrestec(this.prestec);			
		}
	}
}
