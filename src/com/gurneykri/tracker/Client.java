package com.gurneykri.tracker;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private InetAddress address;
    private int port;

    private List<Athlete> subscribedAthletes = new ArrayList();

    public Client(InetAddress address, int port){
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
