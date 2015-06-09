//Simplement substitu�r tot el codi de la classe
package CapaAplicacio;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.persistence.internal.helper.SimpleDatabaseType;

import Domini.Biblioteca;
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
	private Biblioteca biblioteca;
	private Date avui;
	
	public controladorRetornPrestec(){
		this.bbddExemplar = new BBDDExemplar();
		this.bbddPrestec = new BBDDPrestec();
		this.deutePrestec = 0;
		biblioteca = Biblioteca.getInstancia();
	}
	
	public void retornDinsTermini(String retorn) throws NumberFormatException, Exception{
		
		if(retorn.equals("")){ throw new Exception("Per favor, introdue�xi un ISBN");}
	
		exemplar = this.bbddExemplar.find(Long.parseLong(retorn));		
		if(exemplar == null){throw new Exception("Exemplar no trobat a la BBDD");}
		
		prestec = this.bbddPrestec.find(exemplar);//retorna el prestec d'aquest exemplar		
		if(prestec == null){throw new Exception("Prestec no trobat a la BBDD");}
		
		Date dataMaxRetorn = prestec.getDataMaxRetorn();		
		avui = new Date();
		if(avui.compareTo(dataMaxRetorn) >0){ //prestec fora del termini
			 int diferenciaDies = (int) ((avui.getTime() - dataMaxRetorn.getTime())/1000/60/60/24);		
			 deutePrestec = (diferenciaDies * Float.parseFloat(biblioteca.getImportPerDiaRetard().toString()));
		}		
	}
	
	public void confirmarRetorn() throws Exception{
		if(prestec == null){
			throw new Exception("No s'ha indicat cap prestec a retornar");
		}
		if(this.deutePrestec > 0){
			prestec.setImportRetard(BigDecimal.valueOf(this.deutePrestec));
		}
		System.out.println("2");
		if(prestec.getSoci() == null){System.out.println("SOCI NULL");}
		prestec.getSoci().tornarPrestec(exemplar);
		System.out.println("3");
		prestec.setDataRealRetorn(avui);
		System.out.println("4");
		this.bbddPrestec.retornarPrestec(prestec);
		System.out.println("5");
	}
	
	public void negarRetorn(){
		this.prestec = null;
		this.exemplar = null;
		this.deutePrestec = 0;
	}
	
	public float getDeutePrestec(){
		return this.deutePrestec;
	}
	
	public String getNomExemplar(){
		return this.exemplar.getObra().getTitol();
	}
}