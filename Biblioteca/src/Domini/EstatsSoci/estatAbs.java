package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;

public abstract class estatAbs {

	public abstract estatAbs demanarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs tornarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	//AQUEST MÈTODE ESTÀ MALAMENT; Combina coses que han de fer les classes que hereten en els metodes abstract.ELIMINAR
	/*public boolean prestecValid(Soci soci, Exemplar exemplar) throws Exception {	
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
				if(soci.getPrestecs().size()>2){
					return false;
				}
				for(int i=0;i<soci.getPrestecs().size();i++){
					if(soci.getPrestecs().get(i).getExemplar().getObra().equals(exemplar.getObra())){
						return false;
					}
				}
				return true;
			}
		}
	}*/
/**
 * 
 * @Autor Manu
 * Metode per coneixer l'estat d'un soci depenent del seu nombre de prestecs
 */
	public estatAbs informarEstat(Soci soci) { //de saber si és moros o no ja ho fa el mètode "tornarPrestec"
		if(soci.getPrestecs().size()==0)
			return new estatSensePrestec();
		else
			return new estatAmbPrestec();
	}	
}
