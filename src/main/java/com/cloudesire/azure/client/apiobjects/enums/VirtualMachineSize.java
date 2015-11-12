package com.cloudesire.azure.client.apiobjects.enums;

public enum VirtualMachineSize
{
    Basic_A0( 1, 768 ),
    Basic_A1( 1, 1792 ),
    Basic_A2( 2, 3584 ),
    Basic_A3( 4, 7168 ),
    Basic_A4( 8, 14336 ),
    ExtraSmall( 1, 768 ),
    Small( 1, 1792 ),
    Medium( 2, 3584 ),
    Large( 4, 7168 ),
    ExtraLarge( 8, 14336 ),
    A5( 2, 14336 ),
    A6( 4, 28672 ),
    A7( 8, 57344 ),
    A8( 8, 57344 ),
    A9( 16, 114688 ),
    A10( 8, 57344 ),
    A11( 16, 114688 ),
    Standard_D1( 1, 3584 ),
    Standard_D2( 2, 7168 ),
    Standard_D3( 4, 14336 ),
    Standard_D4( 8, 28672 ),
    Standard_D11( 2, 14336 ),
    Standard_D12( 4, 28672 ),
    Standard_D13( 8, 57344 ),
    Standard_D14( 16, 114688 ),
    Standard_D1_v2( 1, 3584 ),
    Standard_D2_v2( 2, 7168 ),
    Standard_D3_v2( 4, 14336 ),
    Standard_D4_v2( 8, 28672 ),
    Standard_D5_v2( 16, 57344 ),
    Standard_D11_v2( 2, 14336 ),
    Standard_D12_v2( 4, 28672 ),
    Standard_D13_v2( 8, 57344 ),
    Standard_D14_v2( 16, 114688 ),
    Standard_DS1( 2, 3584 ),
    Standard_DS2( 2, 7168 ),
    Standard_DS3( 4, 14336 ),
    Standard_DS4( 8, 28672 ),
    Standard_DS11( 2, 14336 ),
    Standard_DS12( 4, 28672 ),
    Standard_DS13( 8, 57344 ),
    Standard_DS14( 16, 114688 ),
    Standard_G1( 2, 28672 ),
    Standard_G2( 4, 57344 ),
    Standard_G3( 8, 114688 ),
    Standard_G4( 16, 229376 ),
    Standard_G5( 32, 458752 ),
    Standard_GS1( 2, 28672 ),
    Standard_GS2( 4, 57344 ),
    Standard_GS3( 8, 114688 ),
    Standard_GS4( 16, 229376 ),
    Standard_GS5( 32, 458752 );

    private int core;

    private int memory;

    VirtualMachineSize( int core, int memory )
    {
        this.core = core;
        this.memory = memory;
    }

    public static VirtualMachineSize getSize( int core, int memory )
    {
        VirtualMachineSize size = VirtualMachineSize.ExtraSmall;

        if ( core > 0 ) size = VirtualMachineSize.Small;
        if ( core > 1 ) size = VirtualMachineSize.Medium;
        if ( core > 2 ) size = VirtualMachineSize.Large;
        if ( core > 4 ) size = VirtualMachineSize.ExtraLarge;

        if ( memory > 0 && size.getCore() < VirtualMachineSize.Small.getCore() ) size = VirtualMachineSize.Small;
        if ( memory > 2048 && size.getCore() < VirtualMachineSize.Medium.getCore() ) size = VirtualMachineSize.Medium;
        if ( memory > 4096 && size.getCore() < VirtualMachineSize.Large.getCore() ) size = VirtualMachineSize.Large;
        if ( memory > 8192 && size.getCore() < VirtualMachineSize.ExtraLarge.getCore() )
            size = VirtualMachineSize.ExtraLarge;

        if ( core <= 1 && memory <= 1024 ) size = VirtualMachineSize.ExtraSmall;

        return size;
    }

    public int getCore()
    {
        return core;
    }

    public int getMemory()
    {
        return memory;
    }
}
