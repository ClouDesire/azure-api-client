package com.cloudesire.azure.client.apiobjects;

import com.cloudesire.azure.client.apiobjects.Deployment.Builder.OSFamily;
import com.cloudesire.azure.client.apiobjects.enums.VirtualMachineSize;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	public String getName()
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}

	public String getDeploymentSlot()
	{
		return deploymentSlot;
	}

	public void setDeploymentSlot( String deploymentSlot )
	{
		this.deploymentSlot = deploymentSlot;
	}

	public String getLabel()
	{
		return label;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setLabel( String label )
	{
		this.label = label;
	}
	
	public void setUrl( String url )
	{
		this.url = url;
	}

	public RoleList getRoleList()
	{
		return roleList;
	}

	public RoleInstanceList getRoleInstanceList()
	{
		return roleInstanceList;
	}

	public void setRoleInstanceList( RoleInstanceList roleInstanceList )
	{
		this.roleInstanceList = roleInstanceList;
	}

	public void setRoleList( RoleList roleList )
	{
		this.roleList = roleList;
	}

	public String getVirtualNetworkName()
	{
		return virtualNetworkName;
	}

	public void setVirtualNetworkName( String virtualNetworkName )
	{
		this.virtualNetworkName = virtualNetworkName;
	}

	// Overwrite properties if used
	public static class Builder
	{
		public static enum OSFamily
		{
			Linux, Windows
		}
		public static enum WinRMProtocol
		{
			Http , Https 
		}

		private String name;
		private String label;
		private String url;
		private String hostname;
		private String username;
		private String password;
		private String sourceImage;
		private String sourceImageLink;
		private String dataImageLink;
		private String fingerprint;
		private OSFamily osFamily = OSFamily.Linux;
		private WinRM winRM = new WinRM(new Listener(WinRMProtocol.Http));
		private int minMemory;
		private int minCpu;
		private int minDisk;
		private List<InputEndpoint> inputEndpoints;
		private String roleName;
		private String accountJson;
		private String scriptJson;
		private boolean enableWindowsAutomaticUpdates = false;
		private boolean disableSSHPasswordAuthentication = false;
		private String publicIPName;

		public Builder()
		{
		}
		
		public Builder setWinRMP( WinRM winRM )
		{
			this.winRM = winRM;
			return this;
		}
		
		public Builder setEnableWindowsAutomaticUpdates( boolean enableWindowsAutomaticUpdates )
		{
			this.enableWindowsAutomaticUpdates = enableWindowsAutomaticUpdates;
			return this;
		}

		public Builder withAccountJson( String accountJson )
		{
			this.accountJson = accountJson;
			return this;
		}

		public Builder withScriptJson( String scriptJson )
		{
			this.scriptJson = scriptJson;
			return this;
		}

		public Builder withOSFamily( OSFamily osFamily )
		{
			this.osFamily = osFamily;
			return this;
		}

		public Builder withName( String name )
		{
			this.name = sanitize(name);
			return this;
		}

		public Builder withLabel( String label )
		{
			this.label = label;
			return this;
		}
		
		public Builder withUrl( String url )
		{
			this.url = url;
			return this;
		}

		public Builder withHostname( String hostname )
		{
			this.hostname = hostname;
			return this;
		}

		public Builder withUsername( String username )
		{
			this.username = username;
			return this;
		}

		public Builder withPassword( String password )
		{
			this.password = password;
			return this;
		}

		public Builder withSourceImage( String sourceImage )
		{
			this.sourceImage = sourceImage;
			return this;
		}

		public Builder withSourceImageLink( String sourceImageLink )
		{
			this.sourceImageLink = sourceImageLink;
			return this;
		}

		public Builder withDataImageLink( String dataImageLink )
		{
			this.dataImageLink = dataImageLink;
			return this;
		}

		public Builder withFingerprint( String fingerprint )
		{
			this.fingerprint = fingerprint;
			return this;
		}

		public Builder withMinCpu( int minCpu )
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
		public Builder withMinMemory( int minMemory )
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
		public Builder withMinDisk( int minDisk )
		{
			this.minDisk = minDisk;
			return this;
		}

		public Builder withInputEndpoints( List<InputEndpoint> ie )
		{
			this.inputEndpoints = ie;
			return this;
		}

		public Builder withRoleName( String roleName )
		{
			this.roleName = roleName;
			return this;
		}

		public Builder disableSSHPasswordAuthentication()
		{
			this.disableSSHPasswordAuthentication = true;
			return this;
		}

		public Builder withPublicIP( String publicIPName )
		{
			this.publicIPName = publicIPName;
			return this;
		}

		public Deployment build()
		{
			return new Deployment(this);
		}
	}

	public Deployment()
	{
	}

	public Deployment( Builder builder )
	{
		final Logger log = LoggerFactory.getLogger(this.getClass());

		RoleList roleList = this.roleList;
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		// TODO rifattorizzare la classe ConfigurationSet e splittarla in più
		// classi diverse a seconda se WindowsConfig, LinuxConfig o
		// NetworkConfig
		ConfigurationSets configurationSets = role.getConfigurationSets();
		List<ConfigurationSet> configurationList = new ArrayList<>();
		ConfigurationSet set = new ConfigurationSet();
		ConfigurationSet networkSet = new ConfigurationSet();
		OSVirtualHardDisk osvh = role.getOsVirtualHardDisk();
		List<DataVirtualHardDisk> disksList = new ArrayList<>();

		InputEndpoints endpoints = new InputEndpoints();
		endpoints.setEndpoints(builder.inputEndpoints);

		networkSet.setConfigurationSetType("NetworkConfiguration");
		networkSet.setConfigurationSetTypeAttribute("NetworkConfiguration");

		networkSet.setInputEndpoints(endpoints);


		osvh.setSourceImageName(builder.sourceImage);
		osvh.setMediaLink(builder.sourceImageLink);

		if ( builder.roleName != null )
			role.setRoleName(builder.roleName);
		else if ( builder.hostname != null )
			role.setRoleName(builder.hostname);
		else
			role.setRoleName(builder.name + "-" + UUID.randomUUID().toString());

		// WINDOWS OS
		if ( builder.osFamily.equals(OSFamily.Windows) )
		{
			if ( builder.accountJson != null || builder.scriptJson != null )
			{
				role.setResourceExtensionReferences(new ResourceExtensionReferences());
				role.getResourceExtensionReferences().setResourceExtensionReferences(
						setupCustomScriptExtension(builder.accountJson, builder.scriptJson));
				role.setProvisionGuestAgent(true);
				
			}
			set.setConfigurationSetType("WindowsProvisioningConfiguration");
			set.setConfigurationSetTypeAttribute("WindowsProvisioningConfiguration");
			set.setEnableAutomaticUpdates(builder.enableWindowsAutomaticUpdates);
			set.setComputerName(builder.hostname);
			set.setAdminPassword(builder.password);
			set.setAdminUsername(builder.username);
			set.setWinRm(builder.winRM);
			
		}
		// LINUX OS
		else
		{
			SshKeyContainer ssh = new SshKeyContainer();
			PublicKey pk = new PublicKey();
			KeyPair kp = new KeyPair();

			if ( builder.fingerprint != null )
			{
				pk.setFingerPrint(builder.fingerprint);
				String authorizedKeysFile = String.format("/home/%s/.ssh/authorized_keys", builder.username);
				pk.setPath(authorizedKeysFile);
				log.debug(String.format("Added fingerprint to %s", authorizedKeysFile));

				kp.setFingerPrint(builder.fingerprint);
				String idRSAFile = String.format("/home/%s/.ssh/id_rsa", builder.username);
				log.debug(String.format("Added fingerprint to %s", idRSAFile));
				kp.setPath(idRSAFile);

				ssh.getPublicKeys().add(pk);
				ssh.getKeyPairs().add(kp);
				set.setSsh(ssh);
			}
			set.setConfigurationSetType("LinuxProvisioningConfiguration");
			set.setConfigurationSetTypeAttribute("LinuxProvisioningConfiguration");
			set.setHostName(builder.hostname);
			set.setUserName(builder.username);
			set.setUserPassword(builder.password);
			set.setDisableSshPasswordAuthentication(builder.disableSSHPasswordAuthentication);
		}

		if(builder.publicIPName != null)
		{
			PublicIP publicIP = new PublicIP();
			publicIP.setName(builder.publicIPName);
			publicIP.setIdleTimeoutInMinutes("30");
			List<PublicIP> publicIPList = new ArrayList<>();
			publicIPList.add(publicIP);
			PublicIPs publicIPs = new PublicIPs();
			publicIPs.setPublicIPs(publicIPList);
			networkSet.setPublicIPs(publicIPs);
		}

		configurationList.add(set);
		configurationList.add(networkSet);
		configurationSets.setConfigurationSets(configurationList);
		role.setConfigurationSets(configurationSets);
		role.setOsVirtualHardDisk(osvh);

		if ( builder.dataImageLink != null )
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
		this.setUrl(builder.url);
	}

	private List<ResourceExtensionReference> setupCustomScriptExtension( String accountJson, String scriptJson )
	{
		List<ResourceExtensionReference> extensions = new ArrayList<>();
		ResourceExtensionReference reference = new ResourceExtensionReference();
		reference.setName("CustomScriptExtension");
		reference.setPublisher("Microsoft.Compute");
		reference.setVersion("1.1");
		reference.setReferenceName("MyCustomScriptExtension");
		reference.getResourceExtensionParameterValues().setResourceExtensionParameterValues(
				setupParameterValuesForCustomScript(accountJson, scriptJson));
		extensions.add(reference);
		return extensions;
	}

	private List<ResourceExtensionParameterValue> setupParameterValuesForCustomScript( String accountJson,
	                                                                                   String scriptJson )
	{

		try
		{
			List<ResourceExtensionParameterValue> pvalues = new ArrayList<>();
			if ( scriptJson != null )
				pvalues.add(setupParameterValue("CustomScriptExtensionPublicConfigParameter",
						Base64.encode(scriptJson.getBytes("UTF-8")), "Public"));
			if ( accountJson != null )
				pvalues.add(setupParameterValue("CustomScriptExtensionPrivateConfigParameter",
						Base64.encode(accountJson.getBytes("UTF-8")), "Private"));
			return pvalues;
		} catch ( UnsupportedEncodingException e )
		{
			throw new IllegalArgumentException(e);
		}
	}

	private ResourceExtensionParameterValue setupParameterValue( String key, String value, String type )
	{
		ResourceExtensionParameterValue pvalue = new ResourceExtensionParameterValue();
		pvalue.setKey(key);
		pvalue.setValue(value);
		pvalue.setType(type);
		return pvalue;
	}

	public static String sanitize( String uncleanStr )
	{
		if ( uncleanStr == null ) return "";
		String cleanStr;
		cleanStr = uncleanStr.replaceAll("[^a-zA-Z0-9]", "-");
		cleanStr = cleanStr.replaceAll("_", "-").replaceAll(" ", "");
		return cleanStr;
	}

	@Override
	public String toString()
	{
		return "Name: " + name + " DeploymentSlot: "
				+ deploymentSlot + " label: " + label + " Url: " + url + " "
				+ roleList + " " + roleInstanceList + " VirtualNetworkName: " + virtualNetworkName;
	}
}
