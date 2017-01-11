package br.jus.cnj.saci.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.dao.TipoAplicacaoPerguntaDAO;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.DaoException;

@Component("tipoAplicacaoPerguntaConverter")
public class TipoAplicacaoPerguntaConverter implements Converter {

	@Autowired
	private TipoAplicacaoPerguntaDAO tipoAplicacaoPerguntaDao;

	TipoAplicacaoPergunta entidade = null;

	@Override
	public Object getAsObject(FacesContext faces, UIComponent ui, String value) {

		if (!(value == null || value.trim().isEmpty())) {
			//return null;

			try {
				entidade = tipoAplicacaoPerguntaDao.pesquisarPorId(new Integer(value));
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
		if (value == null || !(value instanceof TipoAplicacaoPergunta))
			return "";

		TipoAplicacaoPergunta tipoAplicacaoPergunta = (TipoAplicacaoPergunta) value;
		Integer val = tipoAplicacaoPergunta.getId() != null ? tipoAplicacaoPergunta.getId() : 0;
		return Integer.valueOf(val) != null ? Integer.valueOf(val).toString() : "";
	}

}
