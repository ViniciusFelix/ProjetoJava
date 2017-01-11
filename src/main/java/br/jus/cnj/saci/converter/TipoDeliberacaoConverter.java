package br.jus.cnj.saci.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.dao.TipoDeliberacaoDAO;
import br.jus.cnj.saci.entity.TipoDeliberacao;
import br.jus.cnj.utils.exception.DaoException;

@Component("tipoDeliberacaoConverter")
public class TipoDeliberacaoConverter implements Converter {

	@Autowired
	private TipoDeliberacaoDAO tipoDeliberacaoDao;

	TipoDeliberacao entidade = null;

	@Override
	public Object getAsObject(FacesContext faces, UIComponent ui, String value) {

		if (!(value == null || value.trim().isEmpty())) {
			//return null;

			try {
				entidade = tipoDeliberacaoDao.pesquisarPorId(new Integer(value));
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
		if (value == null || !(value instanceof TipoDeliberacao))
			return "";

		TipoDeliberacao tipoDeliberacao = (TipoDeliberacao) value;
		return Integer.valueOf(tipoDeliberacao.getId()) != null ? Integer.valueOf(tipoDeliberacao.getId()).toString() : "";
	}

}
