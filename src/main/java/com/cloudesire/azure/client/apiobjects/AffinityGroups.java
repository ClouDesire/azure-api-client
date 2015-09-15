package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement (name = "AffinityGroups")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class AffinityGroups
{
	@XmlElement (name = "AffinityGroup")
	private List<AffinityGroup> affinityGroups;

	public List<AffinityGroup> getAffinityGroups()
	{
		return affinityGroups;
	}

	public void setAffinityGroups( List<AffinityGroup> affinityGroups )
	{
		this.affinityGroups = affinityGroups;
	}
}
