package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;

public class estatAmbPrestec extends estatAbs{

	@Override
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		if (soci.getDataBaixa() != null){
			throw new Exception("Aquest soci esta de baixa i no pot fer un prestec.");
		}
		if(soci.getPrestecs().size()<3)
			return new estatAmbPrestec();
		else
			throw new Exception("Aquest soci ja te el limit de prestecs.");
	}

	@Override
	public estatAbs tornarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		for(Prestec p: soci.getPrestecs()){
			if (p.getExemplar().equals(exemplar)) {
				soci.getPrestecs().remove(p);
				if(p.getImportRetard() != null){
					return new estatMoros();
				}		
				else{
					return super.informarEstat(soci);
				}
			}
		}
		throw new Exception("No s'ha torbat el Exemplar en els prestecs del soci");
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
