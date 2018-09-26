package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.RaceManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateRaceCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();

    //Race,<race name>,<course length in meters>


    @Test
    public void testValidExecute(){
        CreateRaceCommand createRaceCommand = new CreateRaceCommand(clientManager, raceManager,"Race,BensonLoop,234567");

        createRaceCommand.execute();
        assertEquals(clientManager.getRaceName(), "BensonLoop");
        assertEquals(clientManager.getRaceLength(), 234567);
    }

    @Test
    public void testInvalidMessage() {
        List<String> badMessages = new ArrayList();
        
        badMessages.add("Race,BensonLoop,junk");

        badMessages.add("Race");
        badMessages.add("Race,BensonLoop");

        CreateRaceCommand createRaceCommand= new CreateRaceCommand(clientManager, raceManager, "");
        TestHelpers.testValidMessage(badMessages, createRaceCommand);
    }
}
