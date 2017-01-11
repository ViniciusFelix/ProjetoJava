package br.jus.cnj.saci.entity;

import java.util.List;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;

public class UnidadesAtas {
	private List<CorporativoOrgao> unidades;
	
	private List<TipoAplicacaoPergunta> atas;
	
	private List<Cartorio> cartorios;
	
	private Questionario questionario;

	public List<Cartorio> getCartorios() {
		return cartorios;
	}

	public void setCartorios(List<Cartorio> cartorios) {
		this.cartorios = cartorios;
	}

	public List<CorporativoOrgao> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<CorporativoOrgao> unidades) {
		this.unidades = unidades;
	}

	public List<TipoAplicacaoPergunta> getAtas() {
		return atas;
	}

	public void setAtas(List<TipoAplicacaoPergunta> atas) {
		this.atas = atas;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
	public UnidadesAtas() {
    }
 
    
	
}
