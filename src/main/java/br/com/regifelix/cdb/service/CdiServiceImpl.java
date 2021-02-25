package br.com.regifelix.cdb.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.regifelix.cdb.entity.Cdi;
import br.com.regifelix.cdb.persistence.CdiRepository;


@Service
public class CdiServiceImpl implements CdiService {
	
	@Autowired
	private CdiRepository repository;

	@Override
	public List<Cdi> getCdiValues(LocalDate initialDate, LocalDate finalDate) {
		return repository.findAllByDataGreaterThanEqualAndDataLessThanOrderByDataAsc(initialDate, finalDate);
	}

	@Override
	public BigDecimal calculeAcumulatedCdi(List<Cdi> cdiValues, BigDecimal cdbRate) {


		Double powerCdi = Double.valueOf(1) / Double.valueOf(252);
		Double cdb = cdbRate.doubleValue();
		Double tcdiacumulated = Double.valueOf(0);

		for (Cdi cdi : cdiValues) {

			Double value1 = ((cdi.getValue().doubleValue() / 100) + 1);
			Double tcdi = Math.pow(value1, powerCdi) - 1;

			Double cdiValue = 1 + tcdi * (cdb / 100);
			Double truncatedDouble = BigDecimal.valueOf(cdiValue)
					.setScale(6, RoundingMode.HALF_UP)
					.doubleValue();
			tcdiacumulated += truncatedDouble;


		}

		return BigDecimal.valueOf(tcdiacumulated);
	}

	@Override
	public BigDecimal calculateCdi(Cdi cdi) {
		Double power = Double.valueOf(1) / Double.valueOf(252);
		Double value1 = ((cdi.getValue().doubleValue() / 100) + 1);
		Double powerCdi = Math.pow(value1, power);

		Double cdiValue = powerCdi - 1;
		return BigDecimal.valueOf(cdiValue)
				.setScale(16, RoundingMode.HALF_UP);

	}


}
