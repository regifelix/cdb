package br.com.regifelix.cdb.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.regifelix.cdb.entity.Cdi;

@Repository
public interface CdiRepository extends JpaRepository<Cdi, Long>{
	
	List<Cdi> findAllByDataGreaterThanEqualAndDataLessThanOrderByDataAsc(LocalDate data, LocalDate data2);
	      
	
	
	

}
