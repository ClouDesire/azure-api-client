package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "RoleInstance")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class RoleInstance
{
	@XmlElement (name = "RoleName")
	private String roleName;

	@XmlElement (name = "InstanceName")
	private String instanceName;

	@XmlElement (name = "InstanceStatus")
	private String instanceStatus;

	@XmlElement (name = "InstanceSize")
	private String instanceSize;

	@XmlElement (name = "InstanceStateDetails")
	private String instanceStateDetails;

	@XmlElement (name = "InstanceErrorCode")
	private String instanceErrorCode;

	@XmlElement (name = "IpAddress")
	private String ipAddress;

	@XmlElement (name = "InstanceEndpoints")
	private InstanceEndpoints instanceEndpoints;

	@XmlElement (name = "PowerState")
	private String powerState;

	@XmlElement (name = "HostName")
	private String hostName;

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName( String roleName )
	{
		this.roleName = roleName;
	}

	public String getInstanceName()
	{
		return instanceName;
	}

	public void setInstanceName( String instanceName )
	{
		this.instanceName = instanceName;
	}

	public String getInstanceStatus()
	{
		return instanceStatus;
	}

	public void setInstanceStatus( String instanceStatus )
	{
		this.instanceStatus = instanceStatus;
	}

	public String getInstanceSize()
	{
		return instanceSize;
	}

	public void setInstanceSize( String instanceSize )
	{
		this.instanceSize = instanceSize;
	}

	public String getInstanceStateDetails()
	{
		return instanceStateDetails;
	}

	public void setInstanceStateDetails( String instanceStateDetails )
	{
		this.instanceStateDetails = instanceStateDetails;
	}

	public String getInstanceErrorCode()
	{
		return instanceErrorCode;
	}

	public void setInstanceErrorCode( String instanceErrorCode )
	{
		this.instanceErrorCode = instanceErrorCode;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress( String ipAddress )
	{
		this.ipAddress = ipAddress;
	}

	public InstanceEndpoints getInstanceEndpoints()
	{
		return instanceEndpoints;
	}

	public void setInstanceEndpoints( InstanceEndpoints instanceEndpoints )
	{
		this.instanceEndpoints = instanceEndpoints;
	}

	public String getPowerState()
	{
		return powerState;
	}

	public void setPowerState( String powerState )
	{
		this.powerState = powerState;
	}

	public String getHostName()
	{
		return hostName;
	}

	public void setHostName( String hostName )
	{
		this.hostName = hostName;
	}

	@Override
	public String toString()
	{
		return "InstanceName: " + instanceName + " InstanceStatus: " + instanceStatus + " InstanceSize: " + instanceSize + " IpAddress: " + ipAddress;
	}
}
