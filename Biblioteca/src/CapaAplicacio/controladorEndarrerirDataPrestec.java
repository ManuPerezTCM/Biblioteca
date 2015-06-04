package CapaAplicacio;

import Domini.Soci;
import Persistencia.BBDDSoci;

public class controladorEndarrerirDataPrestec {
	private Soci soci;
	
	public controladorEndarrerirDataPrestec(){
		
	}
	
	public boolean SociValid(String soci) throws Exception{
		BBDDSoci bbddSoci=new BBDDSoci();
		return bbddSoci.find(soci)!=null;
	}
	
	
}
