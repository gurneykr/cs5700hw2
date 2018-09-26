package com.gurneykri.tracker.commands;
import com.gurneykri.tracker.IClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class RegisterClientCommand extends BaseCommand {

    public RegisterClientCommand(IClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port){
        super(clientManager, raceManager, message, address, port);
    }
    @Override
    public void execute() {
        clientManager.registerClient(raceManager.getAthleteList(),address, port);
    }
}
