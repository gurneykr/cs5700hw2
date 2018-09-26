package com.gurneykri.tracker.commands;

import com.gurneykri.tracker.Athlete;
import com.gurneykri.tracker.DummyClientManager;
import com.gurneykri.tracker.IClientManager;
import com.gurneykri.tracker.RaceManager;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class SubscribeUnsubscribeTest {
    private RaceManager raceManager = new RaceManager();
    private DummyClientManager clientManager = new DummyClientManager();


    @Before
    public void loadAthletes(){
        Athlete athlete = new Athlete(1, "Krista","Gurney",23,"Registered","F");

        raceManager.registerAthlete(athlete);
    }
    @Test
    public void testValidExecute(){
        InetAddress address = null;
        int port = 12000;

        SubscribeUnsubscribeAthleteCommand subscribeUnsubscribeAthleteCommand = new SubscribeUnsubscribeAthleteCommand(clientManager, raceManager,
                "Subscribe,1", address,  port);
        subscribeUnsubscribeAthleteCommand.execute();
        assertEquals(clientManager.getClient().getSubscribedAthlete().getBibNumber(), 1);


        SubscribeUnsubscribeAthleteCommand subscribeUnsubscribeAthleteCommand2 = new SubscribeUnsubscribeAthleteCommand(clientManager, raceManager,
                "Unsubscribe,1", address,  port);
        subscribeUnsubscribeAthleteCommand2.execute();
        assertEquals(clientManager.getClient().getUnSubscribedAthlete().getBibNumber(), 1);
    }
}
