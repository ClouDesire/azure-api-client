package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (name = "ConfigurationSets")
public class ConfigurationSets
{
	@XmlElement (name = "ConfigurationSet")
	private List<ConfigurationSet> configurationSets;

	public List<ConfigurationSet> getConfigurationSets()
	{
		return configurationSets;
	}

	public void setConfigurationSets( List<ConfigurationSet> configurationSets )
	{
		this.configurationSets = configurationSets;
	}

	@Override
	public String toString()
	{
		String ret = " ConfigurationSets: ";
		for ( ConfigurationSet cf : configurationSets )
		{
			ret += " " + cf;
		}
		return ret;
	}
}
