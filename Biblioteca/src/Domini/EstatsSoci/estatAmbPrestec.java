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
		if(soci.getListPrestecs().size()<3)
			return new estatAmbPrestec();
		else
			throw new Exception("Aquest soci ja te el limit de prestecs.");
	}

	@Override
	public estatAbs tornarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		if(soci.getListPrestecs().size()==1)
			return new estatSensePrestec();
		else
			return new estatAmbPrestec();
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
