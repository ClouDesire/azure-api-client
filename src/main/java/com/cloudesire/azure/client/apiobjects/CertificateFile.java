package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement (name = "CertificateFile")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class CertificateFile
{
	@XmlElement (name = "Data")
	private String data;

	@XmlElement (name = "CertificateFormat")
	private String certificateFormat;

	@XmlElement (name = "Password")
	private String password;

	public String getData()
	{
		return data;
	}

	public void setData( String data )
	{
		this.data = data;
	}

	public String getCertificateFormat()
	{
		return certificateFormat;
	}

	public void setCertificateFormat( String certificateFormat )
	{
		this.certificateFormat = certificateFormat;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}
}
