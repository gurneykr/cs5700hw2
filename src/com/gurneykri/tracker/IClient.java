package com.gurneykri.tracker;

import java.net.InetAddress;

import java.util.List;

public interface IClient {

    public List<Athlete> getSubscribedAthletes();

    public void subscribeToAthlete(Athlete athlete);


    public void unsubscribeToAthlete(Athlete athlete);

    public InetAddress getAddress();


    public int getPort();

}
