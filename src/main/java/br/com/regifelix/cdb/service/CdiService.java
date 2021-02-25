package br.com.regifelix.cdb.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.regifelix.cdb.entity.Cdi;

public interface CdiService {
	
	public List<Cdi> getCdiValues(LocalDate initialDate, LocalDate finalDate);
	public BigDecimal calculeAcumulatedCdi(List<Cdi> cdiValues, BigDecimal cdbRate);
	public BigDecimal calculateCdi(Cdi cdi);

}
