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
public class FingerPath
{
	@XmlElement (name = "Fingerprint")
	protected String fingerPrint;

	@XmlElement (name = "Path")
	protected String path;

	public String getFingerPrint ()
	{
		return fingerPrint;
	}

	public void setFingerPrint ( String fingerPrint )
	{
		this.fingerPrint = fingerPrint;
	}

	public String getPath ()
	{
		return path;
	}

	public void setPath ( String path )
	{
		this.path = path;
	}
}
