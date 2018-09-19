package com.gurneykri.tracker;

import com.gurneykri.tracker.Communicator;

import java.net.InetAddress;

public class DummyMessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount;
    public InetAddress address;
    public int port;
    private Communicator communicator;

    public DummyMessageProcessor(String name, Communicator communicator) {
        this.communicator = communicator;
        this.name = name;
    }
    @Override
    public void process(String message, InetAddress address, int port) {


        if (message==null) {
            System.out.println("Null string");
            return;
        }

        if (address==null) {
            System.out.println("Null address");
            return;
        }

        if(message.startsWith("Hello")){
            System.out.println("Received hello message from client");
            this.address = address;
            this.port = port;

            try {
                System.out.println("About to send to client");
                communicator.send("com.gurneykri.tracker.Race,TestRace,100",address,port);
                communicator.send("Athlete,1234,Krista,Gurney,female,23", address, port);
                System.out.println("Sent the message to client");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
        receiveCount++;
    }

    public int ReceiveCount() { return receiveCount; }
}
