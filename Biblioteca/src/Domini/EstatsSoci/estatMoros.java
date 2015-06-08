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
		return new estatMoros();
	}

	@Override
	public estatAbs pagarPrestec(Soci soci, Exemplar exemplar) throws Exception {
		int contadorPrestecsPerPagar=0;
		for(int i=0;i<soci.getListPrestecs().size();i++){
			if(soci.getListPrestecs().get(i).getDataPagament()!=null){
				contadorPrestecsPerPagar++;
			}
		}
		if(contadorPrestecsPerPagar>1)
			return new estatMoros();
		else{
			if(soci.getListPrestecs().size()>1)
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
