package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.*;

import java.net.InetAddress;

public class SubscribeUnsubscribeAthleteCommand extends BaseCommand{
    private InetAddress address;
    private int port;

    public SubscribeUnsubscribeAthleteCommand(IClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port) {
        super(clientManager, raceManager, message);
        this.address = address;
        this.port = port;
    }

    @Override
    public void execute() {

        String[] parts = message.split(",");
        int bibNumber = Integer.parseInt(parts[1]);
        String command = parts[0];

        Athlete athlete = raceManager.findAthleteByBibNumber(bibNumber);
        IClient client = clientManager.findClient(address, port);

        if(athlete != null && client != null) {
            if (command.equals("Subscribe")) {
                client.subscribeToAthlete(athlete);
            } else if (command.equals("Unsubscribe")) {
                client.unsubscribeToAthlete(athlete);
            }
        }
    }
}
