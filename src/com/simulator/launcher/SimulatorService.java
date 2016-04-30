package com.simulator.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimulatorService {

    public static void main(String args[]) throws Exception {
        List<Integer> arrayData = new ArrayList<>();
        arrayData.add(100);
        arrayData.add(1000);
        arrayData.add(10000);
        
        for (Integer totalData : arrayData) {
            for (int i = 0; i < 1; i++) {
                String threadId = UUID.randomUUID().toString();
                SimulatorThread st1 = new SimulatorThread(threadId, 1, totalData);
                SimulatorThread st2 = new SimulatorThread(threadId, 2, totalData);
                SimulatorThread st3 = new SimulatorThread(threadId, 3, totalData);
                SimulatorThread st4 = new SimulatorThread(threadId, 4, totalData);
                st1.start();
                st2.start();
                st3.start();
                st4.start();
            }
        }
    }
}
