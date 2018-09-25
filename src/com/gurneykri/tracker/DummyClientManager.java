package com.gurneykri.tracker;

import java.net.InetAddress;
import java.util.List;

public class DummyClientManager implements IClientManager {
    private List<Athlete> athleteStatusList;
    private List<Athlete> athleteList;

    public List<Athlete> getAthleteList() {
        return athleteList;
    }

    public List<Athlete> getAthleteStatusList() {
        return athleteStatusList;
    }

    @Override
    public void broadcastRaceInfo(String raceName, int raceLength) {

    }

    @Override
    public void broadcastAthletes(List<Athlete> athleteList) {
        this.athleteList = athleteList;
    }

    @Override
    public void registerClient(List<Athlete> athleteList, InetAddress address, int port) {

    }

    @Override
    public void broadcastAthletesStatus(List<Athlete> athleteList) {
        athleteStatusList = athleteList;
    }

    @Override
    public Client findClient(InetAddress address, int port) {
        return null;
    }
}
