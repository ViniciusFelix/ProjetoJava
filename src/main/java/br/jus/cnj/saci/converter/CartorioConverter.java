package br.jus.cnj.saci.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.dao.CartorioDAO;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.utils.exception.DaoException;

@Component("cartorioConverter")
public class CartorioConverter implements Converter {

	@Autowired
	private CartorioDAO cartorioDao;

	Cartorio entidade = null;

	@Override
	public Object getAsObject(FacesContext faces, UIComponent ui, String value) {

		if (!(value == null || value.trim().isEmpty())) {
			//return null;

			try {
				entidade = cartorioDao.pesquisarPorId(new Integer(value));
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
		if (value == null || !(value instanceof Cartorio))
			return "";

		Cartorio cartorio = (Cartorio) value;
		return Integer.valueOf(cartorio.getId()) != null ? Integer.valueOf(cartorio.getId()).toString() : "";
	}

}
