import java.net.InetAddress;

public class DummyMessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount;
    public InetAddress address;
    public int port;

    public DummyMessageProcessor(String name) {
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
            //String[] parts = message.split(",");
            this.address = address;
            this.port = port;

        }

        System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
        receiveCount++;
    }

    public int ReceiveCount() { return receiveCount; }
}
