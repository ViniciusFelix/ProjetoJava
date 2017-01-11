package br.jus.cnj.saci.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;

public class TipoAplicacaoPerguntaModel extends ListDataModel<TipoAplicacaoPergunta> implements SelectableDataModel<TipoAplicacaoPergunta> {


	public TipoAplicacaoPerguntaModel() {
    }
	
	public TipoAplicacaoPerguntaModel(List<TipoAplicacaoPergunta> data) {
        super(data);
    }
     
    @Override
    public TipoAplicacaoPergunta getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
         
        List<TipoAplicacaoPergunta> tipos = (List<TipoAplicacaoPergunta>) getWrappedData();
         
        for(TipoAplicacaoPergunta tipo : tipos) {
            if(tipo.getId().equals(Integer.parseInt(rowKey)))
                return tipo;
        }
         
        return null;
    }
 
    @Override
    public Object getRowKey(TipoAplicacaoPergunta tipo) {
        return tipo.getId();
    }
}
