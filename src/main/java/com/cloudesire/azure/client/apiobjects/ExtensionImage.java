package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType (value = XmlAccessType.FIELD)
@XmlType ( name = "ExtensionImage",
 propOrder = { "providerNameSpace", "type", "version", "label", "description",
		"hostingResources", "thumbprintAlgorithm", "publicConfigurationSchema", "privateConfigurationSchema",
		"sampleConfig", "replicationCompleted", "eula", "privacyUri", "homepageUri", "isJsonExtension", "companyName",
		"supportedOS", "publishedDate" } )
@XmlRootElement ( name = "ExtensionImage" )
public class ExtensionImage
{

	@XmlElement (name = "ProviderNameSpace")
	private String providerNameSpace;
	
	@XmlElement (name = "Type")
	private String type;

	@XmlElement (name = "Version")
	private String version;
	
	@XmlElement (name = "Label")
	private String label;
	
	@XmlElement (name = "Description")
	private String description;
	
	@XmlElement (name = "HostingResources")
	private String hostingResources;
	
	@XmlElement (name = "ThumbprintAlgorithm")
	private String thumbprintAlgorithm;
	
	@XmlElement (name = "PublicConfigurationSchema")
	private String publicConfigurationSchema;
	
	@XmlElement (name = "PrivateConfigurationSchema")
	private String privateConfigurationSchema;
	
	@XmlElement (name = "SampleConfig")
	private String sampleConfig;
	
	@XmlElement (name = "ReplicationCompleted")
	private String replicationCompleted;
	
	@XmlElement (name = "Eula")
	private String eula;
	
	@XmlElement (name = "PrivacyUri")
	private String privacyUri;
	
	@XmlElement (name = "HomepageUri")
	private String homepageUri;
	
	@XmlElement (name = "IsJsonExtension")
	private Boolean isJsonExtension;
	
	@XmlElement (name = "CompanyName")
	private String companyName;
	
	@XmlElement (name = "SupportedOS")
	private String supportedOS;
	
	@XmlElement (name = "PublishedDate")
	private String publishedDate;

	public String getProviderNameSpace ()
	{
		return providerNameSpace;
	}

	public void setProviderNameSpace ( String providerNameSpace )
	{
		this.providerNameSpace = providerNameSpace;
	}

	public String getType ()
	{
		return type;
	}

	public void setType ( String type )
	{
		this.type = type;
	}

	public String getVersion ()
	{
		return version;
	}

	public void setVersion ( String version )
	{
		this.version = version;
	}

	public String getLabel ()
	{
		return label;
	}

	public void setLabel ( String label )
	{
		this.label = label;
	}

	public String getDescription ()
	{
		return description;
	}

	public void setDescription ( String description )
	{
		this.description = description;
	}

	public String getHostingResources ()
	{
		return hostingResources;
	}

	public void setHostingResources ( String hostingResources )
	{
		this.hostingResources = hostingResources;
	}

	public String getThumbprintAlgorithm ()
	{
		return thumbprintAlgorithm;
	}

	public void setThumbprintAlgorithm ( String thumbprintAlgorithm )
	{
		this.thumbprintAlgorithm = thumbprintAlgorithm;
	}

	public String getPublicConfigurationSchema ()
	{
		return publicConfigurationSchema;
	}

	public void setPublicConfigurationSchema ( String publicConfigurationSchema )
	{
		this.publicConfigurationSchema = publicConfigurationSchema;
	}

	public String getPrivateConfigurationSchema ()
	{
		return privateConfigurationSchema;
	}

	public void setPrivateConfigurationSchema ( String privateConfigurationSchema )
	{
		this.privateConfigurationSchema = privateConfigurationSchema;
	}

	public String getSampleConfig ()
	{
		return sampleConfig;
	}

	public void setSampleConfig ( String sampleConfig )
	{
		this.sampleConfig = sampleConfig;
	}

	public String getReplicationCompleted ()
	{
		return replicationCompleted;
	}

	public void setReplicationCompleted ( String replicationCompleted )
	{
		this.replicationCompleted = replicationCompleted;
	}

	public String getEula ()
	{
		return eula;
	}

	public void setEula ( String eula )
	{
		this.eula = eula;
	}

	public String getPrivacyUri ()
	{
		return privacyUri;
	}

	public void setPrivacyUri ( String privacyUri )
	{
		this.privacyUri = privacyUri;
	}

	public String getHomepageUri ()
	{
		return homepageUri;
	}

	public void setHomepageUri ( String homepageUri )
	{
		this.homepageUri = homepageUri;
	}

	public Boolean getIsJsonExtension ()
	{
		return isJsonExtension;
	}

	public void setIsJsonExtension ( Boolean isJsonExtension )
	{
		this.isJsonExtension = isJsonExtension;
	}

	public String getCompanyName ()
	{
		return companyName;
	}

	public void setCompanyName ( String companyName )
	{
		this.companyName = companyName;
	}

	public String getSupportedOS ()
	{
		return supportedOS;
	}

	public void setSupportedOS ( String supportedOS )
	{
		this.supportedOS = supportedOS;
	}

	public String getPublishedDate ()
	{
		return publishedDate;
	}

	public void setPublishedDate ( String publishedDate )
	{
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString ()
	{
		return "ExtensionImage [providerNameSpace=" + providerNameSpace + ", type=" + type + ", version=" + version
				+ ", label=" + label + ", description=" + description + ", hostingResources=" + hostingResources
				+ ", thumbprintAlgorithm=" + thumbprintAlgorithm + ", publicConfigurationSchema="
				+ publicConfigurationSchema + ", privateConfigurationSchema=" + privateConfigurationSchema
				+ ", sampleConfig=" + sampleConfig + ", replicationCompleted=" + replicationCompleted + ", eula="
				+ eula + ", privacyUri=" + privacyUri + ", homepageUri=" + homepageUri + ", isJsonExtension="
				+ isJsonExtension + ", companyName=" + companyName + ", supportedOS=" + supportedOS
				+ ", publishedDate=" + publishedDate + "]";
	}

}

