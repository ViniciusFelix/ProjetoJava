package br.jus.cnj.saci.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.dao.TipoComplementoRespostaDAO;
import br.jus.cnj.saci.entity.TipoComplementoResposta;
import br.jus.cnj.utils.exception.DaoException;

@Component("tipoComplementoRespostaConverter")
public class TipoComplementoRespostaConverter implements Converter {

	@Autowired
	private TipoComplementoRespostaDAO tipoComplementoRespostaDao;

	TipoComplementoResposta entidade = null;

	@Override
	public Object getAsObject(FacesContext faces, UIComponent ui, String value) {

		if (!(value == null || value.trim().isEmpty())) {
			//return null;

			try {
				entidade = tipoComplementoRespostaDao.pesquisarPorId(new Integer(value));
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
		if (value == null || !(value instanceof TipoComplementoResposta))
			return "";

		TipoComplementoResposta tipoComplementoResposta = (TipoComplementoResposta) value;
		return Integer.valueOf(tipoComplementoResposta.getId()) != null ? Integer.valueOf(tipoComplementoResposta.getId()).toString() : "";
	}

}
