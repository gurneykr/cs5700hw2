package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.RaceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FinishedCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();


    @Before
    public void loadAthletes(){
        Athlete athlete = new Athlete(1, "Krista","Gurney",23,"Registered","F");
        raceManager.registerAthlete(athlete);
    }

    @Test
    public void testValidExecute(){
        FinishedCommand FinishedCommand = new FinishedCommand(clientManager, raceManager,"Finished,1,234");
        FinishedCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 1);
        assertEquals(athleteList.get(0).getBibNumber(), 1);
        assertEquals(athleteList.get(0).getTime(), 234);
        assertEquals(athleteList.get(0).getFinishTime(), 234);
        assertEquals(athleteList.get(0).getStatus(), "Finished");

    }
    @Test
    public void testInvalidBibNumber(){
        FinishedCommand finishedCommand = new FinishedCommand(clientManager, raceManager,"Finished,junk,234");
        try {
            finishedCommand.execute();
            //shouldn't got here something's wrong
            assertEquals(true, false);
        }catch (Exception e){
            assertEquals(true, true);
        }
    }

    @Test
    public void testInvalidTime(){
        FinishedCommand finishedCommand = new FinishedCommand(clientManager, raceManager,"Finished,1,junk");
        try {
            finishedCommand.execute();
            //shouldn't got here something's wrong
            assertEquals(true, false);
        }catch (Exception e){
            assertEquals(true, true);
        }
    }

    @Test
    public void testNotAthletes(){
        FinishedCommand finishedCommand = new FinishedCommand(clientManager, new RaceManager(),"Finished,1,234");
        finishedCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 0);
    }
}
