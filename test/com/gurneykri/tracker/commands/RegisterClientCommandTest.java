package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.RaceManager;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RegisterClientCommandTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();

    @Before
    public void loadAthletes(){
        Athlete athlete = new Athlete(1, "Krista","Gurney",23,"Registered","F");
        raceManager.registerAthlete(athlete);
    }

    @Test
    public void testValidExecute() {
        InetAddress address = null;
        int port = 12000;
        RegisterClientCommand registerClientCommand = new RegisterClientCommand( clientManager, raceManager, "",  address,  port);

        registerClientCommand.execute();

        List<Athlete> athleteList = clientManager.getAthleteList();

        assertNotNull(athleteList);
        assertEquals(athleteList.size(), 1);
        assertNull(clientManager.getAddress());
        assertEquals(clientManager.getPort(),12000);
    }
}
