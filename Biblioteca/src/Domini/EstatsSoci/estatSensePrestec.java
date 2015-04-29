package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public class estatSensePrestec extends estatAbs{

	@Override
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		return new estatAmbPrestec();
	}

	@Override
	public estatAbs tornarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		throw new Exception("Aquest soci no te res a tornar.");
	}

	@Override
	public estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception {
		throw new Exception("Aquest soci no te res a pagar.");
	}

	@Override
	public String toString(){
		return "SensePrestec";
	}
}
