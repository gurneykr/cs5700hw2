package com.gurneykri.tracker;

import java.util.ArrayList;
import java.util.List;

/*
The purpose of this class is to keep track of the raceManager name, length and all of the athletes in the raceManager
 */
public class RaceManager {
    private String name;
    private int length;


    private List<Athlete> athleteList = new ArrayList();

    public void registerAthlete(Athlete athlete){
        athleteList.add(athlete);
    }

    public Athlete findAthleteByBibNumber(int bibNumber){
        Athlete athlete = null;
        for(Athlete a : athleteList){
            if(a.getBibNumber()==bibNumber){
                athlete = a;
                break;
            }
        }
        return athlete;
    }


}
