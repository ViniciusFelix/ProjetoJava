package br.jus.cnj.corporativo.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;

public class CorporativoOrgaoModel extends ListDataModel<CorporativoOrgao> implements SelectableDataModel<CorporativoOrgao> {


	public CorporativoOrgaoModel() {
    }
	
	public CorporativoOrgaoModel(List<CorporativoOrgao> data) {
        super(data);
    }
     
    @Override
    public CorporativoOrgao getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
         
        List<CorporativoOrgao> tipos = (List<CorporativoOrgao>) getWrappedData();
         
        for(CorporativoOrgao tipo : tipos) {
        	if(tipo.getId().equals(Integer.parseInt(rowKey)))
                return tipo;
        }
         
        return null;
    }
 
    @Override
    public Object getRowKey(CorporativoOrgao tipo) {
        return tipo.getId();
    }
}
