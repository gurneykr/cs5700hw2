package com.gurneykri.tracker;

import com.gurneykri.tracker.commands.CommandMessageProcessor;

import java.net.SocketException;

public class TrackerServer {
    private Communicator communicator;


    public static void main(String[] args){
        TrackerServer server = new TrackerServer();
        try{
            server.start();
        }catch(SocketException e){
            System.err.println("Failed to start communicator");
        }

    }

    public void start()throws SocketException{
        System.out.println("Starting Tracker Server");

        communicator = new Communicator(12000);

        ClientManager clientManager = new ClientManager(communicator);
        RaceManager raceManager = new RaceManager();

        CommandMessageProcessor cmp = new CommandMessageProcessor(clientManager, raceManager);

        System.out.println("Clients can communicate by connecting to port: 12000");
        communicator.setProcessor(cmp);
        communicator.start();
    }
}
