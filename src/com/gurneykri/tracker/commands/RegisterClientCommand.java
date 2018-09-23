package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class RegisterClientCommand extends BaseCommand {

    public RegisterClientCommand(ClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port){
        super(clientManager, raceManager, message, address, port);
    }
    @Override
    public void execute() {
        clientManager.registerClient(raceManager.getAthleteList(),address, port);
    }
}
