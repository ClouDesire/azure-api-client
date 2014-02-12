package com.cloudesire.azure.client.apiobjects.enums;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public enum VirtualMachineSize
{
	ExtraSmall(0, 768), Small(1, 1792), Medium(2, 3584), Large(4, 7168), ExtraLarge(8, 14336);

	private int core;

	private int memory;

	VirtualMachineSize ( int core, int memory )
	{
		this.core = core;
		this.memory = memory;
	}


	public int getCore ()
	{
		return core;
	}

	public int getMemory ()
	{
		return memory;
	}

	public static VirtualMachineSize getSize (int core, int memory)
	{
		VirtualMachineSize size = VirtualMachineSize.ExtraSmall;

		if (core > 0) size = VirtualMachineSize.Small;
		if (core > 1) size = VirtualMachineSize.Medium;
		if (core > 2) size = VirtualMachineSize.Large;
		if (core > 4) size = VirtualMachineSize.ExtraLarge;

		if (memory > 0 && size.getCore() < VirtualMachineSize.Small.getCore())
			size = VirtualMachineSize.Small;
		if (memory > 1792 && size.getCore() < VirtualMachineSize.Medium.getCore())
			size = VirtualMachineSize.Medium;
		if (memory > 3584 && size.getCore() < VirtualMachineSize.Large.getCore())
			size = VirtualMachineSize.Large;
		if (memory > 7168 && size.getCore() < VirtualMachineSize.ExtraLarge.getCore())
			size = VirtualMachineSize.ExtraLarge;

		if (core < 1 && memory < 1792) size = VirtualMachineSize.ExtraSmall;

		return size;
	}
}
