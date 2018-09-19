package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.IMessageProcessor;
import com.gurneykri.tracker.Race;

import java.net.InetAddress;

public class CommandMessageProcessor implements IMessageProcessor {
    private ClientManager clientManager;
    private Race race;

    public CommandMessageProcessor(ClientManager clientManager, Race race){
        this.clientManager = clientManager;
        this.race = race;
    }

    @Override
    public void process(String message, InetAddress address, int port) {
        ICommand command = null;

        //decide which command to construct
        if(message.startsWith("Race")){
            command = new CreateRaceCommand(clientManager, race, message, address, port);
        }else if(message.startsWith("Hello")){
            command = new RegisterClientCommand(clientManager, race, message, address, port);
        }

        if(command != null) {
            command.execute();
        }
    }
}
