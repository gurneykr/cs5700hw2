package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.ClientManager;
import com.gurneykri.tracker.RaceManager;

import java.util.ArrayList;
import java.util.List;

public class RegisterAthleteCommand extends BaseCommand{

    public RegisterAthleteCommand(ClientManager clientManager, RaceManager raceManager, String message){
        super(clientManager, raceManager, message);
    }

    @Override
    public void execute() {
        Athlete athlete = new Athlete();

        String[] parts = message.split(",");
        athlete.setBibNumber(Integer.parseInt(parts[1]));
        athlete.setTime(Integer.parseInt(parts[2]));
        athlete.setFirstName(parts[3]);
        athlete.setLastName(parts[4]);
        athlete.setGender(parts[5]);
        athlete.setAge(Integer.parseInt(parts[6]));
        athlete.setStatus("Registered");

        List<Athlete> athleteList = new ArrayList();
        athleteList.add(athlete);

        raceManager.registerAthlete(athlete);
        clientManager.broadcastAthletes(athleteList);
    }
}
