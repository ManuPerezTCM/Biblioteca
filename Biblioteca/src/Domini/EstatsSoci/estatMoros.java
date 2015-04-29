package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public class estatMoros extends estatAbs {

	@Override
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		throw new Exception(
				"Aquest soci no pot demanar préstecs perquè és un morós.");
	}

	@Override
	public estatAbs tornarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		return sociValid(soci, exemplar);
	}

	@Override
	//PER COMPLETAR
	public estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception {
		// PRIMER RECUPEREM EL PRESTEC D'AQUEST SOCI ON TÉ AQUEST EXEMPLAR, L'ESBORREM I DESPRÉS:
		return sociValid(soci, exemplar);
	}
	
	@Override
	public String toString(){
		return "Moros";
	}

}
