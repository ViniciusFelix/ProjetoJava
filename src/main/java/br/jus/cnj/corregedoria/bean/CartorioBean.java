package br.jus.cnj.corregedoria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.corregedoria.service.CartorioService;

@ManagedBean(name = "cartorioBean")
@Component
@Scope(value = "session")
public class CartorioBean {
	private List<Cartorio> cartorioList = new ArrayList<Cartorio>();
	
	private DualListModel<String> cartorios; 

	@Autowired
	private CartorioService cartorioService;

	public  void setCartorios(DualListModel<String> cartorios){
		this.cartorios = cartorios;
	}
	
	public DualListModel<String> getCartorios(){
		List<String> cartorioSource = new ArrayList<String>();  
        List<String> cartorioTarget = new ArrayList<String>();

        for(int i=0; i < this.cartorioList.size();i++){
        	cartorioSource.add(this.cartorioList.get(i).getDenominacaoCartorio());
    	}
		cartorios = new DualListModel<String>(cartorioSource, cartorioTarget);
		
		return cartorios;
	}

}
