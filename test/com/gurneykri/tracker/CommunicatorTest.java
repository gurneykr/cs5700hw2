package com.gurneykri.tracker.commands;
import org.junit.Test;

import java.net.DatagramPacket;
import java.net.InetAddress;

import static java.nio.charset.StandardCharsets.UTF_16BE;
import static org.junit.Assert.*;

public class CommunicatorTest {

    @Test
    public void testConstruction() throws Exception {
        com.gurneykri.tracker.Communicator communicator = new com.gurneykri.tracker.Communicator();
        assertTrue(communicator.getLocalPort()>0);

        com.gurneykri.tracker.DummyMessageProcessor processor = new com.gurneykri.tracker.DummyMessageProcessor("test", communicator);
        communicator.setProcessor(processor);
        assertSame(processor, communicator.getProcessor());

        communicator.close();
        assertEquals(0, communicator.getLocalPort());
    }

    @Test
    public void testSendAndGetMessage() throws Exception {
        com.gurneykri.tracker.Communicator comm1 = new com.gurneykri.tracker.Communicator(12001);
        com.gurneykri.tracker.Communicator comm2 = new com.gurneykri.tracker.Communicator(12002);


        comm1.send("Hello", InetAddress.getLocalHost(), comm2.getLocalPort());
        DatagramPacket packet = comm2.getMessage();
        assertNotNull(packet);

        String message = new String( packet.getData(), 0, packet.getLength(), UTF_16BE);
        assertEquals("Hello", message);
        assertEquals(comm1.getLocalPort(), packet.getPort());

        try
        {
            comm1.send(null, InetAddress.getLocalHost(), comm2.getLocalPort());
            fail("Expected exception not thrown");
        }
        catch (Exception e)
        {
            assertEquals("Cannot send an empty message", e.getMessage());
        }

        try
        {
            comm1.send("Hello", null, comm2.getLocalPort());
            fail("Expected exception not thrown");
        }
        catch (Exception e)
        {
            assertEquals("Invalid target address", e.getMessage());
        }

        try
        {
            comm1.send("Hello", InetAddress.getLocalHost(), -1);
            fail("Expected exception not thrown");
        }
        catch (Exception e)
        {
            assertEquals("Invalid target port", e.getMessage());
        }

        comm1.close();
        assertEquals(0, comm1.getLocalPort());

        comm2.close();
        assertEquals(0, comm2.getLocalPort());

    }

    @Test
    public void testStartAndStop() throws Exception {

        com.gurneykri.tracker.Communicator comm1 = new com.gurneykri.tracker.Communicator();
        com.gurneykri.tracker.DummyMessageProcessor processor1 = new com.gurneykri.tracker.DummyMessageProcessor("A", comm1);
        comm1.setProcessor(processor1);
        comm1.start();

        com.gurneykri.tracker.Communicator comm2 = new com.gurneykri.tracker.Communicator();
        com.gurneykri.tracker.DummyMessageProcessor processor2 = new com.gurneykri.tracker.DummyMessageProcessor("B", comm2);
        comm2.setProcessor(processor2);
        comm2.start();

        comm1.send("Hello", InetAddress.getLocalHost(), comm2.getLocalPort());
        comm2.send("Hello there!", InetAddress.getLocalHost(), comm1.getLocalPort());
        comm2.send("What's up", InetAddress.getLocalHost(), comm1.getLocalPort());
        comm1.send("Bye", InetAddress.getLocalHost(), comm2.getLocalPort());
        comm2.send("Bye Bye", InetAddress.getLocalHost(), comm1.getLocalPort());

        Thread.sleep(100);
        assertEquals(3, processor1.ReceiveCount());
        assertEquals(2, processor2.ReceiveCount());

        comm1.stop();
        Thread.sleep(100);
        comm2.send("Are you still there?", InetAddress.getLocalHost(), comm1.getLocalPort());
        Thread.sleep(100);
        assertEquals(3, processor1.ReceiveCount());

        comm2.send("Hello?", InetAddress.getLocalHost(), comm1.getLocalPort());
        Thread.sleep(100);
        assertEquals(3, processor1.ReceiveCount());

        comm1.start();
        Thread.sleep(100);
        assertEquals(5, processor1.ReceiveCount());

        comm1.close();
        assertEquals(0, comm1.getLocalPort());

        comm2.close();
        assertEquals(0, comm2.getLocalPort());
    }

}