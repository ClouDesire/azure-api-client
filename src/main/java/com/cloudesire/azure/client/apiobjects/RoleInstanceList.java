package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlType (name = "RoleInstanceList")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class RoleInstanceList implements Iterable<RoleInstance>
{
	@XmlElement (name = "RoleInstance")
	private List<RoleInstance> roles = new ArrayList<>();

	@Override
	public Iterator<RoleInstance> iterator()
	{
		return roles.iterator();
	}

	public List<RoleInstance> getRoles()
	{
		return roles;
	}

	public void setRoles( List<RoleInstance> roles )
	{
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		String ret = " RoleInstanceList: ";
		for ( RoleInstance role : roles )
		{
			ret += " RoleInstance: " + role;
		}
		return ret;
	}
}
