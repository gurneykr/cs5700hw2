package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.net.InetAddress;

public class AthleteUpdateCommand extends BaseCommand {
    //private String name;

    public AthleteUpdateCommand(ClientManager clientManager, RaceManager raceManager, String message, InetAddress address, int port){
        super(clientManager, raceManager);

        //message coming in is OnCourse,<bib number>,<time>,<distance covered in
        String[] parts = message.split(",");
        int bibNumber = Integer.parseInt(parts[1]);
        int time = Integer.parseInt(parts[2]);
        int distance = Integer.parseInt(parts[3]);

        Athlete athlete = raceManager.findAthleteByBibNumber(bibNumber);
        athlete.setTime(time);
        athlete.setDistance(distance);



    }

    @Override
    public void execute() {

    }
}
