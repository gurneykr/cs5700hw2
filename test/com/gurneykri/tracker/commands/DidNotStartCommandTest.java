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
    public void testNoAthletes(){
        DidNotStartCommand didNotStartCommand = new DidNotStartCommand(clientManager, new RaceManager(),"DidNotStartCommand,1,234");
        didNotStartCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 0);
    }

    @Test
    public void testInvalidMessage() {
        List<String> badMessages = new ArrayList();

        badMessages.add("DidNotStart,junk,234");
        badMessages.add("DidNotStart,1,junk");

        badMessages.add("DidNotStart");
        badMessages.add("DidNotStart,1");

        DidNotStartCommand didNotStartCommand= new DidNotStartCommand(clientManager, raceManager, "");
        TestHelpers.testValidMessage(badMessages, didNotStartCommand);
    }
}
