package CapaAplicacio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.persistence.internal.helper.SimpleDatabaseType;

import Domini.Exemplar;
import Domini.Prestec;
import Persistencia.BBDDExemplar;
import Persistencia.BBDDPrestec;

public class controladorRetornPrestec {

	private BBDDExemplar bbddExemplar;
	private BBDDPrestec bbddPrestec;
	
	private float deutePrestec;	
	private Prestec prestec;
	private Exemplar exemplar;
	
	public controladorRetornPrestec(){
		this.bbddExemplar = new BBDDExemplar();
		this.deutePrestec = 0;
	}
	
	public boolean retornDinsTermini(String retorn){
		
		exemplar = this.bbddExemplar.find(retorn);
		prestec = this.bbddPrestec.find(exemplar);//retorna el prestec d'aquest exemplar
		
		Date dataMaxRetorn = prestec.getDataMaxRetorn();
		Date avui = new Date();
		
		int comparacio = avui.compareTo(dataMaxRetorn);
		
		if(comparacio <= 0){ //prestec dins del termini
			return true;
		}
		else{ //prestec fora del termini			
			 int diferenciaDies = (int) (avui.getTime() - dataMaxRetorn.getTime())/1000/60/60/24;
		     deutePrestec = (float) (diferenciaDies * 0.5);
		     return false;
		}
	}
	
	public void confirmarRetorn(){
		if(prestec == null){
			throw new Exception("No s'ha indicat cap prestec a retornar");
		}
		if(this.deutePrestec > 0){
			prestec.setImportRetard(this.deutePrestec);
		}
		this.bbddPrestec.retornarPrestec(this.prestec);
	}
	
	public void negarRetorn(){
		this.prestec = null;
		this.exemplar = null;
		this.deutePrestec = 0;
	}
}
