package com.cloudesire.azure.client.apiobjects.enums;

public enum VirtualMachineSize
{
	ExtraSmall( 0, 768 ),
	Small( 1, 1792 ),
	Medium( 2, 3584 ),
	Large( 4, 7168 ),
	ExtraLarge( 8, 14336 ),
	Standard_A0( 1, 768 ),
	Standard_A1( 1, 1792 ),
	Standard_A2( 2, 3584 ),
	Standard_A3( 4, 7168 ),
	Standard_A4( 8, 14336 ),
	Standard_D1( 1, 3584 ),
	Standard_D2( 2, 7168 ),
	Standard_D3( 4, 14336 ),
	Standard_D4( 8, 28672 ),
	Basic_A0( 1, 768 ),
	Basic_A1( 1, 1792 ),
	Basic_A2( 2, 3584 ),
	Basic_A3( 4, 7168 ),
	Basic_A4( 8, 14336 ),
	Standard_D1_v2( 1, 3584 ),
	Standard_D2_v2( 2, 7168 ),
	Standard_D3_v2( 4, 14336 ),
	Standard_D4_v2( 8, 28672 );

	private int core;

	private int memory;

	VirtualMachineSize( int core, int memory )
	{
		this.core = core;
		this.memory = memory;
	}


	public int getCore()
	{
		return core;
	}

	public int getMemory()
	{
		return memory;
	}

	public static VirtualMachineSize getSize( int core, int memory )
	{
		VirtualMachineSize size = VirtualMachineSize.ExtraSmall;

		if ( core > 0 ) size = VirtualMachineSize.Small;
		if ( core > 1 ) size = VirtualMachineSize.Medium;
		if ( core > 2 ) size = VirtualMachineSize.Large;
		if ( core > 4 ) size = VirtualMachineSize.ExtraLarge;

		if ( memory > 0 && size.getCore() < VirtualMachineSize.Small.getCore() )
			size = VirtualMachineSize.Small;
		if ( memory > 2048 && size.getCore() < VirtualMachineSize.Medium.getCore() )
			size = VirtualMachineSize.Medium;
		if ( memory > 4096 && size.getCore() < VirtualMachineSize.Large.getCore() )
			size = VirtualMachineSize.Large;
		if ( memory > 8192 && size.getCore() < VirtualMachineSize.ExtraLarge.getCore() )
			size = VirtualMachineSize.ExtraLarge;

		if ( core <= 1 && memory <= 1024 ) size = VirtualMachineSize.ExtraSmall;

		return size;
	}
}
