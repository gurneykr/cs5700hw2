package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class CreateRaceCommand extends BaseCommand implements ICommand {


    public CreateRaceCommand(ClientManager clientManager, RaceManager raceManager, String message){
        super(clientManager, raceManager,message);
    }

    @Override
    public void execute() {

        String[] parts = message.split(",");
        String name = parts[1];
        int length = Integer.parseInt(parts[2]);

        clientManager.broadcastRaceInfo(name,length);
    }
}
