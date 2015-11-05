package com.cloudesire.azure.client.apiobjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType ( value = XmlAccessType.FIELD )
@XmlType ( name = "ResourceExtensionReference", propOrder = { "referenceName", "publisher", "name", "version",
        "resourceExtensionParameterValues" } )
@XmlRootElement ( name = "ResourceExtensionReference" )
public class ResourceExtensionReference
{
    // ResourceExtension [publisher=Microsoft.Compute,
    // name=CustomScriptExtension, version=1.1, label=Custom Script,
    // description=Custom Script Handler runs the specified script on the VM,
    // publicConfigurationSchema=, privateConfigurationSchema=,
    // sampleConfig=null, replicationCompleted=null, eula=null, privacyUri=null,
    // homepageUri=null, isJsonExtension=null, isInternalExtension=null,
    // disallowMajorVersionUpgrade=null, companyName=null, supportedOS=null,
    // publishedDate=null]

    @XmlElement ( name = "ReferenceName" )
    private String referenceName;
    @XmlElement ( name = "Publisher" )
    private String publisher;
    @XmlElement ( name = "Name" )
    private String name;
    @XmlElement ( name = "Version" )
    private String version = "*";
    @XmlElement ( name = "ResourceExtensionParameterValues" )
    private ResourceExtensionParameterValues resourceExtensionParameterValues = new ResourceExtensionParameterValues();

    public String getReferenceName()
    {
        return referenceName;
    }

    public void setReferenceName( String referenceName )
    {
        this.referenceName = referenceName;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher( String publisher )
    {
        this.publisher = publisher;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public ResourceExtensionParameterValues getResourceExtensionParameterValues()
    {
        return resourceExtensionParameterValues;
    }

    public void setResourceExtensionParameterValues( ResourceExtensionParameterValues resourceExtensionParameterValues )
    {
        this.resourceExtensionParameterValues = resourceExtensionParameterValues;
    }

    @Override
    public String toString()
    {
        return "ResourceExtensionReference [referenceName=" + referenceName + ", publisher=" + publisher + ", name="
                + name + ", version=" + version + ", resourceExtensionParameterValues="
                + resourceExtensionParameterValues + "]";
    }

}
