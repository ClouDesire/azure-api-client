package com.cloudesire.azure.client.apiobjects;

import com.cloudesire.azure.client.apiobjects.enums.VirtualMachineSize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlRootElement (name = "Deployment")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class Deployment
{
	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "DeploymentSlot")
	private String deploymentSlot = "Production";

	@XmlElement (name = "PrivateID")
	private String privateIp;

	@XmlElement (name = "Status")
	private String status;

	@XmlElement (name = "Label")
	private String label;

	@XmlElement (name = "Url")
	private String url;

	@XmlElement (name = "RoleInstanceList")
	private RoleInstanceList roleInstanceList = new RoleInstanceList();

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

	public RoleInstanceList getRoleInstanceList ()
	{
		return roleInstanceList;
	}

	public void setRoleInstanceList ( RoleInstanceList roleInstanceList )
	{
		this.roleInstanceList = roleInstanceList;
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
		private String dataImageLink;
		private String fingerprint;
		private int minMemory;
		private int minCpu;
		private int minDisk;
		private List<InputEndpoint> inputEndpoints;

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

		public Builder withDataImageLink ( String dataImageLink )
		{
			this.dataImageLink = dataImageLink;
			return this;
		}

		public Builder withFingerprint ( String fingerprint )
		{
			this.fingerprint = fingerprint;
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
		 * @return Builder
		 */
		public Builder withMinMemory ( int minMemory )
		{
			this.minMemory = minMemory;
			return this;
		}

		/**
		 * Specify the minimum amount of disk in GB
		 *
		 * @param minDisk
		 * @return Builder
		 */
		public Builder withMinDisk ( int minDisk )
		{
			this.minDisk = minDisk;
			return this;
		}

		public Builder withInputEndpoints ( List<InputEndpoint> ie )
		{
			this.inputEndpoints = ie;
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
		ConfigurationSet newtworkSet = new ConfigurationSet();
		OSVirtualHardDisk osvh = role.getOsVirtualHardDisk();
		List<DataVirtualHardDisk> disksList = new ArrayList<>();

		InputEndpoints endpoints = new InputEndpoints();
		endpoints.setEndpoints(builder.inputEndpoints);

		newtworkSet.setConfigurationSetType("NetworkConfiguration");
		newtworkSet.setConfigurationSetTypeAttribute("NetworkConfiguration");
		newtworkSet.setInputEndpoints(endpoints);

		SshKeyContainer ssh = new SshKeyContainer();
		PublicKey pk = new PublicKey();
		KeyPair kp = new KeyPair();

		if (builder.fingerprint != null)
		{
			pk.setFingerPrint(builder.fingerprint);
			pk.setPath(String.format("/home/%s/.ssh/authorized_keys", builder.username));

			kp.setFingerPrint(builder.fingerprint);
			kp.setPath(String.format("/home/%s/.ssh/id_rsa", builder.username));

			ssh.getPublicKeys().add(pk);
			ssh.getKeyPairs().add(kp);

			set.setSsh(ssh);
		}


		set.setHostName(builder.hostname);
		set.setUserName(builder.username);
		set.setUserPassword(builder.password);

		configurationList.add(set);
		configurationList.add(newtworkSet);
		configurationSets.setConfigurationSets(configurationList);

		osvh.setSourceImageName(builder.sourceImage);
		osvh.setMediaLink(builder.sourceImageLink);

		role.setRoleName(builder.name + "-" + UUID.randomUUID().toString());
		role.setConfigurationSets(configurationSets);
		role.setOsVirtualHardDisk(osvh);

		if (builder.dataImageLink != null)
		{
			DataVirtualHardDisks disks = role.getDataVirtualHardDisks();
			DataVirtualHardDisk disk = new DataVirtualHardDisk();
			disk.setLogicalDiskSizeInGB(builder.minDisk);
			disk.setMediaLink(builder.dataImageLink);
			disk.setDiskLabel(builder.label);
			disksList.add(disk);
			disks.setDataVirtualHardDisks(disksList);

			role.setDataVirtualHardDisks(disks);
		}

		VirtualMachineSize size = VirtualMachineSize.getSize(builder.minCpu, builder.minMemory);

		role.setRoleSize(size.toString());

		roles.add(role);
		roleList.setRoles(roles);

		this.setName(builder.name);
		this.setLabel(builder.label);
	}

	@Override
	public String toString ()
	{
		return "Name: " + name + " DeloymentSlot: "
				+ deploymentSlot + " label: " + label + " RoleList: "
				+ roleList + " RoleInstanceList: " + roleInstanceList + " VirtualNetworkName: " + virtualNetworkName;
	}
}