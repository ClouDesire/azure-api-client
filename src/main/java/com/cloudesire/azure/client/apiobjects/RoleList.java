package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Iterator;
import java.util.List;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
@XmlType (name = "RoleList")
@XmlAccessorType (value = XmlAccessType.FIELD)
public class RoleList implements Iterable<Role>
{
	@XmlElement (name = "Role")
	private List<Role> roles;

	@Override
	public Iterator<Role> iterator()
	{
		return roles.iterator();
	}

	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles( List<Role> roles )
	{
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		String ret = " RoleList: ";
		for ( Role role : roles )
		{
			ret += " Role: " + role;
		}
		return ret;
	}
}