package br.jus.cnj.saci.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.dao.InspecaoDAO;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.utils.exception.DaoException;

@Component("inspecaoConverter")
public class ExampleConverter implements Converter {

	@Autowired
	private InspecaoDAO inspecaoDao;

	@Override
	public Object getAsObject(FacesContext faces, UIComponent ui, String value) {
		Inspecao entidade = null;

		if (value == null || value.trim().isEmpty())
			return null;

		try {
			entidade = inspecaoDao.pesquisarPorId(new Integer(value));
		} catch (NumberFormatException e) {
			return null;
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}

		return entidade;
	}

	@Override
	public String getAsString(FacesContext faces, UIComponent ui, Object value) {
		if (value == null || !(value instanceof Inspecao))
			return "";

		Inspecao inspecao = (Inspecao) value;
		return Integer.valueOf(inspecao.getId()) != null ? Integer.valueOf(inspecao.getId()).toString() : "";
	}

}
