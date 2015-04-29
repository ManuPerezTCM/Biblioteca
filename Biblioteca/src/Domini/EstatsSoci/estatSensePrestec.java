package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public class estatSensePrestec extends estatAbs{

	@Override
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		//POSAR A LA LLISTA I A LA BD EL PRESTEC DEL SOCI I L'EXEMPLAR I DESPRES
		//mirar que soci no estigui de baixa
		soci.setEstat("AmbPrestec");
		return sociValid(soci, exemplar);
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
