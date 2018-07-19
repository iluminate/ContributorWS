package com.everis.fallas.operacionales.contribuidor.bean;

public class Request {
	private Auditoria auditoria;
	private String name;

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
