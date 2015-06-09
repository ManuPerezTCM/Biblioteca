//Simplement substituïr tot el codi de la classe
package CapaAplicacio;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.persistence.internal.helper.SimpleDatabaseType;

import Domini.Biblioteca;
import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;
import Persistencia.BBDDExemplar;
import Persistencia.BBDDPrestec;
import Persistencia.BBDDSoci;

public class controladorRetornPrestec {

	private BBDDExemplar bbddExemplar;
	private BBDDPrestec bbddPrestec;
	private BBDDSoci bbddSoci;
	
	private float deutePrestec;	
	private Prestec prestec;
	private Exemplar exemplar;
	private Biblioteca biblioteca;
	private Soci soci;
	private Date avui;
	
	public controladorRetornPrestec(){
		this.bbddExemplar = new BBDDExemplar();
		this.bbddPrestec = new BBDDPrestec();
		this.bbddSoci = new BBDDSoci();
		this.deutePrestec = 0;
		biblioteca = Biblioteca.getInstancia();
	}
	
	public void retornDinsTermini(String retorn) throws NumberFormatException, Exception{
		
		if(retorn.equals("")){ throw new Exception("Per favor, introdueïxi un ISBN");}
		try{
		exemplar = this.bbddExemplar.find(Long.parseLong(retorn));		
		}catch(Exception e){
			throw new Exception("Exemplar no trobat a la BBDD");
		}
		
		try{
		prestec = this.bbddPrestec.find(exemplar);//retorna el prestec d'aquest exemplar	
		}catch(Exception e){
			throw new Exception("Prestec no trobat a la BBDD");
		}
		
		soci = prestec.getSoci();
		soci.setPrestecs(bbddPrestec.findPrestecsSoci(soci.getDni()));//carreguem els prestecs de soci
		
		
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
		prestec.setDataRealRetorn(avui);
		soci.actualitzarPrestec(prestec);
		soci.tornarPrestec(exemplar);
		this.bbddSoci.actualitzarSoci(soci);
		this.bbddPrestec.retornarPrestec(prestec);
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