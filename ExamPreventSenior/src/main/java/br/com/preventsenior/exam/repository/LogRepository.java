package br.com.preventsenior.exam.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.preventsenior.exam.model.Log;

@org.springframework.stereotype.Repository
public interface LogRepository extends JpaRepository<Log, Long> {
	
	@Query("SELECT l FROM Log l where l.ip = :ip and l.date >= :startDate and l.date <= :endDate") 
	Page<Log> findAll(@Param("ip") String ip, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	
}
