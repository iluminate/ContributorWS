package com.everis.fallas.operacionales.contribuidor.bean;

import java.util.List;

public class Response {
	private String errorCode;
	private String errorMsg;
	private List<Contributor> contribuidores;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public List<Contributor> getContribuidores() {
		return contribuidores;
	}
	public void setContribuidores(List<Contributor> contribuidores) {
		this.contribuidores = contribuidores;
	}
}
