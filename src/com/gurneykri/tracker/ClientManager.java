package com.gurneykri.tracker;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
/*
The purpose of this object is to keep track of all the clients and to be able to interact with the communicator
to talk to the clients.
 */

public class ClientManager implements IClientManager{
    private List<Client> clientList = new ArrayList();
    private Communicator communicator;
    private String raceName;
    private int raceLength = 0;

    public ClientManager(Communicator communicator){
        this.communicator = communicator;
    }

    public void broadcastRaceInfo(String raceName, int raceLength){
        this.raceName = raceName;
        this.raceLength = raceLength;

        //tell each client about the Race
        for(Client c: clientList){
            try {
                communicator.send("Race,"+ raceName +"," + raceLength, c.getAddress(), c.getPort());
            }catch (Exception e){
                System.err.println("Error while broadcasting the raceManager information");
                e.printStackTrace();
            }
        }
    }

    public void broadcastAthletes(List<Athlete> athleteList){
        //tell each client about the athleteList
        for(Client c: clientList){
            for(Athlete a: athleteList){
                try{
                    communicator.send(a.getAthleteCommandString(), c.getAddress(), c.getPort());
                }catch (Exception e){
                    System.err.println("Error while broadcasting the athlete information");
                    e.printStackTrace();
                }
            }
        }
    }

    public void registerClient(List<Athlete> athleteList,InetAddress address, int port){
        Client client = new Client(address, port);
        clientList.add(client);

        if(raceName != null && raceLength > 0) {
            broadcastRaceInfo(raceName, raceLength);
        }
        broadcastAthletes(athleteList);
    }

    public void broadcastAthletesStatus(List<Athlete> athleteList){
        //go through all the clients
        for(Client c: clientList){
            for(Athlete a: athleteList) {
                for(Athlete sa: c.getSubscribedAthletes()) {
                    if(sa.getBibNumber()==a.getBibNumber()){
                        try{
                            communicator.send(a.getAthleteStatusString(), c.getAddress(), c.getPort());
                        }catch (Exception e){
                            System.err.println("Error while broadcasting the athlete status");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public Client findClient(InetAddress address, int port){
        Client client = null;
        for(Client c: clientList){
            if(c.getAddress().getHostAddress().equals(address.getHostAddress()) && c.getPort() == port){
                client = c;
                break;
            }
         }
        return client;
    }
}
