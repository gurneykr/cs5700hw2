package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.RaceManager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegisterAthleteCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();


    @Test
    public void testValidExecute(){
        RegisterAthleteCommand registerAthleteCommand = new RegisterAthleteCommand(clientManager, raceManager,"Registered,1,234,Krista,Gurney,F,23");
        registerAthleteCommand.execute();
        List<Athlete> athleteList = clientManager.getAthleteList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 1);
        assertEquals(athleteList.get(0).getBibNumber(), 1);
        assertEquals(athleteList.get(0).getStatus(), "Registered");

    }

    @Test
    public void testValidMessage(){
        List<String> badMessages = new ArrayList();

        badMessages.add("Registered,junk,234,Krista,Gurney,F,23");
        badMessages.add("Registered,1,junk,Krista,Gurney,F,23");
        badMessages.add("Registered,1,234,Krista,Gurney,F,junk");

        badMessages.add("Registered");
        badMessages.add("Registered,1");
        badMessages.add("Registered,1,234");
        badMessages.add("Registered,1,234,Krista");
        badMessages.add("Registered,1,234,Krista,Gurney");
        badMessages.add("Registered,1,234,Krista,Gurney,F");

        for(String bm: badMessages) {

            RegisterAthleteCommand registerAthleteCommand = new RegisterAthleteCommand(clientManager, raceManager, bm);
            try {
                registerAthleteCommand.execute();
                //shouldn't got here something's wrong
                assertEquals(true, false);
            } catch (Exception e) {
                assertEquals(true, true);
            }
        }
    }





}
