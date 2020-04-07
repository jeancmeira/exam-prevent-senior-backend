package br.com.preventsenior.exam.vo;

/**
 * Classe dashboard
 * @author Jean
 *
 */
public interface LogAggregation {

	/**
	 * Total
	 * @return
	 */
	Long getTotal();
	
	/**
	 * Ip
	 * @return
	 */
	String getIp();
	
	/**
	 * Hora
	 * @return
	 */
	Integer getHour();
	
	/**
	 * User agent
	 * @return
	 */
	String getUserAgent();
	
}
