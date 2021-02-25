package br.com.regifelix.cdb.controller;

import br.com.regifelix.cdb.model.CdbPos;
import br.com.regifelix.cdb.model.InvestmentData;
import br.com.regifelix.cdb.service.CdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "/cdb", produces = MediaType.APPLICATION_JSON_VALUE)
public class CdbController {
	
	@Autowired
	private CdbService cdbService;

	
    @GetMapping
    public List<CdbPos> getLista(@RequestBody  @Valid InvestmentData data){
        return cdbService.calculateCdbPos(data);
    }
}
