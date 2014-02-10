package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "Role", propOrder = {
		"roleName", "roleType", "configurationSets", "osVirtualHardDisk", "roleSize"})
@XmlAccessorType (value = XmlAccessType.FIELD)
public class Role
{
	@XmlElement (name = "RoleName")
	private String roleName;

	@XmlElement (name = "RoleType")
	private String roleType = "PersistentVMRole";

	@XmlElement (name = "ConfigurationSets")
	private ConfigurationSets configurationSets = new ConfigurationSets();

	@XmlElement (name = "OSVirtualHardDisk")
	private OSVirtualHardDisk osVirtualHardDisk = new OSVirtualHardDisk();

	@XmlElement (name = "RoleSize")
	private String roleSize;

	public String getRoleName ()
	{
		return roleName;
	}

	public void setRoleName ( String roleName )
	{
		this.roleName = roleName;
	}

	public String getRoleType ()
	{
		return roleType;
	}

	public void setRoleType ( String roleType )
	{
		this.roleType = roleType;
	}

	public ConfigurationSets getConfigurationSets ()
	{
		return configurationSets;
	}

	public void setConfigurationSets ( ConfigurationSets configurationSets )
	{
		this.configurationSets = configurationSets;
	}

	public OSVirtualHardDisk getOsVirtualHardDisk ()
	{
		return osVirtualHardDisk;
	}

	public void setOsVirtualHardDisk ( OSVirtualHardDisk osVirtualHardDisk )
	{
		this.osVirtualHardDisk = osVirtualHardDisk;
	}

	public String getRoleSize ()
	{
		return roleSize;
	}

	public void setRoleSize ( String roleSize )
	{
		this.roleSize = roleSize;
	}

	@Override
	public String toString ()
	{
		return " RoleName: " + roleName + " RoleType: " + roleType + " ConfigurationSets: "
				+ configurationSets + " OSVirtualDisk: " + osVirtualHardDisk + " RoleSize: " + roleSize;
	}
}