package com.cloudesire.azure.client.apiobjects.enums;

public enum DeploymentSlot
{
    PRODUCTION( "production" ),
    STAGING( "staging" );

    private final String name;

    DeploymentSlot( String s )
    {
        name = s;
    }

    public boolean equalsName( String otherName )
    {
        return ( otherName == null ) ? false : name.equals( otherName );
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
