package com.cloudesire.azure.client.apiobjects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@JacksonXmlRootElement ( localName = "OSImage" )
public class OSImage
{
	@JacksonXmlProperty ( localName = "AffinityGroup" )
	private String affinityGroup;

	@JacksonXmlProperty ( localName = "Category" )
	private String category;

	@JacksonXmlProperty ( localName = "Label" )
	private String label;

	@JacksonXmlProperty ( localName = "Location" )
	private String location;

	@JacksonXmlProperty ( localName = "LogicalSizeInGB" )
	private int logicalSizeInGB;

	@JacksonXmlProperty ( localName = "MediaLink" )
	private String mediaLink;

	@JacksonXmlProperty ( localName = "Name" )
	private String name;

	@JacksonXmlProperty ( localName = "OS" )
	private String os;

	@JacksonXmlProperty ( localName = "Eula" )
	private String eula;

	@JacksonXmlProperty ( localName = "Description" )
	private String description;

	@JacksonXmlProperty ( localName = "ImageFamily" )
	private String imageFamily;

	@JacksonXmlProperty ( localName = "ShowInGui" )
	private boolean showInGui;

	@JacksonXmlProperty ( localName = "PublishedDate" )
	private String publishedDate;

	@JacksonXmlProperty ( localName = "IsPremium" )
	private boolean isPremium;

	@JacksonXmlProperty ( localName = "PrivacyUri" )
	private String privacyUri;

	@JacksonXmlProperty ( localName = "RecommendedVMSize" )
	private String recommendedVMSize;

	@JacksonXmlProperty ( localName = "PublisherName" )
	private String publisherName;

	@JacksonXmlProperty ( localName = "PricingDetailLink" )
	private String pricingDetailLink;

	@JacksonXmlProperty ( localName = "SmallIconUri" )
	private String smallIconUri;

	@JacksonXmlProperty ( localName = "IconUri" )
	private String IconUri;

	@JacksonXmlProperty ( localName = "Language" )
	private String language;

	public String getAffinityGroup ()
	{
		return affinityGroup;
	}

	public String getCategory ()
	{
		return category;
	}

	public String getLabel ()
	{
		return label;
	}

	public String getLocation ()
	{
		return location;
	}

	public int getLogicalSizeInGB ()
	{
		return logicalSizeInGB;
	}

	public String getMediaLink ()
	{
		return mediaLink;
	}

	public String getName ()
	{
		return name;
	}

	public String getOs ()
	{
		return os;
	}

	public String getEula ()
	{
		return eula;
	}

	public String getDescription ()
	{
		return description;
	}

	public String getImageFamily ()
	{
		return imageFamily;
	}

	public boolean isShowInGui ()
	{
		return showInGui;
	}

	public String getPublishedDate ()
	{
		return publishedDate;
	}

	public boolean isPremium ()
	{
		return isPremium;
	}

	public String getPrivacyUri ()
	{
		return privacyUri;
	}

	public String getRecommendedVMSize ()
	{
		return recommendedVMSize;
	}

	public String getPublisherName ()
	{
		return publisherName;
	}

	public String getPricingDetailLink ()
	{
		return pricingDetailLink;
	}

	public String getSmallIconUri ()
	{
		return smallIconUri;
	}

	public String getLanguage ()
	{
		return language;
	}

	public void setAffinityGroup ( String affinityGroup )
	{
		this.affinityGroup = affinityGroup;
	}

	public void setCategory ( String category )
	{
		this.category = category;
	}

	public void setLabel ( String label )
	{
		this.label = label;
	}

	public void setLocation ( String location )
	{
		this.location = location;
	}

	public void setLogicalSizeInGB ( int logicSizeInGB )
	{
		this.logicalSizeInGB = logicSizeInGB;
	}

	public void setMediaLink ( String mediaLink )
	{
		this.mediaLink = mediaLink;
	}

	public void setName ( String name )
	{
		this.name = name;
	}

	public void setOs ( String os )
	{
		this.os = os;
	}

	public void setEula ( String eula )
	{
		this.eula = eula;
	}

	public void setDescription ( String description )
	{
		this.description = description;
	}

	public void setImageFamily ( String imageFamily )
	{
		this.imageFamily = imageFamily;
	}

	public void setShowInGui ( boolean showInGui )
	{
		this.showInGui = showInGui;
	}

	public void setPublishedDate ( String publishedDate )
	{
		this.publishedDate = publishedDate;
	}

	public void setPremium ( boolean isPremium )
	{
		this.isPremium = isPremium;
	}

	public void setPrivacyUri ( String privacyUri )
	{
		this.privacyUri = privacyUri;
	}

	public void setRecommendedVMSize ( String recommendedVMSize )
	{
		this.recommendedVMSize = recommendedVMSize;
	}

	public void setPublisherName ( String publisherName )
	{
		this.publisherName = publisherName;
	}

	public void setPricingDetailLink ( String pricingDetailLink )
	{
		this.pricingDetailLink = pricingDetailLink;
	}

	public void setSmallIconUri ( String smallIconUri )
	{
		this.smallIconUri = smallIconUri;
	}

	public void setLanguage ( String language )
	{
		this.language = language;
	}

	@Override
	public String toString()
	{
		return "Name: " + name + " OS: " + os;
	}
}
