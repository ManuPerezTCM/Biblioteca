package Domini.EstatsSoci;

import java.util.Calendar;
import java.util.Date;

import Domini.Exemplar;
import Domini.Prestec;
import Domini.PrestecPK;
import Domini.Soci;

public class estatSensePrestec extends estatAbs{

	@Override
	public estatAbs demanarPrestec(Soci soci, Exemplar exemplar)
			throws Exception {
		if (soci.getDataBaixa() != null){
			throw new Exception("Aquest soci esta de baixa i no pot fer un prestec.");
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
