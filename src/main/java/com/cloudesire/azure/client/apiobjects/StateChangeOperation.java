package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlAccessorType (XmlAccessType.FIELD)
@XmlTransient
public class StateChangeOperation
{
	@XmlElement (name = "OperationType")
	private String operationType;

	public String getOperationType()
	{
		return operationType;
	}

	public void setOperationType( String operationType )
	{
		this.operationType = operationType;
	}
}
