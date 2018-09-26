package com.gurneykri.tracker;

import java.net.InetAddress;
import java.util.List;

public class DummyClient implements IClient {
    private Athlete subscribedAthlete;
    private Athlete unSubscribedAthlete;


    public DummyClient(InetAddress address, int port) {

    }

    public Athlete getSubscribedAthlete() {
        return subscribedAthlete;
    }

    public Athlete getUnSubscribedAthlete() {
        return unSubscribedAthlete;
    }

    @Override
    public List<Athlete> getSubscribedAthletes() {
        return null;
    }

    @Override
    public void subscribeToAthlete(Athlete athlete) {
        this.subscribedAthlete = athlete;
    }

    @Override
    public void unsubscribeToAthlete(Athlete athlete) {
        this.unSubscribedAthlete = athlete;
    }

    @Override
    public InetAddress getAddress() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }
}
