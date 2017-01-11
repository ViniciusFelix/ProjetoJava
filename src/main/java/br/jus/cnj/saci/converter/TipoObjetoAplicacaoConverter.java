package br.jus.cnj.saci.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.dao.TipoObjetoAplicacaoDAO;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;

@Component("tipoObjetoAplicacaoConverter")
public class TipoObjetoAplicacaoConverter implements Converter {

	@Autowired
	private TipoObjetoAplicacaoDAO tipoObjetoAplicacaoDao;

	TipoObjetoAplicacao entidade = null;

	@Override
	public Object getAsObject(FacesContext faces, UIComponent ui, String value) {

		if (!(value == null || value.trim().isEmpty())) {
			//return null;

			try {
				entidade = tipoObjetoAplicacaoDao.pesquisarPorId(new Integer(value));
			} catch (NumberFormatException e) {
				return null;
			} catch (DaoException e) {
				throw new RuntimeException(e);
			}
		}

		return entidade;
	}

	@Override
	public String getAsString(FacesContext faces, UIComponent ui, Object value) {
		if (value == null || !(value instanceof TipoObjetoAplicacao))
			return "";

		TipoObjetoAplicacao tipoObjetoAplicacao = (TipoObjetoAplicacao) value;
		return Integer.valueOf(tipoObjetoAplicacao.getId()) != null ? Integer.valueOf(tipoObjetoAplicacao.getId()).toString() : "";
	}

}
