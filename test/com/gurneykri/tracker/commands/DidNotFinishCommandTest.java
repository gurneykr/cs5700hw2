package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DidNotFinishCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();


    @Before
    public void loadAthletes(){
        Athlete athlete = new Athlete(1, "Krista","Gurney",23,"Registered","F");
        raceManager.registerAthlete(athlete);
    }

    @Test
    public void testValidExecute(){
        DidNotFinishCommand didNotFinishCommand = new DidNotFinishCommand(clientManager, raceManager,"DidNotFinishCommand,1,234");
        didNotFinishCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 1);
        assertEquals(athleteList.get(0).getBibNumber(), 1);
        assertEquals(athleteList.get(0).getTime(), 234);
        assertEquals(athleteList.get(0).getFinishTime(), 234);
        assertEquals(athleteList.get(0).getStatus(), "DidNotFinish");

    }
    @Test
    public void testInvalidBibNumber(){
        DidNotFinishCommand didNotFinishCommand = new DidNotFinishCommand(clientManager, raceManager,"DidNotFinishCommand,junk,234");
       try {
           didNotFinishCommand.execute();
           //shouldn't got here something's wrong
           assertEquals(true, false);
       }catch (Exception e){
            assertEquals(true, true);
        }
    }

    @Test
    public void testInvalidTime(){
        DidNotFinishCommand didNotFinishCommand = new DidNotFinishCommand(clientManager, raceManager,"DidNotFinishCommand,1,junk");
        try {
            didNotFinishCommand.execute();
            //shouldn't got here something's wrong
            assertEquals(true, false);
        }catch (Exception e){
            assertEquals(true, true);
        }
    }

    @Test
    public void testNotAthletes(){
        DidNotFinishCommand didNotFinishCommand = new DidNotFinishCommand(clientManager, new RaceManager(),"DidNotFinishCommand,1,234");
        didNotFinishCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 0);
    }
}
