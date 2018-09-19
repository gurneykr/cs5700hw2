package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class RegisterClientCommand extends BaseCommand {
    private InetAddress address;
    private int port;

    public RegisterClientCommand(ClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port){
        super(clientManager, raceManager);
        this.address = address;
        this.port = port;
    }
    @Override
    public void execute() {
        //tell the client manager to register the client
        clientManager.registerClient(address, port);
    }
}
