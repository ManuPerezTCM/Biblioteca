package Domini.EstatsSoci;

import java.util.Calendar;
import java.util.Date;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.PrestecPK;
import Domini.Soci;

public class estatAmbPrestec extends estatAbs{

	@Override
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		if (soci.getDataBaixa() != null){
			throw new Exception("Aquest soci esta de baixa i no pot fer un prestec.");
		}
		if(soci.getPrestecs().size()<3){
			for(int i=0;i<soci.getPrestecs().size();i++){
				if(soci.getPrestecs().get(i).getExemplar().getObra().equals(exemplar.getObra())){
					throw new Exception("Aquest soci ja té aquesta mateixa obra en prèstec");
				}
			}
			
			
			
			PrestecPK prestecPK = new PrestecPK();
			prestecPK.setDataPrestec(new Date());
			prestecPK.setSoci(soci.getDni());

			Prestec prestec = new Prestec();
			prestec.setId(prestecPK);
			prestec.setExemplar(exemplar);
			prestec.setSoci(soci);
				
			Calendar c = Calendar.getInstance(); 
			c.setTime(prestec.getId().getDataPrestec()); 
			c.add(Calendar.DATE, 5);
			prestec.setDataMaxRetorn(c.getTime());
			
			
			soci.getPrestecs().add(prestec);
			
			
			
			return new estatAmbPrestec();
		}
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
		throw new Exception("Aquest soci no tÃ© res a pagar.");
	}

	@Override
	public String toString(){
		return "AmbPrestec";
	}
}
