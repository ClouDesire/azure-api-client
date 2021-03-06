package com.cloudesire.azure.client.apiobjects.enums;

public enum InstanceState
{
    ROLESTATEUNKNOWN, CREATINGVM, STARTINGVM, CREATINGROLE, STARTINGROLE,
    READYROLE, BUSYROLE, STOPPINGROLE, STOPPINGVM, DELETINGVM, STOPPEDVM,
    RESTARTINGROLE, CYCLINGROLE, FAILEDSTARTINGROLE, FAILEDSTARTINGVM,
    UNRESPONSIVEROLE, STOPPEDDEALLOCATED
}
