package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.RaceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DidNotStartCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();


    @Before
    public void loadAthletes(){
        Athlete athlete = new Athlete(1, "Krista","Gurney",23,"Registered","F");
        raceManager.registerAthlete(athlete);
    }

    @Test
    public void testValidExecute(){
        DidNotStartCommand didNotStartCommand = new DidNotStartCommand(clientManager, raceManager,"DidNotStartCommand,1,234");
        didNotStartCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 1);
        assertEquals(athleteList.get(0).getBibNumber(), 1);
        assertEquals(athleteList.get(0).getTime(), 234);
        assertEquals(athleteList.get(0).getFinishTime(), 234);
        assertEquals(athleteList.get(0).getStatus(), "DidNotStart");

    }
    @Test
    public void testInvalidBibNumber(){
        DidNotStartCommand didNotStartCommand = new DidNotStartCommand(clientManager, raceManager,"DidNotStartCommand,junk,234");
        try {
            didNotStartCommand.execute();
            //shouldn't got here something's wrong
            assertEquals(true, false);
        }catch (Exception e){
            assertEquals(true, true);
        }
    }

    @Test
    public void testInvalidTime(){
        DidNotStartCommand didNotStartCommand = new DidNotStartCommand(clientManager, raceManager,"DidNotStartCommand,1,junk");
        try {
            didNotStartCommand.execute();
            //shouldn't got here something's wrong
            assertEquals(true, false);
        }catch (Exception e){
            assertEquals(true, true);
        }
    }

    @Test
    public void testNotAthletes(){
        DidNotStartCommand didNotStartCommand = new DidNotStartCommand(clientManager, new RaceManager(),"DidNotStartCommand,1,234");
        didNotStartCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 0);
    }
}
