import java.net.SocketException;

public class TrackerServer {
    private Communicator communicator;


    public static void main(String[] args){
        TrackerServer server = new TrackerServer();
        try{
            server.start();

        }catch(SocketException e){
            System.err.println("Failed to start communicator");
        }

    }

    public TrackerServer(){
        System.out.println("Hello Tracker Server");
    }
    public void start()throws SocketException{
        System.out.println("Starting Tracker Server");

        DummyMessageProcessor messageProcessor = new DummyMessageProcessor("Dummy message processor");

        communicator = new Communicator(12000);
        communicator.setProcessor(messageProcessor);
        communicator.start();
        try {
            while(true) {
                if (messageProcessor.address != null) {
                    System.out.println("Sending message");
                    communicator.send("Athlete,00000,Krista,Gurney,female,23", messageProcessor.address, messageProcessor.port);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
