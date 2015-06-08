package CapaAplicacio;

import java.util.ArrayList;

import Domini.Prestec;
import Persistencia.BBDDPrestec;
import Persistencia.ConnexioJPA;

public class controladorFerPagament {
	private ConnexioJPA connexio;
	private BBDDPrestec prestecBBDD;

	public controladorFerPagament()throws Exception{
		connexio = ConnexioJPA.getInstancia();
		prestecBBDD = new BBDDPrestec();
	}
	
	public ArrayList<Prestec> getPrestecsPerPagar(String soci) throws Exception{
		return prestecBBDD.findPrestecsPerPagar(soci);
	}

	public void pagarPrestec(Prestec prestec) {
		prestecBBDD.pagarPrestec(prestec);
	}
}
