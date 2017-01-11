package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.PerguntaAutorizadaOrgao;

	public interface PerguntaAutorizadaOrgaoDAO extends GenericDao<PerguntaAutorizadaOrgao> {

		List<Pergunta> pesquisaAtaTribunal(
				InspecaoOrgao inspecaoOrgao);


		List<Pergunta> pesquisaPorPerguntaAta(
				InspecaoOrgao inspecaoOrgao);

		boolean pesquisaPerguntaAutorizada(Pergunta p, InspecaoOrgao ata);
		
		void removerPerguntasAutorizadas(InspecaoOrgao inspecaoOrgao);

}
