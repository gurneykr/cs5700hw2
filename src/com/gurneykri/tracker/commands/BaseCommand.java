package com.gurneykri.tracker.commands;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.Race;

/*
The purpose of this base command is to provide access to the Race and the Communicator
 */
public abstract class BaseCommand implements ICommand{
    protected ClientManager clientManager;
    protected Race race;

    public BaseCommand(ClientManager clientManager, Race race){
        this.clientManager = clientManager;
        this.race = race;
    }

}
