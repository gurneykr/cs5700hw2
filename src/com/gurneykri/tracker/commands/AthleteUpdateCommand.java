package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class AthleteUpdateCommand extends BaseCommand {
    private List<Athlete> athleteList = new ArrayList();

    public AthleteUpdateCommand(ClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port){
        super(clientManager, raceManager);

        //message coming in is OnCourse,<bib number>,<time>,<distance covered in
        String[] parts = message.split(",");
        int bibNumber = Integer.parseInt(parts[1]);
        int time = Integer.parseInt(parts[2]);
        double distance = Double.parseDouble(parts[3]);

        Athlete athlete = raceManager.findAthleteByBibNumber(bibNumber);
        if(athlete != null) {
            athlete.setTime(time);
            athlete.setDistance(distance);
            athlete.setStatus("OnCourse");
            athleteList.add(athlete);
        }
    }

    @Override
    public void execute() {

        clientManager.broadcastAthletesStatus(athleteList);
    }
}
