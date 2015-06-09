package Domini.EstatsSoci;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.Soci;

public class estatMoros extends estatAbs {

	@Override
	public estatAbs demanarPrestec(Soci soci, Prestec prestec)
			throws Exception {
		throw new Exception(
				"Aquest soci no pot demanar préstecs perquè és un morós.");
	}

	@Override
	public estatAbs tornarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		for(Prestec p: soci.getPrestecs()){
			if (p.getExemplar().equals(exemplar)) {
				soci.getPrestecs().remove(p);
				return this;
			}
		}
		throw new Exception("No s'ha torbat el Exemplar en els prestecs del soci");
	}
	

	@Override
	public estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception {
		int contadorPrestecsPerPagar=0;
		for(int i=0;i<soci.getPrestecs().size();i++){
			if(soci.getPrestecs().get(i).getDataPagament()!=null){
				contadorPrestecsPerPagar++;
			}
		}
		soci.pagarPrestec(exemplar);
		if(contadorPrestecsPerPagar>1){
			return new estatMoros();
		}
		else{
			if(soci.getPrestecs().size()>1)
				return new estatAmbPrestec();
			else
				return new estatSensePrestec();
		}
			
	}
	
	@Override
	public String toString(){
		return "Moros";
	}

}
