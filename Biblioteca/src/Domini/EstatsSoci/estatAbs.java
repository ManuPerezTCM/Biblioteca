package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public abstract class estatAbs {

	public abstract estatAbs demanarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs tornarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public boolean prestecValid(Soci soci, Exemplar exemplar) throws Exception {	
		if(soci.getDataBaixa()!=null||!exemplar.disponible()){
			return false;
		}
		if(soci.getEstatObj().toString().equals("Moros")){
			return false;
		}
		else{
			if(soci.getEstatObj().toString().equals("SensePrestec")){
				return true;
			}
			else{
				if(soci.getListPrestecs().size()>2){
					return false;
				}
				for(int i=0;i<soci.getListPrestecs().size();i++){
					if(soci.getListPrestecs().get(i).getExemplar().getObra().equals(exemplar.getObra())){
						return false;
					}
				}
				return true;
			}
		}
	}	
}
