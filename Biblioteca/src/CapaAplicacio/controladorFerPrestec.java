package CapaAplicacio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;

public class controladorFerPrestec {
	
	private Soci sociObj;
	private Date data_prestec;
	private Date data_max_retorn;
	private Exemplar exemplarObj;
	
	private BBDDPrèstec bbddPrestec;
	private BBDDSoci bbddSoci;
	private BBDDExemplar bbddExemplar;

	private Prestec prestec;
	
	public controladorFerPrestec(){
		
	}
	
	public boolean enregistrarPrestec(String soci, String exemplar){
		if (soci.equals("") || exemplar.equals("")){
			throw new Exception("Introdueixi un soci i un exemplar per realitzar el prèstec");
		}
		
		sociObj = bbddSoci.find(soci);
		exemplarObj = bbddExemplar.find(exemplar);
				
		if(sociObj.fiable() && exemplarObj.disponible()){			
			
			data_max_retorn = new Date();
			Calendar calendar;
			//S'HA DE CAMBIAR PER FER QUE AGAFI EL VALOR DE DIES DE LA BBDD
			calendar.add(data_max_retorn.getDay(), 5);//afegim el temps especificat. 
			
			prestec = new Prestec();
			prestec.setSoci(sociObj);
			prestec.setDataMaxRetorn(data_max_retorn);
			this.prestec.setDataPrestec(data_prestec);
			
			return this.bbddPrestec.addPrestec(this.prestec);
			
		}
	}
}
