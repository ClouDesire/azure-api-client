package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType ( name = "Role" )
@XmlAccessorType ( value = XmlAccessType.FIELD )
public class Role
{
    @XmlElement ( name = "RoleName" )
    private String roleName;

    @XmlElement ( name = "RoleType" )
    private String roleType = "PersistentVMRole";

    @XmlElement ( name = "ConfigurationSets" )
    private ConfigurationSets configurationSets = new ConfigurationSets();

    @XmlElement ( name = "ResourceExtensionReferences" )
    private ResourceExtensionReferences resourceExtensionReferences;

    @XmlElement ( name = "DataVirtualHardDisks" )
    private DataVirtualHardDisks dataVirtualHardDisks = new DataVirtualHardDisks();

    @XmlElement ( name = "OSVirtualHardDisk" )
    private OSVirtualHardDisk osVirtualHardDisk = new OSVirtualHardDisk();

    @XmlElement ( name = "RoleSize" )
    private String roleSize;

    @XmlElement ( name = "ProvisionGuestAgent" )
    private Boolean provisionGuestAgent;

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName( String roleName )
    {
        this.roleName = roleName;
    }

    public String getRoleType()
    {
        return roleType;
    }

    public void setRoleType( String roleType )
    {
        this.roleType = roleType;
    }

    public ConfigurationSets getConfigurationSets()
    {
        return configurationSets;
    }

    public void setConfigurationSets( ConfigurationSets configurationSets )
    {
        this.configurationSets = configurationSets;
    }

    public ResourceExtensionReferences getResourceExtensionReferences()
    {
        return resourceExtensionReferences;
    }

    public void setResourceExtensionReferences( ResourceExtensionReferences resourceExtensionReferences )
    {
        this.resourceExtensionReferences = resourceExtensionReferences;
    }

    public OSVirtualHardDisk getOsVirtualHardDisk()
    {
        return osVirtualHardDisk;
    }

    public void setOsVirtualHardDisk( OSVirtualHardDisk osVirtualHardDisk )
    {
        this.osVirtualHardDisk = osVirtualHardDisk;
    }

    public DataVirtualHardDisks getDataVirtualHardDisks()
    {
        return dataVirtualHardDisks;
    }

    public void setDataVirtualHardDisks( DataVirtualHardDisks dataVirtualHardDisks )
    {
        this.dataVirtualHardDisks = dataVirtualHardDisks;
    }

    public String getRoleSize()
    {
        return roleSize;
    }

    public void setRoleSize( String roleSize )
    {
        this.roleSize = roleSize;
    }

    public Boolean getProvisionGuestAgent()
    {
        return provisionGuestAgent;
    }

    public void setProvisionGuestAgent( Boolean provisionGuestAgent )
    {
        this.provisionGuestAgent = provisionGuestAgent;
    }

    @Override
    public String toString()
    {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", roleType='" + roleType + '\'' +
                ", " + configurationSets +
                ", resourceExtensionReferences=" + resourceExtensionReferences +
                ", dataVirtualHardDisks=" + dataVirtualHardDisks +
                ", osVirtualHardDisk=" + osVirtualHardDisk +
                ", roleSize='" + roleSize + '\'' +
                ", provisionGuestAgent=" + provisionGuestAgent +
                '}';
    }
}
