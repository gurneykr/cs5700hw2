package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class CreateRaceCommand extends BaseCommand implements ICommand {
    private String name;
    private int length;

    public CreateRaceCommand(ClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port){
        super(clientManager, raceManager);

        //the message coming in will look like this "RaceManager,name,distance"
        String[] parts = message.split(",");
        this.name = parts[1];
        this.length = Integer.parseInt(parts[2]);

    }

    @Override
    public void execute() {
        //tell all the clients about the raceManager
        clientManager.broadcastRaceInfo(name,length);
    }
}
