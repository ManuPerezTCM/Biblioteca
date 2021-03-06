package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;

public abstract class estatAbs {

	public abstract estatAbs demanarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs tornarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public estatAbs informarEstat(Soci soci) { 
		if(soci.getPrestecs().size()==0)
			return new estatSensePrestec();
		else
			return new estatAmbPrestec();
	}	
}
