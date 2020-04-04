package br.com.preventsenior.exam.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.preventsenior.exam.model.Log;
import br.com.preventsenior.exam.vo.LogAggregation;

@org.springframework.stereotype.Repository
public interface LogRepository extends JpaRepository<Log, Long> {
	
	@Query("SELECT l FROM Log l where l.ip = :ip and l.date >= :startDate and l.date <= :endDate") 
	Page<Log> findAll(@Param("ip") String ip, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
	@Query("SELECT count(l) as total, l.ip, l.userAgent, HOUR(l.date) as hour"
			+ " FROM Log l group by l.ip, l.userAgent, HOUR(l.date)")
	Page<LogAggregation> listTotalization(Pageable pageable);
	
}
