package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "OSImage")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class OSImage
{
	@XmlElement (name = "AffinityGroup")
	private String affinityGroup;

	@XmlElement (name = "Category")
	private String category;

	@XmlElement (name = "Label")
	private String label;

	@XmlElement (name = "Location")
	private String location;

	@XmlElement (name = "LogicalSizeInGB")
	private int logicalSizeInGB;

	@XmlElement (name = "MediaLink")
	private String mediaLink;

	@XmlElement (name = "Name")
	private String name;

	@XmlElement (name = "OS")
	private String os;

	@XmlElement (name = "Eula")
	private String eula;

	@XmlElement (name = "Description")
	private String description;

	@XmlElement (name = "ImageFamily")
	private String imageFamily;

	@XmlElement (name = "ShowInGui")
	private boolean showInGui;

	@XmlElement (name = "PublishedDate")
	private String publishedDate;

	@XmlElement (name = "IsPremium")
	private boolean isPremium;

	@XmlElement (name = "PrivacyUri")
	private String privacyUri;

	@XmlElement (name = "RecommendedVMSize")
	private String recommendedVMSize;

	@XmlElement (name = "PublisherName")
	private String publisherName;

	@XmlElement (name = "PricingDetailLink")
	private String pricingDetailLink;

	@XmlElement (name = "SmallIconUri")
	private String smallIconUri;

	@XmlElement (name = "IconUri")
	private String IconUri;

	@XmlElement (name = "Language")
	private String language;

	public String getAffinityGroup()
	{
		return affinityGroup;
	}

	public String getCategory()
	{
		return category;
	}

	public String getLabel()
	{
		return label;
	}

	public String getLocation()
	{
		return location;
	}

	public int getLogicalSizeInGB()
	{
		return logicalSizeInGB;
	}

	public String getMediaLink()
	{
		return mediaLink;
	}

	public String getName()
	{
		return name;
	}

	public String getOs()
	{
		return os;
	}

	public String getEula()
	{
		return eula;
	}

	public String getDescription()
	{
		return description;
	}

	public String getImageFamily()
	{
		return imageFamily;
	}

	public boolean isShowInGui()
	{
		return showInGui;
	}

	public String getPublishedDate()
	{
		return publishedDate;
	}

	public boolean isPremium()
	{
		return isPremium;
	}

	public String getPrivacyUri()
	{
		return privacyUri;
	}

	public String getRecommendedVMSize()
	{
		return recommendedVMSize;
	}

	public String getPublisherName()
	{
		return publisherName;
	}

	public String getPricingDetailLink()
	{
		return pricingDetailLink;
	}

	public String getSmallIconUri()
	{
		return smallIconUri;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setAffinityGroup( String affinityGroup )
	{
		this.affinityGroup = affinityGroup;
	}

	public void setCategory( String category )
	{
		this.category = category;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}

	public void setLocation( String location )
	{
		this.location = location;
	}

	public void setLogicalSizeInGB( int logicSizeInGB )
	{
		this.logicalSizeInGB = logicSizeInGB;
	}

	public void setMediaLink( String mediaLink )
	{
		this.mediaLink = mediaLink;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public void setOs( String os )
	{
		this.os = os;
	}

	public void setEula( String eula )
	{
		this.eula = eula;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public void setImageFamily( String imageFamily )
	{
		this.imageFamily = imageFamily;
	}

	public void setShowInGui( boolean showInGui )
	{
		this.showInGui = showInGui;
	}

	public void setPublishedDate( String publishedDate )
	{
		this.publishedDate = publishedDate;
	}

	public void setPremium( boolean isPremium )
	{
		this.isPremium = isPremium;
	}

	public void setPrivacyUri( String privacyUri )
	{
		this.privacyUri = privacyUri;
	}

	public void setRecommendedVMSize( String recommendedVMSize )
	{
		this.recommendedVMSize = recommendedVMSize;
	}

	public void setPublisherName( String publisherName )
	{
		this.publisherName = publisherName;
	}

	public void setPricingDetailLink( String pricingDetailLink )
	{
		this.pricingDetailLink = pricingDetailLink;
	}

	public void setSmallIconUri( String smallIconUri )
	{
		this.smallIconUri = smallIconUri;
	}

	public void setLanguage( String language )
	{
		this.language = language;
	}

	@Override
	public String toString()
	{
		return "Name: " + name + " OS: " + os + " MediaLink: " + mediaLink + " Location: " + location;
	}
}
