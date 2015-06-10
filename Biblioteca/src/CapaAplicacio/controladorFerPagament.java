package CapaAplicacio;

import java.util.ArrayList;

import Domini.Prestec;
import Persistencia.BBDDPrestec;
import Persistencia.BBDDSoci;
import Persistencia.ConnexioJPA;

public class controladorFerPagament {
	private ConnexioJPA connexio;
	private BBDDPrestec prestecBBDD;
	private BBDDSoci sociBBDD;

	public controladorFerPagament()throws Exception{
		connexio = ConnexioJPA.getInstancia();
		prestecBBDD = new BBDDPrestec();
		sociBBDD = new BBDDSoci();
	}
	
	public ArrayList<Prestec> getPrestecsPerPagar(String soci) throws Exception{
		return prestecBBDD.findPrestecsPerPagar(soci);
	}

	public void pagarPrestec(Prestec prestec) throws Exception {
		prestec.getSoci().setPrestecs(prestecBBDD.findPrestecsPerPagar(prestec.getSoci().getDni()));
		prestec.getSoci().pagarPrestec(prestec.getExemplar());
		prestecBBDD.pagarPrestec(prestec);
		sociBBDD.actualitzarSoci(prestec.getSoci());
	}
}
