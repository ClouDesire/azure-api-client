package com.cloudesire.azure.client.test;

import com.cloudesire.azure.client.apiobjects.enums.VirtualMachineSize;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VirtualMachineSizeTest
{
    @Test
    public void test()
    {
        assertEquals( VirtualMachineSize.ExtraSmall, VirtualMachineSize.getSize( 1, 512 ));
        assertEquals( VirtualMachineSize.ExtraSmall, VirtualMachineSize.getSize( 1, 1024 ));
        assertEquals( VirtualMachineSize.Small, VirtualMachineSize.getSize( 1, 2048 ));
        assertEquals( VirtualMachineSize.Medium, VirtualMachineSize.getSize( 2, 2048 ));
        assertEquals( VirtualMachineSize.Medium, VirtualMachineSize.getSize( 2, 4096 ));
        assertEquals( VirtualMachineSize.Large, VirtualMachineSize.getSize( 4, 8192 ));
        assertEquals( VirtualMachineSize.ExtraLarge, VirtualMachineSize.getSize( 8, 13000 ));
    }
}
