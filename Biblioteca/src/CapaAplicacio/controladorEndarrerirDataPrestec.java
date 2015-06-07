package CapaAplicacio;

import java.util.ArrayList;

import Domini.Prestec;
import Domini.Soci;
import Persistencia.BBDDPrestec;
import Persistencia.BBDDSoci;

public class controladorEndarrerirDataPrestec {
	private Soci soci;
	private BBDDSoci bbddsoci=new BBDDSoci();
	private BBDDPrestec bbddprestec=new BBDDPrestec();
	
	public controladorEndarrerirDataPrestec(){
		
	}
	
	public boolean SociValid(String soci) throws Exception{
		comprobarDNI(soci);
		BBDDSoci bbddSoci=new BBDDSoci();
		return bbddSoci.find(soci)!=null;
	}
	
	private void comprobarDNI(String soci) throws Exception{
		String lletra = "TRWAGMYFPDXBNJZSQVHLCKE";
		if(soci.equals("")){
			throw new Exception(
					"Introdueix un DNI, siusplau");
		}
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
	
	public String[]getPrestecsActiusSoci(String soci) throws Exception{
		this.soci=this.bbddsoci.find(soci);
		ArrayList<Prestec>llistaPrestecs=this.bbddprestec.findPrestecsSoci(this.soci);
		String toReturn[] = new String[llistaPrestecs.size()];
		for(int i=0;i<llistaPrestecs.size();i++){
			toReturn[i]=llistaPrestecs.get(i).getExemplar().getObra().getTitol();
		}
		return toReturn;
	}
	
	public void endarrerirPrestec(String obra, int dies)throws Exception{
		if(dies<1){
			throw new Exception("Nombre de d�es no v�lid. Ha de ser m�s gran o igual a 1");
		}
		/*BBDDObra bbddobra=new BBDDObra();
		this.*/
	}
	
	
}
