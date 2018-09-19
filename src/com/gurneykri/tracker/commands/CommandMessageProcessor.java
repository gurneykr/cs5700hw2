package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.IMessageProcessor;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class CommandMessageProcessor implements IMessageProcessor {
    private ClientManager clientManager;
    private RaceManager raceManager;

    public CommandMessageProcessor(ClientManager clientManager, RaceManager raceManager){
        this.clientManager = clientManager;
        this.raceManager = raceManager;
    }

    @Override
    public void process(String message, InetAddress address, int port) {
        ICommand command = null;

        //decide which command to construct
        if(message.startsWith("Race")){
            command = new CreateRaceCommand(clientManager, raceManager, message, address, port);
        }else if(message.startsWith("Hello")){
            command = new RegisterClientCommand(clientManager, raceManager, message, address, port);
        }else if(message.startsWith("Registered")){
            command = new RegisterAthleteCommand(clientManager, raceManager, message, address, port);
        }

        if(command != null) {
            command.execute();
        }
    }
}
