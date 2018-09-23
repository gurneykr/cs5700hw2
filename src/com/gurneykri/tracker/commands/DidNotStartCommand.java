package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class DidNotStartCommand extends BaseCommand {
    private List<Athlete> athleteList = new ArrayList();

    public DidNotStartCommand(ClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port) {
        super(clientManager, raceManager);

        //DidNotStart,<bib number>,<time>
        String[] parts = message.split(",");
        int bibNumber = Integer.parseInt(parts[1]);
        int time = Integer.parseInt(parts[2]);

        Athlete athlete = raceManager.findAthleteByBibNumber(bibNumber);
        if(athlete != null) {
            athlete.setTime(time);
            athlete.setFinishTime(time);
            athlete.setStatus("DidNotStart");
            athleteList.add(athlete);
        }
    }

    @Override
    public void execute() {
        clientManager.broadcastAthletesStatus(athleteList);
    }
}
