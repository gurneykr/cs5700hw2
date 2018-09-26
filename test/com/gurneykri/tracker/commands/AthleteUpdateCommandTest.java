package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.RaceManager;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AthleteUpdateCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager;

    @Before
    public void loadAthletes(){
        Athlete athlete = new Athlete(1, "Krista","Gurney",23,"Registered","F");
        raceManager.registerAthlete(athlete);
    }

    @Test
    public void testValidExecute(){
        clientManager = new DummyClientManager();
        AthleteUpdateCommand athleteUpdateCommand = new AthleteUpdateCommand(clientManager, raceManager,"OnCourse,1,234,567.8");
        athleteUpdateCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 1);
        assertEquals(athleteList.get(0).getBibNumber(), 1);
        assertEquals(athleteList.get(0).getTime(), 234);
        assertEquals(athleteList.get(0).getDistance(), 567.8, 0.00000001);
        assertEquals(athleteList.get(0).getStatus(), "OnCourse");

    }

    @Test
    public void testNoAthletes(){
        clientManager = new DummyClientManager();
        AthleteUpdateCommand athleteUpdateCommand = new AthleteUpdateCommand(clientManager, new RaceManager(),"OnCourse,1,234,567.8");
        athleteUpdateCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteStatusList();

        assertNull(athleteList);
    }
    @Test
    public void testInvalidMessage() {
        clientManager = new DummyClientManager();
        List<String> badMessages = new ArrayList();

        badMessages.add("OnCourse,junk,234,567.8");
        badMessages.add("OnCourse,1,junk,567.8");
        badMessages.add("OnCourse,1,234,junk");


        badMessages.add("OnCourse");
        badMessages.add("OnCourse,1");
        badMessages.add("OnCourse,1,234");

        AthleteUpdateCommand AthleteUpdateCommand= new AthleteUpdateCommand(clientManager, raceManager, "");
        TestHelpers.testValidMessage(badMessages, AthleteUpdateCommand);
    }
    
}
