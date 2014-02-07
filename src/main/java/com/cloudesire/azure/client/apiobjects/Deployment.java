package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement ( name = "Deployment" )
@XmlType ( propOrder = {"name", "deploymentSlot", "label", "roleList", "virtualNetworkName"} )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class Deployment
{
	@XmlElement ( name = "Name" )
	private String name;

	@XmlElement ( name = "DeploymentSlot" )
	private String deploymentSlot;

	@XmlElement ( name = "Label" )
	private String label;

	@XmlElement ( name = "RoleList" )
	private RoleList roleList;

	@XmlElement ( name = "VirtualNetworkName" )
	private String virtualNetworkName;

	public String getName ()
	{
		return name;
	}

	public void setName ( String name )
	{
		this.name = name;
	}

	public String getDeploymentSlot ()
	{
		return deploymentSlot;
	}

	public void setDeploymentSlot ( String deploymentSlot )
	{
		this.deploymentSlot = deploymentSlot;
	}

	public String getLabel ()
	{
		return label;
	}

	public void setLabel ( String label )
	{
		this.label = label;
	}

	public RoleList getRoleList ()
	{
		return roleList;
	}

	public void setRoleList ( RoleList roleList )
	{
		this.roleList = roleList;
	}

	public String getVirtualNetworkName ()
	{
		return virtualNetworkName;
	}

	public void setVirtualNetworkName ( String virtualNetworkName )
	{
		this.virtualNetworkName = virtualNetworkName;
	}

	@Override
	public String toString ()
	{
		return "Name: " + name + " deploymentSlot: "
				+ deploymentSlot + " label: " + label + " RoleList: "
				+ roleList + " VirtualNetworkName: " + virtualNetworkName;
	}
}