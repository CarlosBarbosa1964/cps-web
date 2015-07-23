package br.com.ibtechnology.cpsweb.model.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

//Classe persistente com definição de Tipo de ID
public abstract class BaseEntities<PK extends Serializable> extends AbstractPersistable<PK> implements Serializable {

	private static final long serialVersionUID = -5377726703339445533L;

	//Define o ID como publico
	@Override
	public void setId(PK id) {
		super.setId(id);
	}

	//Utilizado para retornar valores da classe
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	//Utilizado para comparativos
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
