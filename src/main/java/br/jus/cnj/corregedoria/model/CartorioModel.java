package br.jus.cnj.corregedoria.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.jus.cnj.corregedoria.entity.Cartorio;

public class CartorioModel extends ListDataModel<Cartorio> implements SelectableDataModel<Cartorio> {


	public CartorioModel() {
    }
	
	public CartorioModel(List<Cartorio> data) {
        super(data);
    }
     
    @Override
    public Cartorio getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
         
        List<Cartorio> tipos = (List<Cartorio>) getWrappedData();
         
        for(Cartorio tipo : tipos) {
            if(tipo.getId().equals(Integer.parseInt(rowKey)))
                return tipo;
        }
         
        return null;
    }
 
    @Override
    public Object getRowKey(Cartorio tipo) {
        return tipo.getId();
    }
}
