package br.com.preventsenior.exam.vo;

import java.util.List;

/**
 * Classe que retorna resultado de uma pesquisa
 * @author Jean
 *
 * @param <T>
 */
public class Result<T> {

	/**
	 * Total paginas
	 */
	private Integer totalPages;

	/**
	 * Lista de registros
	 */
	private List<T> records;
	
	public Result(Integer totalPages, List<T> records) {
		this.totalPages = totalPages;
		this.records = records;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public List<T> getRecords() {
		return records;
	}

}
