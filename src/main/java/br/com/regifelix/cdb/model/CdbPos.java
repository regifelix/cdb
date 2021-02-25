package br.com.regifelix.cdb.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CdbPos {
	
	private LocalDate date;
	private BigDecimal unitPrice;

}
