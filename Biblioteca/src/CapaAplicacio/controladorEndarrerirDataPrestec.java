package CapaAplicacio;

import java.util.ArrayList;

import Domini.Obra;
import Domini.Prestec;
import Domini.Soci;
import Persistencia.BBDDPrestec;
import Persistencia.BBDDSoci;

public class controladorEndarrerirDataPrestec {
	private Prestec prestecPerEndarrerir;
	private String dniSoci;
	private BBDDSoci bbddsoci=new BBDDSoci();
	private BBDDPrestec bbddprestec=new BBDDPrestec();
	
	public controladorEndarrerirDataPrestec(){
		
	}
	
	
	public String[]getPrestecsActiusSoci(String soci) throws Exception{
		this.dniSoci=soci;
		ArrayList<Prestec>llistaPrestecs=this.bbddprestec.findPrestecsSoci(soci);
		String toReturn[] = new String[llistaPrestecs.size()];
		for(int i=0;i<llistaPrestecs.size();i++){
			toReturn[i]=llistaPrestecs.get(i).getExemplar().getObra().getTitol();
		}
		return toReturn;
	}
	
	public void endarrerirPrestec(String obra, int dies)throws Exception{
		this.prestecPerEndarrerir=this.bbddprestec.getPrestecPerEndarrerir(obra,this.dniSoci);
		this.bbddprestec.endarrerirPrestec(this.prestecPerEndarrerir, dies);
	}
	
	
}
