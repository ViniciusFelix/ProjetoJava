package br.jus.cnj.corporativo.autenticacao;

public class Credencial {
	private String id;
	private int seqSistema;
	private String sistema;
	private int seqPerfil;
	private String perfil;
	private long datCriacao;
	private long timeTolive;
	private UsuarioCorporativo usuario;
	
	public String getId() {
		return id;
	}
	public int getSeqSistema() {
		return seqSistema;
	}
	public String getSistema() {
		return sistema;
	}
	public int getSeqPerfil() {
		return seqPerfil;
	}
	public String getPerfil() {
		return perfil;
	}
	public long getDatCriacao() {
		return datCriacao;
	}
	public long getTimeTolive() {
		return timeTolive;
	}
	public UsuarioCorporativo getUsuario() {
		return usuario;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSeqSistema(int seqSistema) {
		this.seqSistema = seqSistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public void setSeqPerfil(int seqPerfil) {
		this.seqPerfil = seqPerfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public void setDatCriacao(long datCriacao) {
		this.datCriacao = datCriacao;
	}
	public void setTimeTolive(long timeTolive) {
		this.timeTolive = timeTolive;
	}
	public void setUsuario(UsuarioCorporativo usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Credencial [id=" + id + ", seqSistema=" + seqSistema
				+ ", sistema=" + sistema + ", seqPerfil=" + seqPerfil
				+ ", perfil=" + perfil + ", datCriacao=" + datCriacao
				+ ", timeTolive=" + timeTolive + ", usuario=" + usuario + "]";
	}	
	
}
