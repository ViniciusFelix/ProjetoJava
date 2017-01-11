package br.jus.cnj.corporativo.autenticacao;

import javax.persistence.Entity;

@Entity
public class UsuarioCorporativo {
	private int seqUsuario;
	private String sigUsuario;
	private String nomUsuario;
	private String numCpf;
	private int seqOrgao;
	private String orgao;
	private String dscEmail;
	
	public int getSeqUsuario() {
		return seqUsuario;
	}
	public String getSigUsuario() {
		return sigUsuario;
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public String getNumCpf() {
		return numCpf;
	}
	public int getSeqOrgao() {
		return seqOrgao;
	}
	public String getOrgao() {
		return orgao;
	}
	public String getDscEmail() {
		return dscEmail;
	}
	public void setSeqUsuario(int seqUsuario) {
		this.seqUsuario = seqUsuario;
	}
	public void setSigUsuario(String sigUsuario) {
		this.sigUsuario = sigUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public void setNumCpf(String numCpf) {
		this.numCpf = numCpf;
	}
	public void setSeqOrgao(int seqOrgao) {
		this.seqOrgao = seqOrgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	public void setDscEmail(String dscEmail) {
		this.dscEmail = dscEmail;
	}
	
	@Override
	public String toString() {
		return "UsuarioCorporativo [seqUsuario=" + seqUsuario + ", sigUsuario="
				+ sigUsuario + ", nomUsuario=" + nomUsuario + ", numCpf="
				+ numCpf + ", seqOrgao=" + seqOrgao + ", orgao=" + orgao
				+ ", dscEmail=" + dscEmail + "]";
	}
	
	
}
