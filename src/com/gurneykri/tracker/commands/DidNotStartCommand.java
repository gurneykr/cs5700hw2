package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.IClientManager;
import com.gurneykri.tracker.RaceManager;

import java.util.ArrayList;
import java.util.List;

public class DidNotStartCommand extends BaseCommand {
    public DidNotStartCommand(IClientManager clientManager, RaceManager raceManager, String message) {
        super(clientManager, raceManager, message);

    }

    @Override
    public void execute() {
        List<Athlete> athleteList = new ArrayList();
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
        clientManager.broadcastAthletesStatus(athleteList);
    }
}
