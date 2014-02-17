package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "ConfigurationSet")
public class ConfigurationSet
{
	@XmlAttribute (name = "type", namespace = "i")
	private String configurationSetTypeAttribute = "LinuxProvisioningConfiguration";

	@XmlElement (name = "ConfigurationSetType")
	private String configurationSetType = "LinuxProvisioningConfiguration";

	@XmlElement (name = "HostName")
	private String hostName;

	@XmlElement (name = "UserName")
	private String userName;

	@XmlElement (name = "UserPassword")
	private String userPassword;

	@XmlElement (name = "DisableSshPasswordAuthentication")
	private Boolean disableSshPasswordAuthentication = Boolean.TRUE;

	@XmlElement (name = "SSH")
	private SshKeyContainer ssh;

	@XmlElement (name = "InputEndpoints")
	private InputEndpoints inputEndpoints;

	@XmlElement (name = "StaticVirtualNetworkIPAddress")
	private String staticVirtualNetworkIPAddress;

	public void setConfigurationSetTypeAttribute( String configurationSetTypeAttribute )
	{
		this.configurationSetTypeAttribute = configurationSetTypeAttribute;
	}

	public void setConfigurationSetType( String configurationSetType )
	{
		this.configurationSetType = configurationSetType;
	}

	public String getConfigurationSetTypeAttribute()
	{
		return configurationSetTypeAttribute;
	}

	public String getConfigurationSetType()
	{
		return configurationSetType;
	}

	public String getHostName()
	{
		return hostName;
	}

	public void setHostName( String hostName )
	{
		this.hostName = hostName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword( String userPassword )
	{
		this.userPassword = userPassword;
	}

	public Boolean getDisableSshPasswordAuthentication()
	{
		return disableSshPasswordAuthentication;
	}

	public void setDisableSshPasswordAuthentication( Boolean disableSshPasswordAuthentication )
	{
		this.disableSshPasswordAuthentication = disableSshPasswordAuthentication;
	}

	public SshKeyContainer getSsh()
	{
		return ssh;
	}

	public void setSsh( SshKeyContainer ssh )
	{
		this.ssh = ssh;
	}

	public InputEndpoints getInputEndpoints()
	{
		return inputEndpoints;
	}

	public void setInputEndpoints( InputEndpoints inputEndpoints )
	{
		this.inputEndpoints = inputEndpoints;
	}

	public String getStaticVirtualNetworkIPAddress()
	{
		return staticVirtualNetworkIPAddress;
	}

	public void setStaticVirtualNetworkIPAddress( String staticVirtualNetworkIPAddress )
	{
		this.staticVirtualNetworkIPAddress = staticVirtualNetworkIPAddress;
	}

	@Override
	public String toString()
	{
		return " ConfigurationSetType: " + configurationSetType + " HostName: " + hostName
				+ " UserName: " + userName + " UserPassword: " + userPassword
				+ " DisableSshPasswordAuthentication: " + disableSshPasswordAuthentication;
	}
}
