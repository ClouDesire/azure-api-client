package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType (value = XmlAccessType.FIELD)
@XmlType ( name = "ResourceExtension",
 propOrder = { "publisher", "name", "version", "label", "description",
		"publicConfigurationSchema", "privateConfigurationSchema", "sampleConfig", "replicationCompleted", "eula",
		"privacyUri", "homepageUri", "isJsonExtension", "isInternalExtension", "disallowMajorVersionUpgrade",
		"companyName", "supportedOS", "publishedDate" } )
@XmlRootElement ( name = "ResourceExtension" )
public class ResourceExtension
{
	@XmlElement (name = "Publisher")
	private String publisher;
	@XmlElement (name = "Name")
	private String name;
	@XmlElement (name = "Version")
	private String version;
	@XmlElement (name = "Label")
	private String label;
	@XmlElement (name = "Description")
	private String description;
	@XmlElement (name = "PublicConfigurationSchema")
	private String publicConfigurationSchema;
	@XmlElement (name = "PrivateConfigurationSchema")
	private String privateConfigurationSchema;
	@XmlElement (name = "SampleConfig")
	private String sampleConfig;
	@XmlElement (name = "ReplicationCompleted")
	private Boolean replicationCompleted;
	@XmlElement (name = "Eula")
	private String eula;
	@XmlElement (name = "PrivacyUri")
	private String privacyUri;
	@XmlElement (name = "HomepageUri")
	private String homepageUri;
	@XmlElement ( name = "IsJsonExtension" )
	private Boolean isJsonExtension;
	@XmlElement ( name = "IsInternalExtension" )
	private Boolean isInternalExtension;
	@XmlElement ( name = "DisallowMajorVersionUpgrade" )
	private Boolean disallowMajorVersionUpgrade;
	@XmlElement ( name = "CompanyName" )
	private String companyName;
	@XmlElement ( name = "SupportedOS" )
	private String supportedOS;
	@XmlElement ( name = "PublishedDate" )
	private String publishedDate;

	public String getPublisher ()
	{
		return publisher;
	}

	public void setPublisher ( String publisher )
	{
		this.publisher = publisher;
	}

	public String getName ()
	{
		return name;
	}

	public void setName ( String name )
	{
		this.name = name;
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

	public Boolean getReplicationCompleted ()
	{
		return replicationCompleted;
	}

	public void setReplicationCompleted ( Boolean replicationCompleted )
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

	public Boolean getIsInternalExtension ()
	{
		return isInternalExtension;
	}

	public void setIsInternalExtension ( Boolean isInternalExtension )
	{
		this.isInternalExtension = isInternalExtension;
	}

	public Boolean getDisallowMajorVersionUpgrade ()
	{
		return disallowMajorVersionUpgrade;
	}

	public void setDisallowMajorVersionUpgrade ( Boolean disallowMajorVersionUpgrade )
	{
		this.disallowMajorVersionUpgrade = disallowMajorVersionUpgrade;
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
		return "ResourceExtension [publisher=" + publisher + ", name=" + name + ", version=" + version + ", label="
				+ label + ", description=" + description + ", publicConfigurationSchema=" + publicConfigurationSchema
				+ ", privateConfigurationSchema=" + privateConfigurationSchema + ", sampleConfig=" + sampleConfig
				+ ", replicationCompleted=" + replicationCompleted + ", eula=" + eula + ", privacyUri=" + privacyUri
				+ ", homepageUri=" + homepageUri + ", isJsonExtension=" + isJsonExtension + ", isInternalExtension="
				+ isInternalExtension + ", disallowMajorVersionUpgrade=" + disallowMajorVersionUpgrade
				+ ", companyName=" + companyName + ", supportedOS=" + supportedOS + ", publishedDate=" + publishedDate
				+ "]";
	}

}

