package com.gurneykri.tracker;

import java.net.InetAddress;
import java.util.List;

public class DummyClientManager implements IClientManager {
    private List<Athlete> athleteStatusList;
    private List<Athlete> athleteList;
    private InetAddress address;
    private int port;

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

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
        this.athleteList = athleteList;
        this.address = address;
        this.port = port;

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
