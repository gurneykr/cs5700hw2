package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.Client;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class SubscribeUnsubscribeAthleteCommand extends BaseCommand{
    private InetAddress address;
    private int port;
    private Athlete athlete;
    private String command;
    private Client client;


    public SubscribeUnsubscribeAthleteCommand(ClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port) {
        super(clientManager, raceManager);
        this.address = address;
        this.port = port;

        String[] parts = message.split(",");
        int bibNumber = Integer.parseInt(parts[1]);
        command = parts[0];

        //Subscribe,<bib number>
        athlete = raceManager.findAthleteByBibNumber(bibNumber);
        client = clientManager.findClient(address, port);


    }

    @Override
    public void execute() {
        if(athlete != null && client != null) {
            if (command.equals("Subscribe")) {
                client.subscribeToAthlete(athlete);
            } else if (command.equals("Unsubscribe")) {
                client.unsubscribeToAthlete(athlete);
            }
        }
    }
}
