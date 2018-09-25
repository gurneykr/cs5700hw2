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

        if(message.startsWith("Race")){
            command = new CreateRaceCommand(clientManager, raceManager, message);
        }else if(message.startsWith("Hello")){
            command = new RegisterClientCommand(clientManager, raceManager, message, address, port);
        }else if(message.startsWith("Registered")){
            command = new RegisterAthleteCommand(clientManager, raceManager, message);
        }else if(message.startsWith("OnCourse")){
            command = new AthleteUpdateCommand(clientManager, raceManager, message);
        }else if(message.startsWith("Subscribe") || message.startsWith("Unsubscribe") ){
            command = new SubscribeUnsubscribeAthleteCommand(clientManager, raceManager, message, address, port);
        }else if(message.startsWith("Started")){
            command = new StartCommand(clientManager, raceManager, message);
        }else if(message.startsWith("DidNotStart")){
            command = new DidNotStartCommand(clientManager, raceManager, message);
        }else if(message.startsWith("DidNotFinishCommand")){
            command = new DidNotFinishCommand(clientManager, raceManager, message);
        }else if(message.startsWith("Finished")){
            command = new FinishedCommand(clientManager, raceManager, message);
        }
        if(command != null) {
            command.execute();
        }
    }
}
