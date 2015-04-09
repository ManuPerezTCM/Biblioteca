package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public class estatAmbPrestec extends estatAbs{

	@Override
	//No m'agrada això, el .size<=2 hauria de ser el paràmetre que te la biblioteca on s'especifica quants
	//préstecs puc tenir a l'hora, com ha dit en debru a classe, de metres ho deixo aixi
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		if(soci.getPrestecsPerTornar().size()<=2)
			return new estatAmbPrestec();
		else
			throw new Exception("Aquest soci ja te el límit de préstecs.");
	}

	@Override
	//PER COMPLETAR
	public estatAbs tornarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		//PRIMER S'HA DE VEURE SI EL PRÉSTEC D'AQUEST SOCI AMB AQUEST EXEMPLAR ES TORNA A TEMPS O PASSAT EL PERIODE.
		return sociValid(soci, exemplar);
	}

	@Override
	public estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception {
		throw new Exception("Aquest soci no té res a pagar.");
	}

}
