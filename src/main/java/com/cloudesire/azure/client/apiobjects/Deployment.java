package com.cloudesire.azure.client.apiobjects;

import com.cloudesire.azure.client.apiobjects.enums.VirtualMachineSize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement (name = "Deployment")
@XmlType (propOrder = {"name", "deploymentSlot", "label", "roleList", "virtualNetworkName"})
@XmlAccessorType (value = XmlAccessType.FIELD)
public class Deployment
{
	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "DeploymentSlot")
	private String deploymentSlot = "Production";

	@XmlElement (name = "Label")
	private String label;

	@XmlElement (name = "RoleList")
	private RoleList roleList = new RoleList();

	@XmlElement (name = "VirtualNetworkName")
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

	// Overwrite properties if used
	public static class Builder
	{
		private String name;
		private String label;
		private String hostname;
		private String username;
		private String password;
		private String sourceImage;
		private String sourceImageLink;
		private int minMemory;
		private int minCpu;
		// TODO: Add custom data disk
		private int minDisk;

		public Builder ()
		{
		}

		public Builder withName ( String name )
		{
			this.name = name;
			return this;
		}

		public Builder withLabel ( String label )
		{
			this.label = label;
			return this;
		}

		public Builder withHostname ( String hostname )
		{
			this.hostname = hostname;
			return this;
		}

		public Builder withUsername ( String username )
		{
			this.username = username;
			return this;
		}

		public Builder withPassword ( String password )
		{
			this.password = password;
			return this;
		}

		public Builder withSourceImage ( String sourceImage )
		{
			this.sourceImage = sourceImage;
			return this;
		}

		public Builder withSourceImageLink ( String sourceImageLink )
		{
			this.sourceImageLink = sourceImageLink;
			return this;
		}

		public Builder withMinCpu ( int minCpu )
		{
			this.minCpu = minCpu;
			return this;
		}

		/**
		 * Specify the minimum amount of memory in MB
		 *
		 * @param minMemory
		 * @return
		 */
		public Builder withMinMemory ( int minMemory )
		{
			this.minMemory = minMemory;
			return this;
		}

		public Builder withMinDisk ( int minDisk )
		{
			this.minDisk = minDisk;
			return this;
		}

		public Deployment build ()
		{
			return new Deployment(this);
		}
	}

	public Deployment ()
	{
	}

	public Deployment ( Builder builder )
	{
		RoleList roleList = this.roleList;
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		ConfigurationSets configurationSets = role.getConfigurationSets();
		List<ConfigurationSet> configurationList = new ArrayList<>();
		ConfigurationSet set = new ConfigurationSet();
		OSVirtualHardDisk osvh = role.getOsVirtualHardDisk();

		set.setHostName(builder.hostname);
		set.setUserName(builder.username);
		set.setUserPassword(builder.password);

		configurationList.add(set);
		configurationSets.setConfigurationSets(configurationList);

		osvh.setSourceImageName(builder.sourceImage);
		osvh.setMediaLink(builder.sourceImageLink);

		role.setRoleName(UUID.randomUUID().toString());
		role.setConfigurationSets(configurationSets);
		role.setOsVirtualHardDisk(osvh);

		VirtualMachineSize size = VirtualMachineSize.ExtraSmall;

		if (builder.minCpu >= 0) size = VirtualMachineSize.Small;
		if (builder.minCpu >= 1) size = VirtualMachineSize.Medium;
		if (builder.minCpu >= 2) size = VirtualMachineSize.Large;
		if (builder.minCpu >= 4) size = VirtualMachineSize.ExtraLarge;

		if (builder.minMemory >= 0 && size.getSize() < VirtualMachineSize.Small.getSize())
			size = VirtualMachineSize.Small;
		if (builder.minMemory >= 1792 && size.getSize() < VirtualMachineSize.Medium.getSize())
			size = VirtualMachineSize.Medium;
		if (builder.minMemory >= 3584 && size.getSize() < VirtualMachineSize.Large.getSize())
			size = VirtualMachineSize.Large;
		if (builder.minMemory >= 7168 && size.getSize() < VirtualMachineSize.ExtraLarge.getSize())
			size = VirtualMachineSize.ExtraLarge;

		if (builder.minCpu <= 1 && builder.minMemory < 1792) size = VirtualMachineSize.ExtraSmall;

		role.setRoleSize(size.toString());

		roles.add(role);
		roleList.setRoles(roles);

		this.setName(builder.name);
		this.setLabel(builder.label);
	}

	@Override
	public String toString ()
	{
		return "Name: " + name + " deploymentSlot: "
				+ deploymentSlot + " label: " + label + " RoleList: "
				+ roleList + " VirtualNetworkName: " + virtualNetworkName;
	}
}