package br.jus.cnj.saci.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.dao.TipoDocumentoDAO;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.utils.exception.DaoException;

@Component("tipoDocumentoConverter")
public class TipoDocumentoConverter implements Converter {

	@Autowired
	private TipoDocumentoDAO tipoDocumentoDao;

	TipoDocumento entidade = null;

	@Override
	public Object getAsObject(FacesContext faces, UIComponent ui, String value) {

		if (!(value == null || value.trim().isEmpty())) {
			//return null;

			try {
				entidade = tipoDocumentoDao.pesquisarPorId(new Integer(value));
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
		if (value == null || !(value instanceof TipoDocumento))
			return "";

		TipoDocumento tipoDocumento = (TipoDocumento) value;
		return Integer.valueOf(tipoDocumento.getId()) != null ? Integer.valueOf(tipoDocumento.getId()).toString() : "";
	}

}
