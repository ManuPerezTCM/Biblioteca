package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public abstract class estatAbs {

	public abstract estatAbs demanarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs tornarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public boolean prestecValid(Soci soci, Exemplar exemplar) throws Exception {	
		
		
		if(soci.getDataBaixa()!=null)
			throw new Exception("Aquest soci està de baixa. No es pot operar amb ell");
		if (soci.getPrestecsPerPagar()==0) {
			if(soci.getPrestecsPerTornar()==0)
				return new estatSensePrestec();
			else
				return new estatAmbPrestec();
		} else {
			return new estatMoros();
		}
	}	
}
