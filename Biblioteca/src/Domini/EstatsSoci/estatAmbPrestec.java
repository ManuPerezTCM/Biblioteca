package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Soci;

public class estatAmbPrestec extends estatAbs{

	@Override
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		if (soci.getDataBaixa() != null){
			throw new Exception("Aquest soci esta de baixa i no pot fer un prestec.");
		}
		if(soci.getPrestecsPerTornar()<=2)
			return new estatAmbPrestec();
		else
			throw new Exception("Aquest soci ja te el limit de prestecs.");
	}

	@Override
	public estatAbs tornarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		return sociValid(soci, exemplar);
	}

	@Override
	public estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception {
		throw new Exception("Aquest soci no té res a pagar.");
	}

	@Override
	public String toString(){
		return "AmbPrestec";
	}
}
