package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.RaceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StartCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();


    @Before
    public void loadAthletes(){
        Athlete athlete = new Athlete(1, "Krista","Gurney",23,"Registered","F");
        raceManager.registerAthlete(athlete);
    }

    @Test
    public void testValidExecute(){
        StartCommand startCommand = new StartCommand(clientManager, raceManager,"Started,1,234");
        startCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 1);
        assertEquals(athleteList.get(0).getBibNumber(), 1);
        assertEquals(athleteList.get(0).getTime(), 234);
        assertEquals(athleteList.get(0).getStatus(), "Started");
    }

    @Test
    public void testInvalidMessage() {
        List<String> badMessages = new ArrayList();

        badMessages.add("Finished,junk,234");
        badMessages.add("Finished,1,junk");

        badMessages.add("Finished");
        badMessages.add("Finished,1");

        StartCommand StartCommand = new StartCommand(clientManager, raceManager, "");
        TestHelpers.testValidMessage(badMessages, StartCommand);
    }
    @Test
    public void testNoAthletes(){
        StartCommand startCommand= new StartCommand(clientManager, new RaceManager(),"DidNotStartCommand,1,234");
        startCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 0);
    }

}
