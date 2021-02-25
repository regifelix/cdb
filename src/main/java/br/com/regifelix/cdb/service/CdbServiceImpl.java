package br.com.regifelix.cdb.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.regifelix.cdb.entity.Cdi;
import br.com.regifelix.cdb.model.CdbPos;
import br.com.regifelix.cdb.model.InvestmentData;

@Service
public class CdbServiceImpl implements CdbService {
	
	@Autowired
	private CdiService cdiService;

	@Override
	public List<CdbPos> calculateCdbPos(InvestmentData data) {

		List<Cdi> lista = cdiService.getCdiValues(data.getInvestmentDate(), data.getCurrentDate());
		List<Cdi> listaAcumulada = new ArrayList<Cdi>();
		List<CdbPos> listaCdb = new ArrayList<CdbPos>();

		BigDecimal cdiAcumulado = BigDecimal.ZERO;
		BigDecimal tcdb = data.getCdbRate();

		for (Cdi cdi : lista) {
			listaAcumulada.add(cdi);
			BigDecimal tcdi = cdiService.calculateCdi(cdi);


			//produtoCdi.add(BigDecimal.ONE);

			cdiAcumulado =  cdiAcumulado.add(tcdi).setScale(16, RoundingMode.HALF_UP);
			BigDecimal produtoCdi = tcdb.divide(BigDecimal.valueOf(100)).multiply(cdiAcumulado).add(BigDecimal.ONE);
			System.out.println("data=" + cdi.getData() + " tcdi=" + tcdi+ " acumulado="+cdiAcumulado+ " produtocdi=" + produtoCdi);

			BigDecimal cdb = produtoCdi.multiply(new BigDecimal(1000)).setScale(8, RoundingMode.HALF_UP);

			listaCdb.add(new CdbPos(cdi.getData(), cdb));


		}

		if(listaCdb.size()==0){
			listaCdb.add(new CdbPos(data.getCurrentDate(), BigDecimal.valueOf(1.0).setScale(2, RoundingMode.HALF_UP)));
		}


		return listaCdb;
	}
	
	

}
