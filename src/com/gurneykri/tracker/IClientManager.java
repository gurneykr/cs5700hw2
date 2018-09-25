package com.gurneykri.tracker;

import java.net.InetAddress;
import java.util.List;

public interface IClientManager{
    public void broadcastRaceInfo(String raceName, int raceLength);

    public void broadcastAthletes(List<Athlete> athleteList);

    public void registerClient(List<Athlete> athleteList, InetAddress address, int port);

    public void broadcastAthletesStatus(List<Athlete> athleteList);

    public Client findClient(InetAddress address, int port);
}
