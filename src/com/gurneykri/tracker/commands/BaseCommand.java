package com.gurneykri.tracker.commands;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

/*
The purpose of this base command is to provide access to the RaceManager and the Communicator
 */
public abstract class BaseCommand implements ICommand{
    protected ClientManager clientManager;
    protected RaceManager raceManager;

    public BaseCommand(ClientManager clientManager, RaceManager raceManager){
        this.clientManager = clientManager;
        this.raceManager = raceManager;
    }

}
