package com.cloudesire.azure.client.apiobjects.enums;

/**
 * @author Manuel Mazzuola <manuel.mazzuola@liberologico.com>
 */
public enum VirtualMachineSize
{
	ExtraSmall(0), Small(1), Medium(2), Large(3), ExtraLarge(4);

	private int size;

	VirtualMachineSize ( int size )
	{
		this.size = size;
	}

	public int getSize ()
	{
		return size;
	}
}
