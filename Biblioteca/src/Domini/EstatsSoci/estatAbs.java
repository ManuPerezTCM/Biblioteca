package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public abstract class estatAbs {

	public abstract estatAbs demanarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs tornarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public abstract estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception;
	
	public estatAbs sociValid(Soci soci, Exemplar exemplar) throws Exception {
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
