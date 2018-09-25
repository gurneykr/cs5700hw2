package com.gurneykri.tracker.commands;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHelpers {

    public static void testValidMessage(List<String> badMessages, ICommand command){

        for(String bm: badMessages) {
            try {
                command.setMessage(bm);
                command.execute();
                //shouldn't got here something's wrong
                assertEquals(true, false);
            } catch (Exception e) {
                assertEquals(true, true);
            }
        }
    }
}
