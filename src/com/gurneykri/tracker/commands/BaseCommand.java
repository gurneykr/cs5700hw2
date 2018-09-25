package com.gurneykri.tracker.commands;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.IClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

/*
The purpose of this base command is to provide access to the RaceManager and the Communicator
 */
public abstract class BaseCommand implements ICommand{
    protected IClientManager clientManager;
    protected RaceManager raceManager;
    protected String message;
    protected InetAddress address;
    protected int port;

    public BaseCommand(IClientManager clientManager, RaceManager raceManager, String message){
        this.clientManager = clientManager;
        this.raceManager = raceManager;
        this.message = message;
    }

    public BaseCommand(IClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port){
        this.clientManager = clientManager;
        this.raceManager = raceManager;
        this.message = message;
        this.address = address;
        this.port = port;
    }



}
