package com.gurneykri.tracker;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
/*
The purpose of this object is to keep track of all the clients and to be able to interact with the communicator
to talk to the clients.
 */

public class ClientManager {
    private List<Client> clientList = new ArrayList();
    private Communicator communicator;

    public ClientManager(Communicator communicator){
        this.communicator = communicator;
    }

    public void broadcastRaceInfo(String raceName, int raceLength){
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


    public void registerClient(InetAddress address, int port){
        Client client = new Client(address, port);
        clientList.add(client);
    }

}
