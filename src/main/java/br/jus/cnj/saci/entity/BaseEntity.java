package br.jus.cnj.saci.entity;

import java.io.Serializable;

/**
* Entidade base
*
* @author vanderli.gomes
*
* @param <T> tipo da chave primaria da entidade
*/
public abstract class BaseEntity<T extends Comparable<T>> implements Serializable, Comparable<BaseEntity<T>> {
       private static final long serialVersionUID = 1L;

       public abstract T getId();
       
       public abstract void setId(T id);

       @Override
       public boolean equals(Object other) {
             if(other == null){
                    return false;
             }

             if(this == other){
                    return true;
             }

             if(other instanceof BaseEntity){
                    BaseEntity<?> obj = (BaseEntity<?>) other;
                    if(getId() == null || obj.getId() == null)
                           return false;

                    return getId().equals(obj.getId());
             } else {
                    return false;
             }
       }

       @Override
       public int hashCode() {
             return getId() != null ? getId().hashCode() : 0;
       }

       @Override
       public int compareTo(BaseEntity<T> other) {
             if(getId() != null){
                    return getId().compareTo(other.getId());
             }      
             return 0;
       }
}
