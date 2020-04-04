package br.com.preventsenior.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.preventsenior.exam.model.Log;

@org.springframework.stereotype.Repository
public interface LogRepository extends JpaRepository<Log, Long> {
		
}
