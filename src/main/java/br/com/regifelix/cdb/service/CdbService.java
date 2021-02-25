package br.com.regifelix.cdb.service;

import java.util.List;

import br.com.regifelix.cdb.model.CdbPos;
import br.com.regifelix.cdb.model.InvestmentData;

public interface CdbService {
	
	public List<CdbPos> calculateCdbPos(InvestmentData data) ;
	

}
