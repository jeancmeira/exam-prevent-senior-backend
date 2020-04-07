package br.com.preventsenior.exam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.preventsenior.exam.json.serialization.CustomDateTimeDeserializer;
import br.com.preventsenior.exam.json.serialization.CustomDateTimeSerializer;

@Entity
@Table(name="log")
/**
 * Classe Log
 * @author Jean
 *
 */
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name="seq_generator", sequenceName = "seq_log", allocationSize=1)
	/**
	 * Id
	 */
	private Long id;
	
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	/**
	 * Data
	 */
	private Date date;
	
	/**
	 * ip
	 */
	private String ip;
	
	/**
	 * Request
	 */
	private String request;
	
	/**
	 * Status
	 */
	private Integer status;
	
	@Column(name="user_agent")
	/**
	 * User agent
	 */
	private String userAgent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}  
	
	
	
}
