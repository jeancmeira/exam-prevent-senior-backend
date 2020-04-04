package br.com.preventsenior.exam.repository;

import org.springframework.data.repository.Repository;

import br.com.preventsenior.exam.model.Log;

@org.springframework.stereotype.Repository
public interface LogRepository extends Repository<Log, Long> {
	
}
