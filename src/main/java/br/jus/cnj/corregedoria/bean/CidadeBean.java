package br.jus.cnj.corregedoria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corregedoria.service.CidadeService;
import br.jus.cnj.utils.exception.ServiceException;

@ManagedBean(name = "cidadeBean")
@Component
@Scope(value = "session")
public class CidadeBean {
	private Cidade cidade = new Cidade();

	private List<Cidade> inspecaoList = new ArrayList<Cidade>();
	
	private String nomeCidade;
	
	private List<Cidade> filteredCartorio;

	@Autowired
	private CidadeService cidadeService;

	List<Cidade> tipoObjeto;
	
	public List<Cidade> getFilteredCartorio() {  
        return filteredCartorio;  
    }  
  
    public void setFilteredCartorio(List<Cidade> filteredCartorio) throws ServiceException {  
        this.filteredCartorio = cidadeService.getAll();  
    }  
    
	public List<Cidade> getAll() {
		try {
			inspecaoList = cidadeService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inspecaoList;
	}

}
