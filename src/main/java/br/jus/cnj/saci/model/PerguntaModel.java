package br.jus.cnj.saci.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.jus.cnj.saci.entity.Pergunta;

public class PerguntaModel extends ListDataModel<Pergunta> implements SelectableDataModel<Pergunta> {


	public PerguntaModel() {
    }
	
	public PerguntaModel(List<Pergunta> data) {
        super(data);
    }
     
    @Override
    public Pergunta getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
         
        List<Pergunta> tipos = (List<Pergunta>) getWrappedData();
         
        for(Pergunta tipo : tipos) {
            if(tipo.getId().equals(Integer.parseInt(rowKey)))
                return tipo;
        }
         
        return null;
    }
 
    @Override
    public Object getRowKey(Pergunta tipo) {
        return tipo.getId();
    }
}
